package com.Tushar.Salon_Service.Mapper;

import com.Tushar.Salon_Service.Model.Salon;
import com.Tushar.Salon_Service.payload.Dto.SalonDto;

public class SalonMapper {
    public static SalonDto mapSalonToSalonDto(Salon salon) {
        SalonDto salonDto = new SalonDto();
        salonDto.setId(salon.getId());
        salonDto.setName(salon.getName());
        salonDto.setAddress(salon.getAddress());
        salonDto.setCity(salon.getCity());
        salonDto.setEmail(salon.getEmail());
        salonDto.setPhoneNumber(salon.getPhoneNumber());
        salonDto.setOpeningTime(salon.getOpeningTime());
        salonDto.setCloseingTime(salon.getCloseingTime());
        salonDto.setOwnerid(salon.getOwnerid());
        return salonDto;
    }
}
