package com.project2.ayurcare.ayurcare_backend.Service;

import com.project2.ayurcare.ayurcare_backend.DTO.PatientDTO;
import com.project2.ayurcare.ayurcare_backend.DTO.PaymentDTO;
import com.project2.ayurcare.ayurcare_backend.entity.Category;
import com.project2.ayurcare.ayurcare_backend.entity.Medicaluser;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.repository.CategoryRepository;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalUserRepository;
import com.project2.ayurcare.ayurcare_backend.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class PatientService {

    @Autowired
    private MedicalUserRepository medicalUserRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ModelMapper modelMapperConfig;

    @Autowired
    private PatientRepository patientRepository;



    public PaymentDTO SavePatient(PatientDTO patientDTO) {
        try {
            Optional<Medicaluser> optionalMedicaluser = medicalUserRepository.findById(patientDTO.getMedicaluser());
            if (!optionalMedicaluser.isPresent()) {
                throw new IllegalArgumentException("Medical user not found");
            }

            Optional<Category> optionalCategory = categoryRepository.findById(patientDTO.getCategory());
            if (!optionalCategory.isPresent()) {
                throw new IllegalArgumentException("Chosen category not found");
            }

            Medicaluser medicaluser = optionalMedicaluser.get();
            Category category = optionalCategory.get();

            // Check if there is already a patient with the given medical user ID
            Optional<Patient> existingPatient = patientRepository.findByMedicaluserMedicaluserId(medicaluser.getMedicaluserId());
            if (existingPatient.isPresent()) {
//                throw new IllegalArgumentException("There is already a patient with this medical user ID");
                return null;
            }

            Patient patient = modelMapperConfig.map(patientDTO, Patient.class);
            patient.setCategory(category);
            patient.setUser(medicaluser);

            patientRepository.save(patient);
            // Return appropriate PaymentDTO if needed
            // return new PaymentDTO();

        } catch (Exception e) {
            System.out.println("Error saving patient: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // Adjust return statement as necessary
    }

    public int getPatientCount() {
        return patientRepository.countAllPatients();
    }

    public List<PatientDTO> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDTO> patientDTOs = new ArrayList<>();
        for (Patient patient : patients) {
            PatientDTO patientDTO = modelMapperConfig.map(patient, PatientDTO.class);
            patientDTOs.add(patientDTO);
        }
        return patientDTOs;
    }

    public PatientDTO getPatientByMedicaluserId(String medicaluserId) {
        Optional<Patient> optionalPatient = patientRepository.findByMedicaluserMedicaluserId(medicaluserId);
        if (!optionalPatient.isPresent()) {
            throw new IllegalArgumentException("Patient not found for this medical user ID");
        }

        Patient patient = optionalPatient.get();
        return modelMapperConfig.map(patient, PatientDTO.class);
    }


}
