package com.project2.ayurcare.ayurcare_backend.entity;

import java.time.LocalDateTime;

import com.project2.ayurcare.ayurcare_backend.entity.listener.BookingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
@EntityListeners(BookingEntityListener.class)
public class Booking {

	@Id
    @Column(name = "booking_id", nullable = false, length = 10)
    private String bookingId;

    @ManyToOne
    @JoinColumn(name = "timeslot_id", nullable = false)
    private TimeSlot timeSlotId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "booking_date", nullable = false)
    private LocalDateTime bookingDate;

	@Column(name = "status", nullable = false)
	private String status = "Booked";

	public Booking() {

	}

	public String getBookingId() {
		return bookingId;
	}

	public void setBookingId(String bookingId) {
		this.bookingId = bookingId;
	}

	public TimeSlot getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(TimeSlot timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public LocalDateTime getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDateTime bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
