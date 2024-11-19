package com.project2.ayurcare.ayurcare_backend.jpaResource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Demo {

    @GetMapping("/demo")
    public ResponseEntity<String> getDemo(){
        return ResponseEntity.ok("Hello World");
    }

    @GetMapping("/admin_only")
    public String adminOnly(){
        return "Hello World";
    }
}

