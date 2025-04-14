package com.microservices.repository;

import com.microservices.entity.LittleAct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LittleActRepository extends JpaRepository<LittleAct, Long> {
}
