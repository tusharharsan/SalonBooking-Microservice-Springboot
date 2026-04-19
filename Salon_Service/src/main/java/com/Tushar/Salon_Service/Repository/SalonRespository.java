package com.Tushar.Salon_Service.Repository;

import com.Tushar.Salon_Service.Model.Salon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SalonRespository extends JpaRepository<Salon ,Long> {
    Salon findByOwnerid(Long ownerId);

    @Query("SELECT s FROM Salon s WHERE s.name LIKE %:keyword% OR s.city LIKE %:keyword% OR s.address LIKE %:keyword%" )
    List<Salon> searchSalons(@Param("keyword") String keyword);
}
