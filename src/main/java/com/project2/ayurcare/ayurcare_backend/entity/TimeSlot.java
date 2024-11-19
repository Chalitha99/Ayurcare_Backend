package com.project2.ayurcare.ayurcare_backend.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.project2.ayurcare.ayurcare_backend.entity.listener.TimeSlotEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "timeslot")
@EntityListeners(TimeSlotEntityListener.class)
public class TimeSlot {
	
	@Id
    @Column(name = "timeslot_id", nullable = false, length = 36)
    private String timeSlotId;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "start_time", nullable = false)
    private LocalTime  startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime  endTime;

    @Column(name = "is_booked", nullable = false)
    private boolean booked;
    
    @Column(name = "date", nullable = false)
    private LocalDate date;

	public TimeSlot() {
	}

	public String getTimeSlotId() {
		return timeSlotId;
	}

	public void setTimeSlotId(String timeSlotId) {
		this.timeSlotId = timeSlotId;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public LocalTime  getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime  startTime) {
		this.startTime = startTime;
	}

	public LocalTime  getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime  endTime) {
		this.endTime = endTime;
	}

	public boolean isBooked() {
		return booked;
	}

	public void setBooked(boolean booked) {
		this.booked = booked;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

}
