package com.project2.ayurcare.ayurcare_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.ayurcare.ayurcare_backend.entity.Category;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, String>{

    Optional<Category> findByCategories(String categories);

    // Custom query to get the count of booked time slots for each category
    @Query("SELECT c.categories, COUNT(ts) " +
            "FROM Category c LEFT JOIN c.timeSlots ts " +
            "ON ts.booked = true " +
            "GROUP BY c.categories")
    List<Object[]> findCategoriesWithBookedTimeSlotsCount();

}
