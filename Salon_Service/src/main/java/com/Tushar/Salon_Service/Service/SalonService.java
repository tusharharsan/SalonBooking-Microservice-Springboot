package com.Tushar.Salon_Service.Service;

import com.Tushar.Salon_Service.Model.Salon;
import com.Tushar.Salon_Service.payload.Dto.SalonDto;
import com.Tushar.Salon_Service.payload.Dto.UserDto;

import java.util.List;

public interface SalonService {
    Salon createSalon(SalonDto salon , UserDto user);

    Salon updateSalon(SalonDto salon , UserDto user , Long salonId) throws Exception;

    List<Salon> getAllSalons();

    Salon getSalonById(Long salonId);

    Salon getSalonByOwnerId(Long ownerId);

    List<Salon> getSalonsByCity(String city);

    List<SalonDto> searchSalonByCity(String city);



}
