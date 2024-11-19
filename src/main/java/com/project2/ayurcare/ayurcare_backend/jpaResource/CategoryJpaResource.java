package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.DTO.CategoryBookedCountDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.CategoryDTO;
import com.project2.ayurcare.ayurcare_backend.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryJpaResource {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/category/{categoryDetails}")
    public CategoryDTO getCategoryDetails(@PathVariable("categoryDetails") String categoryDetails) {
        return categoryService.getCategory(categoryDetails);
    }

    @GetMapping("/category/getall")
    public List<CategoryDTO> getAllCategory() {
        return categoryService.getAllCategories();
    }

//    @PutMapping("/category/update/predefinedTime/{categoryId}")
//    public CategoryDTO updatePredefinedTime(@PathVariable String categoryId, @RequestBody String newPredefinedTime) {
//        return categoryService.updatePredefinedTime(categoryId, newPredefinedTime);
//    }

    @PutMapping("/category/update/predefinedTime/{categoryId}")
    public CategoryDTO updatePredefinedTime(@PathVariable String categoryId, @RequestBody CategoryDTO categoryDTO) {
        return categoryService.updatePredefinedTime(categoryId, categoryDTO.getPredefinedTime());
    }

    @GetMapping("/category/booked-count")
    public List<CategoryBookedCountDTO> getCategoriesWithBookedCount() {
        return categoryService.getCategoriesWithBookedCount();
    }


}
