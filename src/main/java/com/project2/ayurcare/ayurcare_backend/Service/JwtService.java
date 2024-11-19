package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.repository.TokenRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {

    private final String SECRET_KEY = "7363dc12c56ae7c6d7ed4d8ff7aca8fa95f48bd1409a922aecf325f1355607e6";

    private final TokenRepository tokenRepository;

    public JwtService(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    //extract the email from the token
    public String extractMedicalUserEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    //validate the token
    public boolean isValid(String token, UserDetails medicalUser){
        String medicalUserEmail = extractMedicalUserEmail(token);

        boolean isValidToken = tokenRepository.findByToken(token)
                .map(t->!t.isLoggedOut()).orElse(false);

        return (medicalUserEmail.equals(medicalUser.getUsername())) && !isTokenExpired(token) && isValidToken;
    }

    // checking token is expired or not
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    //extracting expiration date of the token
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    //to get specific info from the payload
    public <T> T extractClaim(String token, Function<Claims, T> resolver){
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    // to extract all the info fro the token payload
    private Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigninKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String generateToken(Medicaluser medicalUser){
        String token = Jwts
                .builder()
                .subject(medicalUser.getMedicaluserEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+ 24*60*60*1000))
                .signWith(getSigninKey())
                .compact();
        return token;
    }

    private SecretKey getSigninKey() {
        byte[] keyBytes = Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

