package com.project2.ayurcare.ayurcare_backend.entity;

import java.time.LocalDateTime;
import java.util.Objects;

import com.project2.ayurcare.ayurcare_backend.entity.listener.MedicalRecordEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "medicalrecord")
@EntityListeners(MedicalRecordEntityListener.class)
public class MedicalRecord {

	@Id
	@Column(name = "medical_id", nullable = false, length = 10)
	private String medicalId;

	@ManyToOne
	@JoinColumn(name = "patient_id", nullable = false)
	private Patient patient;

//	@Column(name = "prescription_issuedate", nullable = false)
//	@Temporal(TemporalType.DATE)
//	private Date prescriptionIssueDate;
	
	@Column(name = "prescription_issuedate", nullable = false)
	private LocalDateTime prescriptionIssueDate;

	@Column(name = "medical_record", nullable = true)
	private String medicalRecord;

	public MedicalRecord() {

	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDateTime getPrescriptionIssueDate() {
		return prescriptionIssueDate;
	}

	public void setPrescriptionIssueDate(LocalDateTime prescriptionIssueDate) {
		this.prescriptionIssueDate = prescriptionIssueDate;
	}

	public String getMedicalRecord() {
		return medicalRecord;
	}
	
//	public Optional<String>  getMedicalRecord() {
//		return Optional.ofNullable(medicalRecord);
//	}

	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
	}

	@Override
	public int hashCode() {
		return Objects.hash(medicalId, medicalRecord, patient, prescriptionIssueDate);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MedicalRecord other = (MedicalRecord) obj;
		return Objects.equals(medicalId, other.medicalId) && Objects.equals(medicalRecord, other.medicalRecord)
				&& Objects.equals(patient, other.patient)
				&& Objects.equals(prescriptionIssueDate, other.prescriptionIssueDate);
	}
	
	

}
