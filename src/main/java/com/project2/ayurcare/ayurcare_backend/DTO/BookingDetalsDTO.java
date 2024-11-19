package com.project2.ayurcare.ayurcare_backend.DTO;

public class BookingDetalsDTO {

    private String bookingId;
    private String status;
    private String timeSlotId;
    private String patientFirstName;
    private String patientLastName;
    private String timeSlotStartTime;
    private String timeSlotEndTime;
    private String categoryName;

    // Constructors, getters, and setters
    public BookingDetalsDTO(String bookingId,String status,String timeSlotId, String patientFirstName, String patientLastName, String timeSlotStartTime, String timeSlotEndTime, String categoryName) {
        this.bookingId = bookingId;
        this.status = status;
        this.timeSlotId = timeSlotId;
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.timeSlotStartTime = timeSlotStartTime;
        this.timeSlotEndTime = timeSlotEndTime;
        this.categoryName = categoryName;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getTimeSlotId() {
        return timeSlotId;
    }

    public void setTimeSlotId(String timeSlotId) {
        this.timeSlotId = timeSlotId;
    }

    public String getPatientFirstName() {
        return patientFirstName;
    }

    public void setPatientFirstName(String patientFirstName) {
        this.patientFirstName = patientFirstName;
    }

    public String getPatientLastName() {
        return patientLastName;
    }

    public void setPatientLastName(String patientLastName) {
        this.patientLastName = patientLastName;
    }

    public String getTimeSlotStartTime() {
        return timeSlotStartTime;
    }

    public void setTimeSlotStartTime(String timeSlotStartTime) {
        this.timeSlotStartTime = timeSlotStartTime;
    }

    public String getTimeSlotEndTime() {
        return timeSlotEndTime;
    }

    public void setTimeSlotEndTime(String timeSlotEndTime) {
        this.timeSlotEndTime = timeSlotEndTime;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
