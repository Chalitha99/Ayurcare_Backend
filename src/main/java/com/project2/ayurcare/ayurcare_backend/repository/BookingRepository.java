package com.project2.ayurcare.ayurcare_backend.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project2.ayurcare.ayurcare_backend.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, String>{

	@Query("SELECT b FROM Booking b JOIN b.timeSlotId t WHERE b.patient.patientId = :patientId AND t.date = :date")
    List<Booking> findByPatientIdAndDate(@Param("patientId") String patientId, @Param("date") LocalDate date);
	//use jpql query for operate on the entity objects

    @Query("SELECT b FROM Booking b WHERE b.patient.patientId = :patientId AND b.bookingDate BETWEEN :startOfDay AND :endOfDay")
    List<Booking> findByPatientIdAndDate(@Param("patientId") String patientId, @Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);

    @Query("SELECT b.bookingId AS bookingId, " +
            "b.status AS status," +
            "t.timeSlotId AS timeSlotId, " +
            "p.medicaluser.medicaluserFirstname AS patientFirstName, " +
            "p.medicaluser.medicaluserLastname AS patientLastName, " +
            "t.startTime AS timeSlotStartTime, " +
            "t.endTime AS timeSlotEndTime, " +
            "c.categories AS categoryName " +
            "FROM Booking b " +
            "JOIN b.patient p " +
            "JOIN p.category c " +
            "JOIN b.timeSlotId t " +
            "WHERE t.date = :givenDate")
    List<Object[]> findPatientDetailsForDate(@Param("givenDate") LocalDate givenDate);

    @Query("SELECT b FROM Booking b " +
            "JOIN b.timeSlotId t " +
            "WHERE b.patient.patientId = :patientId " +
            "AND t.date >= CURRENT_DATE " +
            "ORDER BY t.date ASC")
    List<Booking> findUpcomingBookingsByPatientId(@Param("patientId") String patientId);


}
