package com.example.restTemplateDemo.service;

import com.example.restTemplateDemo.dto.GenderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GenderService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.genderize.io?name=hashim";

    public GenderResponse getGender(String name) {

        String url = BASE_URL + name;

        return restTemplate.getForObject(BASE_URL, GenderResponse.class);
    }
}
