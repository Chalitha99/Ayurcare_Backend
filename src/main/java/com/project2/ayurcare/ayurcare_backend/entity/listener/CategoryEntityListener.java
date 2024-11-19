package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.Category;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class CategoryEntityListener {
	private static CustomIdGenerator idGenerator;

	@Autowired
	public void setIdGenerator(CustomIdGenerator idGenerator) {
		CategoryEntityListener.idGenerator = idGenerator;
	}

	@PrePersist
	public void prePersist(Category category) {
		if (category.getCategoryId() == null || category.getCategoryId().isEmpty()) {
			category.setCategoryId(idGenerator.generateId("ACC", "Category", "categoryId"));
		}
	}
}
