package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.ChannellingDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Channelling;
import com.project2.ayurcare.ayurcare_backend.repository.ChannellingRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ChannellingSerice {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ChannellingRepository channellingRepository;

    public ChannellingDTO getChannelling(int channellingId) {
        Optional<Channelling> channelling = channellingRepository.findById(channellingId);
        if (channelling.isPresent()) {
            return modelMapper.map(channelling.get(), ChannellingDTO.class);
        } else {
            // Handle case where channelling ID is not found
            throw new RuntimeException("Channelling not found with ID: " + channellingId);
        }
    }

    // Update Channelling fee by ID
    public ChannellingDTO updateChannellingFee(int channellingId, String fee) {
        Optional<Channelling> channellingOptional = channellingRepository.findById(channellingId);
        if (channellingOptional.isPresent()) {
            Channelling channelling = channellingOptional.get();
            channelling.setFee(fee);
            channellingRepository.save(channelling);
            return modelMapper.map(channelling, ChannellingDTO.class);
        } else {
            throw new RuntimeException("Channelling not found with ID: " + channellingId);
        }
    }
}
