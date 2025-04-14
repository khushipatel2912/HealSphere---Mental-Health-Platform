package org.example.userauthservice.controller;


import org.example.userauthservice.service.UserService;
import org.example.userauthservice.model.User;
import org.example.userauthservice.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/register")
    public String register(@RequestParam String email, @RequestParam String name, @RequestParam String password) {
        userService.registerUser(email, name, password);
        return "User registered successfully!";
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        Optional<User> user = userService.findByEmail(email);
        return user.isPresent() ? jwtUtil.generateToken(email) : "Invalid credentials!";
    }
}
