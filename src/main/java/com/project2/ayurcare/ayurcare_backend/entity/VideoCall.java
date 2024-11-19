package com.project2.ayurcare.ayurcare_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "videocall")
public class VideoCall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "videocall_id", nullable = false)
    private Integer videoCallId;  // Changed to Integer for auto-increment

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private TimeSlot timeSlot;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "status", nullable = false, length = 50)
    private String status = "Proceed";  // Default value for status

    public VideoCall() {
    }

    public VideoCall(Integer videoCallId, TimeSlot timeSlot, Patient patient, String status) {
        this.videoCallId = videoCallId;
        this.timeSlot = timeSlot;
        this.patient = patient;
        this.status = status;
    }

    public Integer getVideoCallId() {
        return videoCallId;
    }

    public void setVideoCallId(Integer videoCallId) {
        this.videoCallId = videoCallId;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(TimeSlot timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}