package com.Category.Category_service.Service;

import com.Category.Category_service.Dto.SalonDto;
import com.Category.Category_service.model.Category;

import java.util.List;
import java.util.Set;

public interface CategoryService {
    Category saveCategory(Category category , SalonDto salonDto);

    Set<Category> getAllCategoriesBySalonId(Long salonId);

    Category getCategoryById(Long categoryId) throws Exception;

    void deleteCategoryById(Long categoryId , Long SalonId);
}
