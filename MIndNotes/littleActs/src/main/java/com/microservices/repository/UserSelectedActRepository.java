package com.microservices.repository;

import com.microservices.entity.UserSelectedAct;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserSelectedActRepository extends JpaRepository<UserSelectedAct, Long> {
    List<UserSelectedAct> findByUserId(Long userId);
}
