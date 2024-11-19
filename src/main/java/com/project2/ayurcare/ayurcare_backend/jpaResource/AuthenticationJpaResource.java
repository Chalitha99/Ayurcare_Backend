package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.Service.AuthenticationService;
import com.project2.ayurcare.ayurcare_backend.entity.AuthenticationResponse;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationJpaResource {

    private final AuthenticationService authService;

    public AuthenticationJpaResource(AuthenticationService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody Medicaluser request
            ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody Medicaluser request
    ){
        return ResponseEntity.ok(authService.authenticate(request));
    }
}
