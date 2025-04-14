package com.microservices.controller;

import com.microservices.entity.LittleAct;
import com.microservices.entity.User;
import com.microservices.entity.UserSelectedAct;
import com.microservices.service.LittleActsService;
import com.microservices.repository.UserRepository;
import com.microservices.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/little-acts")
public class LittleActsController {

    @Autowired
    private LittleActsService littleActsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    // Extracts userId from JWT Token
    private Long getUserIdFromToken(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }

        String token = authHeader.substring(7);
        String email = jwtUtil.extractUsername(token);

        return userRepository.findByEmail(email)
                .map(User::getId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    // Get User's Little Acts (JWT Protected)
    @GetMapping("/user-acts")
    public List<UserSelectedAct> getUserActs(@RequestHeader("Authorization") String authHeader) {
        Long userId = getUserIdFromToken(authHeader);
        return littleActsService.getUserActs(userId);
    }

    // Increment Little Act Count (JWT Protected)
    @PostMapping("/increment")
    public UserSelectedAct incrementAct(@RequestHeader("Authorization") String authHeader,
                                        @RequestBody UserSelectedAct userSelectedAct) {
        Long userId = getUserIdFromToken(authHeader);

        if (!userSelectedAct.getUser().getId().equals(userId)) {
            throw new RuntimeException("Unauthorized action");
        }

        return littleActsService.incrementAct(userSelectedAct);
    }

    // Add a New Little Act (JWT Protected)
    @PostMapping("/add-act")
    public LittleAct addLittleAct(@RequestHeader("Authorization") String authHeader,
                                  @RequestBody LittleAct littleAct) {
        getUserIdFromToken(authHeader);
        return littleActsService.addLittleAct(littleAct);
    }

    // Register a New User (Public - No JWT Required)
    @PostMapping("/register-user")
    public User registerUser(@RequestBody User user) {
        return littleActsService.registerUser(user);
    }
}
