package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.VideoCallDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;
import com.project2.ayurcare.ayurcare_backend.entity.VideoCall;
import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
import com.project2.ayurcare.ayurcare_backend.repository.TimeSlotRepository;
import com.project2.ayurcare.ayurcare_backend.repository.VideoCallRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VideoCallService {

    @Autowired
    VideoCallRepository videoCallRepository;

    @Autowired
    TimeSlotRepository timeSlotRepository;

    @Autowired
    PatientRepository patientRepository;

    @Autowired
    ModelMapper modelMapper;
    

    public List<VideoCallDTO> getVideoCallsByDate(LocalDate date) {
        // Retrieve data from the repository
        List<Object[]> results = videoCallRepository.findVideoCallsByDate(date);

        // Map results to DTOs
        return results.stream().map(result -> {
            VideoCallDTO dto = new VideoCallDTO();
            dto.setMedicaluserId((String) result[0]);
            dto.setPatientId((String) result[1]);
            dto.setVideoCallId((Integer) result[2]);
            dto.setPatientName((String) result[3]);
            dto.setStartTime((LocalTime) result[4]);
            dto.setEndTime((LocalTime) result[5]);
            dto.setCategory((String) result[6]);
            dto.setStatus((String) result[7]);
            return dto;
        }).collect(Collectors.toList());
    }

    public void updateVideoCallStatus(Integer videoCallId, String status) {
        VideoCall videoCall = videoCallRepository.findById(videoCallId)
                .orElseThrow(() -> new RuntimeException("VideoCall not found with id: " + videoCallId));
        videoCall.setStatus(status);
        videoCallRepository.save(videoCall);
    }

    public VideoCallDTO createVideoCall(VideoCallDTO videoCallDTO) {
        // Fetch TimeSlot by ID
        TimeSlot timeSlot = timeSlotRepository.findById(videoCallDTO.getTimeSlotId())
                .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + videoCallDTO.getTimeSlotId()));

        // Fetch Patient by ID
        Patient patient = patientRepository.findById(videoCallDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + videoCallDTO.getPatientId()));

        // Map DTO to VideoCall entity
        VideoCall videoCall = modelMapper.map(videoCallDTO, VideoCall.class);

        // Set the fetched TimeSlot and Patient entities
        videoCall.setTimeSlot(timeSlot);
        videoCall.setPatient(patient);

        // Save the VideoCall entity to the repository
        videoCall = videoCallRepository.save(videoCall);

        // Map the saved VideoCall back to a DTO and return
        return modelMapper.map(videoCall, VideoCallDTO.class);
    }



}
