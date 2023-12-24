package com.ecograd.ecograd.web.controller;

import com.ecograd.ecograd.model.Litter;
import com.ecograd.ecograd.model.LitterSeverity;
import com.ecograd.ecograd.model.LitterType;
import com.ecograd.ecograd.service.LitterService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping()
public class LitterController {
    private final LitterService litterService;

    public LitterController(LitterService litterService) {
        this.litterService = litterService;
    }

    @GetMapping("/home")
    public String getHomePage(Model model){
        model.addAttribute("litters",litterService.findAll());
        return "home-page";
    }
    @GetMapping("/add")
    public String getAddPage(Model model){
        return "add-page";
    }
    @PostMapping("/add")
    public String addLitter(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReported,
                            @RequestParam Double longitude,
                            @RequestParam Double latitude,
                            @RequestParam LitterType litterType,
                            @RequestParam LitterSeverity litterSeverity,
                            @RequestParam byte[] imageData
                            ){
        Litter litter = new Litter(dateReported,longitude,latitude,litterType,litterSeverity,imageData);
        litterService.addLitter(litter);
        return "redirect:/home";
    }
    @GetMapping("/edit/{id}")
    public String getEditPage(@PathVariable Long id, Model model){
        model.addAttribute("litter",litterService.findById(id));
        return "add-page";
    }
    @PostMapping("/edit/{id}")
    public String editLitter(@PathVariable Long id,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateReported,
                            @RequestParam Double longitude,
                            @RequestParam Double latitude,
                            @RequestParam LitterType litterType,
                            @RequestParam LitterSeverity litterSeverity,
                            @RequestParam byte[] imageData
    ){
        Litter litter = litterService.findById(id);
        litter.setDateReported(dateReported);
        litter.setLongitude(longitude);
        litter.setLatitude(latitude);
        litter.setLitterType(litterType);
        litter.setLitterSeverity(litterSeverity);
        litter.setImageData(imageData);
        litterService.addLitter(litter);
        return "redirect:/home";
    }
}
