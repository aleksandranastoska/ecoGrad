package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.repository.UserRepository;
import com.ecograd.ecograd.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new UsernameNotFoundException(username));
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(()->new InvalidUsernameException(username));
    }
    @Override
    public User register(String username, String password, String name, String surname, String email, LocalDate dateOfBirth, Integer points, Role role) {
        User user = new User(username, passwordEncoder.encode(password), name, surname, email, dateOfBirth, 0, role);
        return userRepository.save(user);
    }


}
