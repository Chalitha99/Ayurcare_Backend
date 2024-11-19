package com.project2.ayurcare.ayurcare_backend.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;

import jakarta.persistence.LockModeType;

public interface TimeSlotRepository extends JpaRepository<TimeSlot, String> {

	List<TimeSlot> findByCategoryCategoryId(String categoryId);

	// Checking for the timeslots are available for the days
	@Query("SELECT t FROM TimeSlot t WHERE t.date = :date")
	List<TimeSlot> findByDate(@Param("date") LocalDate date);

	// Optimistic Locking - concurrency control
	@Lock(LockModeType.PESSIMISTIC_WRITE)
	@Query("SELECT t FROM TimeSlot t WHERE t.timeSlotId = :timeSlotId")
	Optional<TimeSlot> findByIdWithLock(@Param("timeSlotId") String timeSlotId);

	@Query("SELECT DISTINCT t.date FROM TimeSlot t WHERE t.date > :tomorrow")
	List<LocalDate> findDistinctDatesExceptTodayAndTomorrow(@Param("tomorrow") LocalDate tomorrow);

	@Query("SELECT t FROM TimeSlot t WHERE t.category.categories = :category "
			+ "AND t.date = :date AND t.booked = false")
	List<TimeSlot> findAvailableTimeSlots(@Param("category") String category, @Param("date") LocalDate date);
}
