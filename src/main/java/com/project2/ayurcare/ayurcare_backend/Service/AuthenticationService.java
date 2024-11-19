package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.entity.AuthenticationResponse;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.entity.Role;
import com.project2.ayurcare.ayurcare_backend.entity.Token;
import com.project2.ayurcare.ayurcare_backend.exceptions.EmailAlreadyExistsException;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import com.project2.ayurcare.ayurcare_backend.repository.TokenRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {

    private final MedicalUserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final TokenRepository tokenRepository;

    public AuthenticationService(MedicalUserRepository repository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager, TokenRepository tokenRepository) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
        this.tokenRepository = tokenRepository;
    }

    public AuthenticationResponse register(Medicaluser request) {

        if (repository.findByMedicaluserEmail(request.getMedicaluserEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email already exists");
        }

        Medicaluser medicaluser = new Medicaluser();
        medicaluser.setMedicaluserFirstname(request.getMedicaluserFirstname());
        medicaluser.setMedicaluserLastname(request.getMedicaluserLastname());
        medicaluser.setMedicaluserEmail(request.getMedicaluserEmail());
        medicaluser.setMedicaluserIntreatment(request.getMedicaluserIntreatment());

        if (request.getMedicaluserIntreatment() == null) {
            medicaluser.setMedicaluserIntreatment("No");
        } else {
            medicaluser.setMedicaluserIntreatment(request.getMedicaluserIntreatment());
        }

        medicaluser.setMedicaluserPhoneno(request.getMedicaluserPhoneno());
        medicaluser.setMedicaluserAddress(request.getMedicaluserAddress());
        medicaluser.setMedicaluserPhoto(request.getMedicaluserPhoto());
        medicaluser.setMedicalUserPassword(passwordEncoder.encode(request.getMedicalUserPassword()));
//        medicaluser.setRole(request.getRole());

        if (request.getRole() == null) {
            medicaluser.setRole(Role.USER);
        } else {
            medicaluser.setRole(request.getRole());
        }

        medicaluser = repository.save(medicaluser);

        String jwt = jwtService.generateToken(medicaluser);
        
        //save the generated token
        saveMedicalUserToken(jwt, medicaluser);

        return new AuthenticationResponse(jwt);
    }

    public AuthenticationResponse authenticate(Medicaluser request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getMedicaluserEmail(),
                        request.getPassword()
                )
        );

        Medicaluser medicaluser = repository.findByMedicaluserEmail(request.getMedicaluserEmail()).orElseThrow();
        String token = jwtService.generateToken(medicaluser);

        revokeAllTokenByMedicalUser(medicaluser);

        saveMedicalUserToken(token, medicaluser);

        return new AuthenticationResponse(token);
    }

    private void revokeAllTokenByMedicalUser(Medicaluser medicaluser) {
        List<Token> validTokenListByUser = tokenRepository.findAllTokenByUser(medicaluser.getMedicaluserId());

        if(!validTokenListByUser.isEmpty()){
            validTokenListByUser.forEach(t -> {
                t.setLoggedOut(true);
            });
        }
        tokenRepository.saveAll(validTokenListByUser);
    }

    private void saveMedicalUserToken(String jwt, Medicaluser medicaluser) {
        Token token = new Token();
        token.setToken(jwt);
        token.setLoggedOut(false);
        token.setMedicaluser(medicaluser);
        tokenRepository.save(token);
    }

}
