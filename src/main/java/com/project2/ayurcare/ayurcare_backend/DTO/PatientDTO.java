package com.project2.ayurcare.ayurcare_backend.DTO;

import java.sql.Date;

public class PatientDTO {

    private String patientId;
    private String medicaluser;
    private String category;
    private Date treatmentStartDate;

    // Getters and setters
    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }


    public Date getTreatmentStartDate() {
        return treatmentStartDate;
    }

    public void setTreatmentStartDate(Date treatmentStartDate) {
        this.treatmentStartDate = treatmentStartDate;
    }

	public String getMedicaluser() {
		return medicaluser;
	}

	public void setMedicaluser(String medicaluser) {
		this.medicaluser = medicaluser;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

    
}
