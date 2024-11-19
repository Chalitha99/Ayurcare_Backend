package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.DTO.ChannellingDTO;
import com.project2.ayurcare.ayurcare_backend.Service.ChannellingSerice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/channelling")
public class ChannellingJpaResources {

    @Autowired
    ChannellingSerice channellingSerice;

    @GetMapping("/fee/{id}")
    public ChannellingDTO getChannellingById(@PathVariable("id") int id) {
        return channellingSerice.getChannelling(id);
    }

    // Update channelling fee by ID
    @PutMapping("/fee/{id}")
    public ChannellingDTO updateChannellingFee(@PathVariable("id") int id, @RequestBody Map<String, String> requestBody) {
        String fee = requestBody.get("fee");  // Extract the fee value from the request body
        return channellingSerice.updateChannellingFee(id, fee);
    }

}
