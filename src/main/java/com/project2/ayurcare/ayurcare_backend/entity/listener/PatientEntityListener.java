package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class PatientEntityListener {

	private static CustomIdGenerator idGenerator;

    @Autowired
    public void setIdGenerator(CustomIdGenerator idGenerator) {
        PatientEntityListener.idGenerator = idGenerator;
    }

    @PrePersist
    public void prePersist(Patient patient) {
        if (patient.getPatientId() == null || patient.getPatientId().isEmpty()) {
            patient.setPatientId(idGenerator.generateId("ACPT", "Patient", "patientId"));
        }
    }
}
