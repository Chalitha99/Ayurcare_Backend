package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.MedicalRecord;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class MedicalRecordEntityListener {
	private static CustomIdGenerator idGenerator;

	@Autowired
	public void setIdGenerator(CustomIdGenerator idGenerator) {
		MedicalRecordEntityListener.idGenerator = idGenerator;
	}

	@PrePersist
	public void prePersist(MedicalRecord medicalrecord) {
		if (medicalrecord.getMedicalId() == null || medicalrecord.getMedicalId().isEmpty()) {
			medicalrecord.setMedicalId(idGenerator.generateId("ACMR", "MedicalRecord", "medicalId"));
		}
	}
}
