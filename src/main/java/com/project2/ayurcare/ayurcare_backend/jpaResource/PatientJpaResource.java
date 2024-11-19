package com.project2.ayurcare.ayurcare_backend.jpaResource;

import com.project2.ayurcare.ayurcare_backend.DTO.PatientDTO;
import com.project2.ayurcare.ayurcare_backend.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientJpaResource {

    @Autowired
    private PatientService patientService;

    @PostMapping("/patient/addpatient")
    public void addPatient(@RequestBody PatientDTO patientDTO) {
        patientService.SavePatient(patientDTO);
    }

    @GetMapping("patient/count")
    public int getPatientCount() {
        return patientService.getPatientCount();
    }

    @GetMapping("patient/allpatients")
    public List<PatientDTO> getPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/patient/by-medicaluser/{medicaluserId}")
    public PatientDTO getPatientByMedicaluserId(@PathVariable String medicaluserId) {
        return patientService.getPatientByMedicaluserId(medicaluserId);
    }

}
