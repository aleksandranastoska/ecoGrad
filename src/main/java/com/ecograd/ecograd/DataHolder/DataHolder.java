package com.ecograd.ecograd.DataHolder;

import com.ecograd.ecograd.model.Role;
import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import java.time.LocalDate;


@Component
public class DataHolder {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataHolder(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void init() {
        User user = new User("kostova", passwordEncoder.encode("tamara123"), "Tamara", "Kostova", "kostovatamara@gmail.com", LocalDate.now(), 0d, Role.USER);
        userRepository.save(user);
    }


}
