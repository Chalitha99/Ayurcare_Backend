package com.project2.ayurcare.ayurcare_backend.DTO;

public class CategoryDTO {

    private String categoryId;
    private String predefinedTime;
    private String categories;

    public CategoryDTO() {
    }

    public CategoryDTO(String categoryId, String predefinedTime, String categories) {
        this.categoryId = categoryId;
        this.predefinedTime = predefinedTime;
        this.categories = categories;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getPredefinedTime() {
        return predefinedTime;
    }

    public void setPredefinedTime(String predefinedTime) {
        this.predefinedTime = predefinedTime;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

}
