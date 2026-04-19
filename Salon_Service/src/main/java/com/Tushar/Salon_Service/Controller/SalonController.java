package com.Tushar.Salon_Service.Controller;

import com.Tushar.Salon_Service.Mapper.SalonMapper;
import com.Tushar.Salon_Service.Model.Salon;
import com.Tushar.Salon_Service.Service.SalonService;
import com.Tushar.Salon_Service.payload.Dto.SalonDto;
import com.Tushar.Salon_Service.payload.Dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salons")
@RequiredArgsConstructor
public class SalonController {
    private final SalonService salonService;

    @PostMapping()
    public ResponseEntity<SalonDto> createSalon(@RequestBody SalonDto salonDto) {
        UserDto user = new UserDto();
        user.setId(1L);
        Salon salon = salonService.createSalon(salonDto, user);
        SalonDto salonDto1 = SalonMapper.mapSalonToSalonDto(salon);
        return ResponseEntity.ok(salonDto1);

    }

    @PatchMapping("/{salonId}")
    public ResponseEntity<SalonDto> updateSalon(@RequestBody SalonDto salonDto,
                                                @PathVariable Long salonId) throws Exception {
        UserDto user = new UserDto();
        user.setId(1L);
        Salon salon = salonService.updateSalon(salonDto, user, salonId);
        SalonDto salonDto1 = SalonMapper.mapSalonToSalonDto(salon);
        return ResponseEntity.ok(salonDto1);
    }

    @GetMapping()
    public ResponseEntity<List<SalonDto>> getSalon() {
        List<Salon> salon = salonService.getAllSalons();
        List<SalonDto> salonDto = salon.stream().map(SalonMapper::mapSalonToSalonDto).toList();
        return ResponseEntity.ok(salonDto);
    }

    @GetMapping("/{salonId}")
    public ResponseEntity<SalonDto> getSalonById(@PathVariable Long salonId) {
        Salon salon = salonService.getSalonById(salonId);
        SalonDto salonDto = SalonMapper.mapSalonToSalonDto(salon);
        return ResponseEntity.ok(salonDto);
    }

    @GetMapping("/owner/{ownerId}")
    public ResponseEntity< SalonDto> getSalonByOwnerId(@PathVariable Long ownerId) {
        Salon salon = salonService.getSalonByOwnerId(ownerId);
        SalonDto salonDto = SalonMapper.mapSalonToSalonDto(salon);
        return ResponseEntity.ok(salonDto);
    }

    @GetMapping("/city/{city}")
    public ResponseEntity<List<SalonDto>> getSalonsByCity(@PathVariable String city) {
        List<Salon> salons = salonService.getSalonsByCity(city);
        List<SalonDto> salonDto = salons.stream().map(SalonMapper::mapSalonToSalonDto).toList();
        return ResponseEntity.ok(salonDto);
    }

    @GetMapping("/search")
    public ResponseEntity<List<SalonDto>> searchSalonByCity(@RequestParam String city) {
        List<SalonDto> salons = salonService.searchSalonByCity(city);
        return ResponseEntity.ok(salons);
    }
}
