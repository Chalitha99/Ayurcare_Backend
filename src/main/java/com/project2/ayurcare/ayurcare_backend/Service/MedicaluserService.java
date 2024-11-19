//package com.project2.ayurcare.ayurcare_backend.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.project2.ayurcare.ayurcare_backend.entity.Patient;
//import com.project2.ayurcare.ayurcare_backend.entity.Token;
//import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
//import com.project2.ayurcare.ayurcare_backend.repository.TokenRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
//import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
//import com.project2.ayurcare.ayurcare_backend.exceptions.ResourceNotFoundException;
//import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
//
//@Service
//public class MedicaluserService {
//
//    @Autowired
//    private MedicalUserRepository medicalUserRepository;
//
//    @Autowired
//    private ModelMapper modelMapperConfig;
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//
//    public List<MedicaluserDTO> getAllUsers() {
//        return medicalUserRepository.findAll().stream()
//                .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
//                .collect(Collectors.toList());
//    }
//
//
//
//    public MedicaluserDTO getUserById(String medicalId) {
//        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//    }
//
//
//
//    public MedicaluserDTO getUserByEmail(String medicaluserEmail) {
//        Medicaluser medicaluser = medicalUserRepository.getUserByEmail(medicaluserEmail);
//        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//    }
//
//
//
//    public MedicaluserDTO createUser(MedicaluserDTO medicaluserDTO) {
//        Medicaluser medicaluser = modelMapperConfig.map(medicaluserDTO, Medicaluser.class);
//        // Ensure the ID is null for new entries
//        medicaluser.setMedicaluserId(null); // Corrected method name
//        Medicaluser savedUser = medicalUserRepository.save(medicaluser);
//        return modelMapperConfig.map(savedUser, MedicaluserDTO.class);
//    }
//
//
//
//    public MedicaluserDTO updateUser(String medicalId, MedicaluserDTO medicaluserDTO) {
//        Medicaluser existingUser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//
//        // Update only the fields of the existing user without changing the ID
//        existingUser.setMedicaluserFirstname(medicaluserDTO.getMedicaluserFirstname());
//        existingUser.setMedicaluserLastname(medicaluserDTO.getMedicaluserLastname());
//        existingUser.setMedicaluserEmail(medicaluserDTO.getMedicaluserEmail());
//        existingUser.setMedicaluserPhoneno(medicaluserDTO.getMedicaluserPhoneno());
//        existingUser.setMedicaluserPhoto(medicaluserDTO.getMedicaluserPhoto());
//        existingUser.setMedicaluserAddress(medicaluserDTO.getMedicaluserAddress());
//        existingUser.setMedicaluserIntreatment(medicaluserDTO.getMedicaluserIntreatment());
//
//        // Save the updated user
//        Medicaluser updatedUser = medicalUserRepository.save(existingUser);
//        return modelMapperConfig.map(updatedUser, MedicaluserDTO.class);
//    }
//
//
//    public void deleteUser(String medicalId) {
//        // Find the Medicaluser entity
//        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//
//        // Delete associated tokens
//        List<Token> tokens = tokenRepository.findAllTokenByUser(medicalId);
//        if (tokens != null && !tokens.isEmpty()) {
//            tokenRepository.deleteAll(tokens);
//        }
//
//        // Delete the user
//        medicalUserRepository.delete(medicaluser);
//    }
//
//    // Geting the all registered useres whose role == USER
//
//    public int registeredPatientCount(){
//        try {
//            return medicalUserRepository.countUsersByRole();
//        }catch (Exception e){
//            throw new ResourceNotFoundException("Patient count not found");
//        }
//    }
//
//    public List<MedicaluserDTO> lastFiveUsers() throws Exception {
//        try{List<Medicaluser> medicalusers = medicalUserRepository.findLast5UsersByRole();
//            return medicalusers.stream()
//                    .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
//                    .collect(Collectors.toList());
//        }catch (Exception e){
//            throw new Exception(e.getMessage());
//        }
//
//    }
//
//
//    public MedicaluserDTO getMedicaluserByPatientId(String patientId) {
//        Patient patient = patientRepository.findByPatientId(patientId);
//        if (patient != null) {
//            Medicaluser medicaluser = patient.getUser(); // Retrieves Medicaluser from Patient
//            MedicaluserDTO medicaluserDTO = modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//            return medicaluserDTO;
//        } else {
//            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
//        }
//    }
//}




