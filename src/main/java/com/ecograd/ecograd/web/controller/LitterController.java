package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.service.LitterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class LitterController {
    private final LitterService litterService;

    public LitterController(LitterService litterService) {
        this.litterService = litterService;
    }

    @GetMapping("")
    public String getHomePage(Model model){

        return "home-page";
    }
}
