package com.project2.ayurcare.ayurcare_backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import org.springframework.data.jpa.repository.Query;

public interface PatientRepository extends JpaRepository<Patient, String>{

	Optional<Patient> findByMedicaluserMedicaluserId(String medicaluserId);

	@Query("SELECT COUNT(p) FROM Patient p")
	int countAllPatients();

	Patient findByPatientId(String patientId);

}
