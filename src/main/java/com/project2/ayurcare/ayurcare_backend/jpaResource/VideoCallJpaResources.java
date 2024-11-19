package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.DTO.VideoCallDTO;
import com.project2.ayurcare.ayurcare_backend.Service.VideoCallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/videocall")
public class VideoCallJpaResources {

    @Autowired
    private VideoCallService videoCallService;

    @GetMapping("/by-date/{date}")
    public List<VideoCallDTO> getVideoCallsByDate(@PathVariable("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date); // Parse the date from the URL path
        return videoCallService.getVideoCallsByDate(parsedDate);
    }

    @GetMapping("/update-status/{id}/{status}")
    public void updateStatus(@PathVariable("id") Integer id, @PathVariable("status") String status) {
        videoCallService.updateVideoCallStatus(id, status);
    }

    @PostMapping("/create")
    public VideoCallDTO createVideoCall(@RequestBody VideoCallDTO videoCallDTO) {
        return videoCallService.createVideoCall(videoCallDTO);
    }


}
