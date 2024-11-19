package com.project2.ayurcare.ayurcare_backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project2.ayurcare.ayurcare_backend.entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, String>{

	List<MedicalRecord> findByPatient_Medicaluser_MedicaluserId(String medicaluserId);
	
	List<MedicalRecord> findByPatient_PatientId(String patientId);
	
	List<MedicalRecord> findByPatient_PatientIdOrderByPrescriptionIssueDateDesc(String patientId);
}
