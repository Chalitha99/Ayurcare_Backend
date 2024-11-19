package com.project2.ayurcare.ayurcare_backend.entity;

import java.sql.Date;
import java.util.List;

import com.project2.ayurcare.ayurcare_backend.entity.listener.PatientEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "patient")
@EntityListeners(PatientEntityListener.class)
public class Patient {
	
	@Id
    @Column(name = "patient_id", nullable = false, length = 100)
    private String patientId;

    @ManyToOne
    @JoinColumn(name = "medicaluser_id", nullable = false)
    private Medicaluser medicaluser;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "treatment_startdate", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date treatmentStartDate;

    @OneToMany(mappedBy = "patient")
    private List<MedicalRecord> medicalRecords;

	public Patient() {

	}

	public Patient(String patientId, Medicaluser medicaluser, Category category, Date treatmentStartDate, List<MedicalRecord> medicalRecords) {
		this.patientId = patientId;
		this.medicaluser = medicaluser;
		this.category = category;
		this.treatmentStartDate = treatmentStartDate;
		this.medicalRecords = medicalRecords;
	}

	public String getPatientId() {
		return patientId;
	}
	
	public Patient(String patientId) {
        this.patientId = patientId;
    }

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public Medicaluser getUser() {
		return medicaluser;
	}

	public void setUser(Medicaluser medicaluser) {
		this.medicaluser = medicaluser;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getTreatmentStartDate() {
		return treatmentStartDate;
	}

	public void setTreatmentStartDate(Date treatmentStartDate) {
		this.treatmentStartDate = treatmentStartDate;
	}

	public List<MedicalRecord> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecord> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}
    
    

}
