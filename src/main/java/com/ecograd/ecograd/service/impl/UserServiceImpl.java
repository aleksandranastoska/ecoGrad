package com.ecograd.ecograd.service.impl;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.model.exception.InvalidUsernameException;
import com.ecograd.ecograd.repository.LitterRepository;
import com.ecograd.ecograd.repository.UserRepository;
import com.ecograd.ecograd.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final LitterRepository litterRepository;

    public UserServiceImpl(UserRepository userRepository, LitterRepository litterRepository) {
        this.userRepository = userRepository;
        this.litterRepository = litterRepository;
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
    public Double calculatePointsByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(()->new InvalidUsernameException(username));
        Double points = litterRepository.findByUserUsername(username).stream().mapToDouble(Litter::getScore).sum();
        user.setPoints(points);
        return points;
    }
}
