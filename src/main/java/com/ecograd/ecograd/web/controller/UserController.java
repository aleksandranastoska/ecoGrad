package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.User;
import com.ecograd.ecograd.repository.UserRepository;
import com.ecograd.ecograd.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/profile")
    private String getProfilePage(HttpServletRequest req, Model model){
        String currentUser = req.getRemoteUser();
        User user = userService.findByUsername(currentUser);
        model.addAttribute("user",user);
        return "profile-page";
    }
}
