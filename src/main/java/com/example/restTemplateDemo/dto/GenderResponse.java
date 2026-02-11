package com.example.restTemplateDemo.dto;

import lombok.Data;

@Data
public class GenderResponse {

    private Integer count;
    private String gender;
    private String name;
    private Double probability;
}

