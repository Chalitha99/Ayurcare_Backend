package com.project2.ayurcare.ayurcare_backend.DTO;


import java.time.LocalTime;

public class VideoCallDTO {
    private String medicaluserId;
    private String timeslotId;
    private String patientId;
    private Integer videoCallId;
    private String patientName;
    private LocalTime startTime;
    private LocalTime endTime;
    private String category;
    private String status;

    // Getters and Setters
    public String getMedicaluserId() {
        return medicaluserId;
    }

    public void setMedicaluserId(String medicaluserId) {
        this.medicaluserId = medicaluserId;
    }

    public String getTimeSlotId() {
        return timeslotId;
    }

    public void setTimeSlotId(String timeslotId) {
        this.timeslotId = timeslotId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public Integer getVideoCallId() {
        return videoCallId;
    }

    public void setVideoCallId(Integer videoCallId) {
        this.videoCallId = videoCallId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
