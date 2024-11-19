package com.project2.ayurcare.ayurcare_backend.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.TimeSlotDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Category;
import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;
import com.project2.ayurcare.ayurcare_backend.repository.CategoryRepository;
import com.project2.ayurcare.ayurcare_backend.repository.TimeSlotRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class TimeSlotService {

	private static final org.slf4j.Logger logger = LoggerFactory.getLogger(TimeSlotService.class);

	@Autowired
	private TimeSlotRepository timeSlotRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private ModelMapper modelMapper;

	public void generateTimeSlotsForWeek() {
		List<Category> categories = categoryRepository.findAll();
		List<TimeSlot> timeSlots = new ArrayList<>();

		for (Category category : categories) {
			for (int i = 0; i < 7; i++) { // 7 days in a week
				LocalDate date = LocalDate.now().plusDays(i);
				if (timeSlotRepository.findByDate(date).isEmpty()) {
					switch (category.getCategories()) {
					case "Meda":
						timeSlots.addAll(generateSlotsForCategory(category, "08:00", "10:30", 30, date));
						break;
					case "Vata":
						timeSlots.addAll(generateSlotsForCategory(category, "10:40", "12:00", 20, date));
						break;
					case "Kapha":
						timeSlots.addAll(generateSlotsForCategory(category, "13:00", "13:30", 10, date));
						break;
					case "Pitta":
						timeSlots.addAll(generateSlotsForCategory(category, "13:30", "14:00", 10, date));
						break;
					case "Rasa":
						timeSlots.addAll(generateSlotsForCategory(category, "14:00", "14:30", 10, date));
						break;
					case "Mamsa":
						timeSlots.addAll(generateSlotsForCategory(category, "14:30", "15:00", 10, date));
						break;
					case "Rakta":
						timeSlots.addAll(generateSlotsForCategory(category, "15:00", "15:30", 10, date));
						break;
					case "Unknown":
						timeSlots.addAll(generateSlotsForCategory(category, "15:30", "16:00", 10, date));
						break;
					default:
						break;
					}
				}
			}
		}

		try {
			timeSlotRepository.saveAll(timeSlots);
			logger.info("Time slots for the week saved successfully");
		} catch (Exception e) {
			logger.error("Error saving time slots", e);
		}
	}

	private List<TimeSlot> generateSlotsForCategory(Category category, String start, String end, int duration,
			LocalDate date) {
		List<TimeSlot> timeSlots = new ArrayList<>();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

		LocalTime startTime = LocalTime.parse(start, formatter);
		LocalTime endTime = LocalTime.parse(end, formatter);

		while (startTime.plusMinutes(duration).isBefore(endTime) || startTime.plusMinutes(duration).equals(endTime)) {
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setCategory(category);
			timeSlot.setStartTime(startTime);
			timeSlot.setEndTime(startTime.plusMinutes(duration));
			timeSlot.setDate(date);
			timeSlot.setBooked(false);

			logger.info("Generated TimeSlot: {} to {} for Category: {} on Date: {}", startTime,
					startTime.plusMinutes(duration), category.getCategoryId(), date);

			timeSlots.add(timeSlot);
			startTime = startTime.plusMinutes(duration);
		}

		return timeSlots;
	}

	public List<TimeSlotDTO> getAllTimeSlots() {
		List<TimeSlot> timeSlots = timeSlotRepository.findAll();
		List<TimeSlotDTO> timeSlotDTOs = new ArrayList<>();
		for (TimeSlot timeSlot : timeSlots) {
			timeSlotDTOs.add(modelMapper.map(timeSlot, TimeSlotDTO.class));
		}
		return timeSlotDTOs;
	}

	// Scheduled method to generate time slots daily
	@Scheduled(cron = "0 0 0 * * ?") // This cron expression: every day at midnight
	public void scheduleTimeSlotGeneration() {
		generateTimeSlotsForWeek();
	}
	
	//geting available dates
	public List<LocalDate> getAllDistinctDatesExceptTodayAndTomorrow() {
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        return timeSlotRepository.findDistinctDatesExceptTodayAndTomorrow(tomorrow);
    }
	
	//getting available timeslots
	public List<TimeSlotDTO> getAvailableTimeSlots(String category, LocalDate date) {
        List<TimeSlot> availableSlots = timeSlotRepository.findAvailableTimeSlots(category, date);
        return availableSlots.stream()
                             .map(timeSlot -> modelMapper.map(timeSlot, TimeSlotDTO.class))
                             .collect(Collectors.toList());
    }
}
