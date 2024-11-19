package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class TimeSlotEntityListener {
	private static CustomIdGenerator idGenerator;

	@Autowired
	public void setIdGenerator(CustomIdGenerator idGenerator) {
		TimeSlotEntityListener.idGenerator = idGenerator;
	}

	@PrePersist
	public void prePersist(TimeSlot timeSlot) {
		if (timeSlot.getTimeSlotId() == null || timeSlot.getTimeSlotId().isEmpty()) {
			timeSlot.setTimeSlotId(idGenerator.generateId("ACT", "TimeSlot", "timeSlotId"));
		}
	}
}
