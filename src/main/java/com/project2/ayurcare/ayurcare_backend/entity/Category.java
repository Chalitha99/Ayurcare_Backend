package com.project2.ayurcare.ayurcare_backend.entity;

import java.util.List;

import com.project2.ayurcare.ayurcare_backend.entity.listener.CategoryEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "category")
@EntityListeners(CategoryEntityListener.class)
public class Category {

	@Id
	@Column(name = "category_id", nullable = false, length = 10)
	private String categoryId;

	@Column(name = "predefined_time", nullable = false, length = 100)
	private String predefinedTime;

	@Column(name = "categories", nullable = false, length = 200)
	private String categories;

	@OneToMany(mappedBy = "category")
	private List<Patient> patients;

	@OneToMany(mappedBy = "category")
	private List<TimeSlot> timeSlots;

	public Category() {

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

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}



}
