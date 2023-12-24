package com.ecograd.ecograd.repository;

import com.ecograd.ecograd.model.Litter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LitterRepository extends JpaRepository<Litter, Long> {
}
