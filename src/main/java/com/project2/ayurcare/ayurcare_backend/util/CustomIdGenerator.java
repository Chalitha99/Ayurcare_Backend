package com.project2.ayurcare.ayurcare_backend.util;

import org.springframework.stereotype.Component;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Component
public class CustomIdGenerator {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public String generateId(String prefix, String entityName, String idFieldName) {
		Long count = entityManager.createQuery("SELECT COUNT(e) FROM " + entityName + " e", Long.class)
				.getSingleResult();
		Long nextId = count + 1;
		return String.format("%s%05d", prefix, nextId);
	}
}
