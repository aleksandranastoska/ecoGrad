package com.ecograd.ecograd.service;

import com.ecograd.ecograd.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    User findByUsername(String username);
    Double calculatePointsByUsername(String username);
}
