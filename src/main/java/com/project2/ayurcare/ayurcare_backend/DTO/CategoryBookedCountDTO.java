package com.project2.ayurcare.ayurcare_backend.DTO;

public class CategoryBookedCountDTO {

    private String categoryName;
    private Long bookedCount;

    public CategoryBookedCountDTO(String categoryName, Long bookedCount) {
        this.categoryName = categoryName;
        this.bookedCount = bookedCount;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Long getBookedCount() {
        return bookedCount;
    }

    public void setBookedCount(Long bookedCount) {
        this.bookedCount = bookedCount;
    }
}
