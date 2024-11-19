package com.project2.ayurcare.ayurcare_backend.jpaResource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.project2.ayurcare.ayurcare_backend.DTO.MedicalRecordDTO;
import com.project2.ayurcare.ayurcare_backend.Service.MedicalRecordService;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class MedicalRecordJpaResource {
	
	@Autowired
    private MedicalRecordService medicalRecordService;

    @GetMapping("/{medicaluserId}/medical-records")
    public List<MedicalRecordDTO> retrieveMedicalRecordsForUser(@PathVariable String medicaluserId) {
        return medicalRecordService.getMedicalRecordsForUser(medicaluserId);
    }
    
    @PostMapping("{patientId}/image/upload")
    public void uploadUserProfileImage(@PathVariable String patientId, @RequestParam("file") MultipartFile file) {
        medicalRecordService.uploadUserMedicalRecord(patientId, file);
    }

    @GetMapping("{patientId}/image/download")
    public byte[] downloadUserMedicalRecord(@PathVariable String patientId) {
        return medicalRecordService.downloadUserMedicalRecord(patientId);
    }
    
    @GetMapping("/{patientId}/{medicalRecord}/image/download")
    public byte[] downloadUserMedicalRecordWithImageName(@PathVariable String patientId, @PathVariable String medicalRecord) {
        return medicalRecordService.downloadUserMedicalRecordwithNameOfTheImage(patientId, medicalRecord);
    }
}
