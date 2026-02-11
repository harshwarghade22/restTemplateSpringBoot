package com.example.restTemplateDemo.controller;

import com.example.restTemplateDemo.dto.GenderResponse;
import com.example.restTemplateDemo.service.GenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class GenderController {

    @Autowired
    private GenderService genderService;

    @GetMapping("/gender")
    public GenderResponse getGender(@RequestParam String name) {
        return genderService.getGender(name);
    }
}
