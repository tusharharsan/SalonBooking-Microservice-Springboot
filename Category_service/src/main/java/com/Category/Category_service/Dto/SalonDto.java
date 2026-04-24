package com.Category.Category_service.Dto;

import lombok.Data;

import java.time.LocalTime;
import java.util.List;

@Data
public class SalonDto {
    private Long id;


    private String name;


    private List<String> images;


    private String address;

    private String phoneNumber;


    private String email;


    private String city;


    private Long ownerid;

    private UserDto owner;

    private LocalTime openingTime;


    private LocalTime closeingTime;
}
