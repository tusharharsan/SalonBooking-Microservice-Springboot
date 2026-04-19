package com.Tushar.Salon_Service.Service.Impl;

import com.Tushar.Salon_Service.Model.Salon;
import com.Tushar.Salon_Service.Repository.SalonRespository;
import com.Tushar.Salon_Service.Service.SalonService;
import com.Tushar.Salon_Service.payload.Dto.SalonDto;
import com.Tushar.Salon_Service.payload.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {

    private final SalonRespository salonRespository;

    @Override
    public Salon createSalon(SalonDto req, UserDto user) {
        Salon salone = new Salon();
        salone.setName(req.getName());
        salone.setAddress(req.getAddress());
        salone.setCity(req.getCity());
        salone.setEmail(req.getEmail());
        salone.setPhoneNumber(req.getPhoneNumber());
        salone.setOpeningTime(req.getOpeningTime());
        salone.setCloseingTime(req.getCloseingTime());
        salone.setOwnerid(user.getId());

        Salon savedSalon = salonRespository.save(salone);
        return savedSalon;
    }

    @Override
    public Salon updateSalon(SalonDto salon, UserDto user, Long salonId) throws Exception {
        Salon existingSalon = salonRespository.findById(salonId).orElse(null);
        if (existingSalon != null && salon.getOwnerid().equals(user.getId())) {
            if (!existingSalon.getOwnerid().equals(user.getId())) {
                throw new RuntimeException("Unauthorized to update this salon");
            }
            existingSalon.setName(salon.getName());
            existingSalon.setAddress(salon.getAddress());
            existingSalon.setCity(salon.getCity());
            existingSalon.setEmail(salon.getEmail());
            existingSalon.setPhoneNumber(salon.getPhoneNumber());
            existingSalon.setOpeningTime(salon.getOpeningTime());
            existingSalon.setCloseingTime(salon.getCloseingTime());
            existingSalon.setOwnerid(user.getId());

            Salon updatedSalon = salonRespository.save(existingSalon);
            return updatedSalon;
        }
        throw new Exception("Salon not found with id: " + salonId);
    }

    @Override
    public List<Salon> getAllSalons() {
        return List.of();
    }

    @Override
    public Salon getSalonById(Long salonId) {
        return null;
    }

    @Override
    public Salon getSalonByOwnerId(String ownerId) {
        return null;
    }

    @Override
    public List<Salon> getSalonsByCity(String city) {
        return List.of();
    }
}
