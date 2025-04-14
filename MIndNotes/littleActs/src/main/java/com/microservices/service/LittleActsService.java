package com.microservices.service;

import com.microservices.entity.LittleAct;
import com.microservices.entity.User;
import com.microservices.entity.UserSelectedAct;
import com.microservices.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LittleActsService {
    @Autowired
    private UserSelectedActRepository userSelectedActRepository;

    @Autowired
    private LittleActRepository littleActRepository;

    @Autowired
    private UserRepository userRepository;

    public List<UserSelectedAct> getUserActs(Long userId) {
        return userSelectedActRepository.findByUserId(userId);
    }

    public UserSelectedAct incrementAct(UserSelectedAct userSelectedAct) {
        userSelectedAct.setCount(userSelectedAct.getCount() + 1);
        userSelectedAct.setUpdatedAt(LocalDateTime.now());
        return userSelectedActRepository.save(userSelectedAct);
    }

    public LittleAct addLittleAct(LittleAct littleAct) {
        return littleActRepository.save(littleAct);
    }

    public User registerUser(User user) {
        return userRepository.save(user);
    }
}
