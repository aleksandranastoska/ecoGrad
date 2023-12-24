package com.ecograd.ecograd.repository;

import com.ecograd.ecograd.model.Litter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LitterRepository extends JpaRepository<Litter, Long> {
    List<Litter> findByUserUsername(String username);
}
