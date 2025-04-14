package com.microservices.login.controllers;

import com.microservices.login.models.User;
import com.microservices.login.services.AuthService;
import com.microservices.login.config.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        Optional<User> existingUser = authService.findByUsername(user.getUsername());

        if (existingUser.isPresent() && authService.matchesPassword(user.getPassword(), existingUser.get().getPassword())) {
            return ResponseEntity.ok(jwtUtil.generateToken(existingUser.get().getUsername()));
        }

        return ResponseEntity.status(401).body("Invalid Credentials");
    }

}
