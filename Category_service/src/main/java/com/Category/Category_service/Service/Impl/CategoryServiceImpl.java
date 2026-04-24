package com.Category.Category_service.Service.Impl;

import com.Category.Category_service.Dto.SalonDto;
import com.Category.Category_service.Repository.CategoryRepository;
import com.Category.Category_service.Service.CategoryService;
import com.Category.Category_service.model.Category;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategory(Category category, SalonDto salonDto) {
        Category newcategory = new Category();
        newcategory.setName(category.getName());
        newcategory.setSalonId(category.getSalonId());
        newcategory.setImage(category.getImage());
        return categoryRepository.save(newcategory);
    }

    @Override
    public Set<Category> getAllCategoriesBySalonId(Long salonId) {

        return  categoryRepository.findBySalonId(salonId);
    }

    @Override
    public Category getCategoryById(Long categoryId) throws Exception {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if(category == null){
            throw new Exception("Category not found with id: " + categoryId);
        }
        return category;
    }

    @Override
    public void deleteCategoryById(Long categoryId ,Long SalonId) {
        Category category = categoryRepository.findById(categoryId).orElse(null);

        if(category == null){
            throw new RuntimeException("Category not found with id: " + categoryId);
        }
        if(!category.getSalonId().equals(SalonId)){
            throw new RuntimeException("Unauthorized to delete this category");
        }
        categoryRepository.deleteById(categoryId);
    }
}
