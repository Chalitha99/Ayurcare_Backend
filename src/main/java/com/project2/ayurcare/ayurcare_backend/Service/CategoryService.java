package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.CategoryBookedCountDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.CategoryDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Category;
import com.project2.ayurcare.ayurcare_backend.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapperConfig;


    public CategoryDTO getCategory(String categoryName) {
        Optional<Category> optionalCategory = categoryRepository.findByCategories(categoryName);
        if (!optionalCategory.isPresent()) {
            throw new IllegalArgumentException("Medical user not found");
        }

        Category category = optionalCategory.get();
        CategoryDTO categoryDTO = modelMapperConfig.map(category, CategoryDTO.class);

        return categoryDTO;
    }


    public List<CategoryDTO> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDTO> categoryDTOS = new ArrayList<>();

        for (Category category : categories) {
            CategoryDTO categoryDTO = modelMapperConfig.map(category, CategoryDTO.class);
            categoryDTOS.add(categoryDTO);  // Add the mapped DTO to the list
        }

        return categoryDTOS;
    }

    public CategoryDTO updatePredefinedTime(String categoryId, String newPredefinedTime) {
        Optional<Category> optionalCategory = categoryRepository.findById(categoryId);
        if (!optionalCategory.isPresent()) {
            throw new IllegalArgumentException("Category not found");
        }

        Category category = optionalCategory.get();
        category.setPredefinedTime(newPredefinedTime);
        Category updatedCategory = categoryRepository.save(category); // Save the updated category

        return modelMapperConfig.map(updatedCategory, CategoryDTO.class);
    }


    // Fetch the category names and booked time slot count
    public List<CategoryBookedCountDTO> getCategoriesWithBookedCount() {
        List<Object[]> result = categoryRepository.findCategoriesWithBookedTimeSlotsCount();
        List<CategoryBookedCountDTO> dtoList = new ArrayList<>();

        for (Object[] row : result) {
            String categoryName = (String) row[0];
            Long bookedCount = (Long) row[1];
            dtoList.add(new CategoryBookedCountDTO(categoryName, bookedCount));
        }

        return dtoList;
    }


}
