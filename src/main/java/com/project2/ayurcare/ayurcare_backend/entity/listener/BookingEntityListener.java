package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.Booking;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class BookingEntityListener {
	private static CustomIdGenerator idGenerator;

	@Autowired
	public void setIdGenerator(CustomIdGenerator idGenerator) {
		BookingEntityListener.idGenerator = idGenerator;
	}

	@PrePersist
	public void prePersist(Booking booking) {
		if (booking.getBookingId() == null || booking.getBookingId().isEmpty()) {
			booking.setBookingId(idGenerator.generateId("ACB", "Booking", "bookingId"));
		}
	}
}
