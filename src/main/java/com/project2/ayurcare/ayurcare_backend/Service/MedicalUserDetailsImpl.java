package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MedicalUserDetailsImpl implements UserDetailsService {

    private final MedicalUserRepository repository;

    public MedicalUserDetailsImpl(MedicalUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByMedicaluserEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
