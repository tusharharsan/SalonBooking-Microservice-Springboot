package com.Category.Category_service.Repository;

import com.Category.Category_service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Set<Category> findBySalonId(Long salonId);
}
