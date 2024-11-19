package com.project2.ayurcare.ayurcare_backend.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.project2.ayurcare.ayurcare_backend.DTO.BookingDetalsDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.BookingDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.PatientDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Booking;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;
import com.project2.ayurcare.ayurcare_backend.repository.BookingRepository;
import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
import com.project2.ayurcare.ayurcare_backend.repository.TimeSlotRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookingSerrvice {

    @Autowired
    BookingRepository bookingRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ModelMapper modelMapper;


    public BookingDTO appointment(BookingDTO bookingDTO) {
        // Lock the time slot to prevent concurrent access
        TimeSlot timeSlot = timeSlotRepository.findByIdWithLock(bookingDTO.getTimeSlotId())
                .orElseThrow(() -> new RuntimeException("Time slot not found"));

        // Check if the time slot is already booked
        if (timeSlot.isBooked()) {
            throw new RuntimeException("Time slot already booked");
        }

        // Check if the patient has already booked a time slot on the same date
        LocalDate bookingDate = timeSlot.getDate();
        List<Booking> existingBookings = bookingRepository.findByPatientIdAndDate(bookingDTO.getPatientId(), bookingDate);

        if (!existingBookings.isEmpty()) {
            throw new RuntimeException("Patient has already booked a time slot on this date");
        }

        // Mark the time slot as booked
        timeSlot.setBooked(true);
        timeSlotRepository.save(timeSlot);

        // Create the booking
        Booking createBooking = modelMapper.map(bookingDTO, Booking.class);

        // Set default status if not provided
        if (createBooking.getStatus() == null) {
            createBooking.setStatus("Booked");
        }

        createBooking.setTimeSlotId(timeSlot);
        Booking booking = bookingRepository.save(createBooking);

        // Return the booking DTO
        return modelMapper.map(booking, BookingDTO.class);
    }


    public PatientDTO retrievePatientByMedicaluserId(String medicaluserId) {
        // Find the patient by the medicaluserId
        Optional<Patient> patientOpt = patientRepository.findByMedicaluserMedicaluserId(medicaluserId);

        if (patientOpt.isPresent()) {
            return modelMapper.map(patientOpt.get(), PatientDTO.class);
        } else {
            throw new RuntimeException("Patient not found for medical user ID: " + medicaluserId);
        }
    }

    public List<BookingDetalsDTO> retrieveBookingDetailsForDate(LocalDate date) {
        List<Object[]> details = bookingRepository.findPatientDetailsForDate(date);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

        return details.stream().map(detail -> new BookingDetalsDTO(
                (String) detail[0],
                (String) detail[1],
                (String) detail[2],
                (String) detail[3],
                (String) detail[4],
                ((LocalTime) detail[5]).toString(),
                ((LocalTime) detail[6]).toString(),
                (String) detail[7]
        )).collect(Collectors.toList());
    }

    public String updateBookingStatus(String bookingId, BookingDTO bookingDTO) {
        try {
            Optional<Booking> bookingOpt = bookingRepository.findById(bookingId);
            if (!bookingOpt.isPresent()) {
                throw new RuntimeException("Booking not found");
            }

            Booking booking = bookingOpt.get();
            booking.setStatus(bookingDTO.getStatus());
            bookingRepository.save(booking);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "Successfully updated booking status";
    }


    public BookingDTO getMostRecentBookingByPatientId(String patientId) {
        List<Booking> upcomingBookings = bookingRepository.findUpcomingBookingsByPatientId(patientId);

        if (upcomingBookings.isEmpty()) {
            throw new RuntimeException("No upcoming bookings found for patient ID: " + patientId);
        }

        // Get the first booking which will be the nearest upcoming booking
        Booking recentBooking = upcomingBookings.get(0);
        return modelMapper.map(recentBooking, BookingDTO.class);
    }


}
