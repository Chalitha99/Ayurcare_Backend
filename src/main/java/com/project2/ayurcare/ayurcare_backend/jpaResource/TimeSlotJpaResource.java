package com.project2.ayurcare.ayurcare_backend.jpaResource;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project2.ayurcare.ayurcare_backend.DTO.TimeSlotDTO;
import com.project2.ayurcare.ayurcare_backend.Service.TimeSlotService;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotJpaResource {

	@Autowired
    private TimeSlotService timeSlotService;

    @PostMapping("/generate")
    public ResponseEntity<Void> generateTimeSlots() {
        timeSlotService.generateTimeSlotsForWeek();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/timeslotretrieve")
    public ResponseEntity<List<TimeSlotDTO>> getAllTimeSlots() {
        List<TimeSlotDTO> timeSlotDTOs = timeSlotService.getAllTimeSlots();
        return ResponseEntity.ok(timeSlotDTOs);
    }
    
    @GetMapping("/dates")
    public ResponseEntity<List<LocalDate>> getAllDistinctDatesExceptTodayAndTomorrow() {
        List<LocalDate> dates = timeSlotService.getAllDistinctDatesExceptTodayAndTomorrow();
        return ResponseEntity.ok(dates);
    }
    
    @GetMapping("/available/{category}/{date}")
    public List<TimeSlotDTO> getAvailableTimeSlots(@PathVariable String category, @PathVariable String date) {
        LocalDate parsedDate = LocalDate.parse(date); // Parse date string to LocalDate
        return timeSlotService.getAvailableTimeSlots(category, parsedDate);
    }
}
