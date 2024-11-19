package com.project2.ayurcare.ayurcare_backend.DTO;

import java.time.LocalDateTime;


public class MedicalRecordDTO {

    private String medicalId;
    private String patientId;
    private LocalDateTime prescriptionIssueDate;
    private String medicalRecord;
    
	public MedicalRecordDTO() {

	}

	public MedicalRecordDTO(String medicalId, String patientId, LocalDateTime prescriptionIssueDate, String medicalRecord) {
		super();
		this.medicalId = medicalId;
		this.patientId = patientId;
		this.prescriptionIssueDate = prescriptionIssueDate;
		this.medicalRecord = medicalRecord;
	}

	public String getMedicalId() {
		return medicalId;
	}

	public void setMedicalId(String medicalId) {
		this.medicalId = medicalId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
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

	public void setMedicalRecord(String medicalRecord) {
		this.medicalRecord = medicalRecord;
	}
    
    
}