//package com.project2.ayurcare.ayurcare_backend.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
//import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
//import com.project2.ayurcare.ayurcare_backend.entity.Patient;
//import com.project2.ayurcare.ayurcare_backend.entity.Token;
//import com.project2.ayurcare.ayurcare_backend.exceptions.ResourceNotFoundException;
//import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
//import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
//import com.project2.ayurcare.ayurcare_backend.repository.TokenRepository;
//import org.modelmapper.ModelMapper;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class MedicaluserService {
//
//    @Autowired
//    private MedicalUserRepository medicalUserRepository;
//
//    @Autowired
//    private ModelMapper modelMapperConfig;
//
//    @Autowired
//    private TokenRepository tokenRepository;
//
//    @Autowired
//    private PatientRepository patientRepository;
//
//    public List<MedicaluserDTO> getAllUsers() {
//        return medicalUserRepository.findAll().stream()
//                .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
//                .collect(Collectors.toList());
//    }
//
//    public MedicaluserDTO getUserById(String medicalId) {
//        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//    }
//
//    public MedicaluserDTO getUserByEmail(String medicaluserEmail) {
//        Medicaluser medicaluser = medicalUserRepository.getUserByEmail(medicaluserEmail);
//        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//    }
//
//    public MedicaluserDTO createUser(MedicaluserDTO medicaluserDTO) {
//        Medicaluser medicaluser = modelMapperConfig.map(medicaluserDTO, Medicaluser.class);
//        medicaluser.setMedicaluserId(null); // Ensure the ID is null for new entries
//        Medicaluser savedUser = medicalUserRepository.save(medicaluser);
//        return modelMapperConfig.map(savedUser, MedicaluserDTO.class);
//    }
//
//    public MedicaluserDTO updateUser(String medicalId, MedicaluserDTO medicaluserDTO) {
//        Medicaluser existingUser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//
//        existingUser.setMedicaluserFirstname(medicaluserDTO.getMedicaluserFirstname());
//        existingUser.setMedicaluserLastname(medicaluserDTO.getMedicaluserLastname());
//        existingUser.setMedicaluserEmail(medicaluserDTO.getMedicaluserEmail());
//        existingUser.setMedicaluserPhoneno(medicaluserDTO.getMedicaluserPhoneno());
//        existingUser.setMedicaluserPhoto(medicaluserDTO.getMedicaluserPhoto());
//        existingUser.setMedicaluserAddress(medicaluserDTO.getMedicaluserAddress());
//        existingUser.setMedicaluserIntreatment(medicaluserDTO.getMedicaluserIntreatment());
//
//        Medicaluser updatedUser = medicalUserRepository.save(existingUser);
//        return modelMapperConfig.map(updatedUser, MedicaluserDTO.class);
//    }
//
//    public void deleteUser(String medicalId) {
//        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
//                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));
//
//        // Soft delete: Set isDeleted flag to true
//        medicaluser.setDeleted(true);
//        medicalUserRepository.save(medicaluser);
//    }
//
//    public int registeredPatientCount() {
//        try {
//            return medicalUserRepository.countUsersByRole();
//        } catch (Exception e) {
//            throw new ResourceNotFoundException("Patient count not found");
//        }
//    }
//
//    public List<MedicaluserDTO> lastFiveUsers() throws Exception {
//        try {
//            List<Medicaluser> medicalusers = medicalUserRepository.findLast5UsersByRole();
//            return medicalusers.stream()
//                    .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
//                    .collect(Collectors.toList());
//        } catch (Exception e) {
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public MedicaluserDTO getMedicaluserByPatientId(String patientId) {
//        Patient patient = patientRepository.findByPatientId(patientId);
//        if (patient != null) {
//            Medicaluser medicaluser = patient.getUser(); // Retrieves Medicaluser from Patient
//            MedicaluserDTO medicaluserDTO = modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
//            return medicaluserDTO;
//        } else {
//            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
//        }
//    }
//}




package com.project2.ayurcare.ayurcare_backend.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.project2.ayurcare.ayurcare_backend.DTO.MedicaluserDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.entity.Token;
import com.project2.ayurcare.ayurcare_backend.exceptions.ResourceNotFoundException;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
import com.project2.ayurcare.ayurcare_backend.repository.TokenRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedicaluserService {

    @Autowired
    private MedicalUserRepository medicalUserRepository;

    @Autowired
    private ModelMapper modelMapperConfig;

    @Autowired
    private TokenRepository tokenRepository;

    @Autowired
    private PatientRepository patientRepository;

    // Return only non-deleted users
    public List<MedicaluserDTO> getAllUsers() {
        return medicalUserRepository.findAll().stream()
                .filter(user -> !user.isDeleted()) // Ensure only non-deleted users are returned
                .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
                .collect(Collectors.toList());
    }

    // Retrieve user by ID, but check if deleted
    public MedicaluserDTO getUserById(String medicalId) {
        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));

        // Check if the user is soft-deleted
        if (medicaluser.isDeleted()) {
            throw new ResourceNotFoundException("Medical user with id: " + medicalId + " is deleted.");
        }

        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
    }

    // Get user by email, but check if deleted
    public MedicaluserDTO getUserByEmail(String medicaluserEmail) {
        Medicaluser medicaluser = medicalUserRepository.getUserByEmail(medicaluserEmail);

        if (medicaluser == null || medicaluser.isDeleted()) {
            throw new ResourceNotFoundException("Medical user with email: " + medicaluserEmail + " not found or deleted.");
        }

        return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
    }

    // Create new user
    public MedicaluserDTO createUser(MedicaluserDTO medicaluserDTO) {
        Medicaluser medicaluser = modelMapperConfig.map(medicaluserDTO, Medicaluser.class);
        medicaluser.setMedicaluserId(null); // Ensure the ID is null for new entries
        medicaluser.setDeleted(false); // Ensure new user is not deleted
        Medicaluser savedUser = medicalUserRepository.save(medicaluser);
        return modelMapperConfig.map(savedUser, MedicaluserDTO.class);
    }

    // Update existing user but check if the user is deleted before updating
    public MedicaluserDTO updateUser(String medicalId, MedicaluserDTO medicaluserDTO) {
        Medicaluser existingUser = medicalUserRepository.findById(medicalId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));

        // Check if the user is soft-deleted
        if (existingUser.isDeleted()) {
            throw new ResourceNotFoundException("Cannot update deleted user with id: " + medicalId);
        }

        existingUser.setMedicaluserFirstname(medicaluserDTO.getMedicaluserFirstname());
        existingUser.setMedicaluserLastname(medicaluserDTO.getMedicaluserLastname());
        existingUser.setMedicaluserEmail(medicaluserDTO.getMedicaluserEmail());
        existingUser.setMedicaluserPhoneno(medicaluserDTO.getMedicaluserPhoneno());
        existingUser.setMedicaluserPhoto(medicaluserDTO.getMedicaluserPhoto());
        existingUser.setMedicaluserAddress(medicaluserDTO.getMedicaluserAddress());
        existingUser.setMedicaluserIntreatment(medicaluserDTO.getMedicaluserIntreatment());

        Medicaluser updatedUser = medicalUserRepository.save(existingUser);
        return modelMapperConfig.map(updatedUser, MedicaluserDTO.class);
    }

    // Soft delete user
    public void deleteUser(String medicalId) {
        Medicaluser medicaluser = medicalUserRepository.findById(medicalId)
                .orElseThrow(() -> new ResourceNotFoundException("Medical user not found with id: " + medicalId));

        // Soft delete: Set isDeleted flag to true
        medicaluser.setDeleted(true);
        medicalUserRepository.save(medicaluser);
    }

    // Count only non-deleted patients with role USER
    public int registeredPatientCount() {
        try {
            return medicalUserRepository.countUsersByRole();
        } catch (Exception e) {
            throw new ResourceNotFoundException("Patient count not found");
        }
    }

    // Return the last 5 non-deleted users with role USER
    public List<MedicaluserDTO> lastFiveUsers() throws Exception {
        try {
            List<Medicaluser> medicalusers = medicalUserRepository.findLast5UsersByRole();
            return medicalusers.stream()
                    .map(user -> modelMapperConfig.map(user, MedicaluserDTO.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // Get medical user based on the patientId
    public MedicaluserDTO getMedicaluserByPatientId(String patientId) {
        Patient patient = patientRepository.findByPatientId(patientId);
        if (patient != null) {
            Medicaluser medicaluser = patient.getUser(); // Retrieves Medicaluser from Patient

            // Check if the associated medical user is deleted
            if (medicaluser == null || medicaluser.isDeleted()) {
                throw new ResourceNotFoundException("Associated medical user for patient id: " + patientId + " is not found or deleted.");
            }

            return modelMapperConfig.map(medicaluser, MedicaluserDTO.class);
        } else {
            throw new ResourceNotFoundException("Patient not found with id: " + patientId);
        }
    }
}
