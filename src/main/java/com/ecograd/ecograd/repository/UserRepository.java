package com.ecograd.ecograd.repository;

import com.ecograd.ecograd.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
}
