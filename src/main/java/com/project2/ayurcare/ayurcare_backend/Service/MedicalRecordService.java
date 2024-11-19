package com.project2.ayurcare.ayurcare_backend.Service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper; // Import ModelMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.project2.ayurcare.ayurcare_backend.DTO.MedicalRecordDTO;
import com.project2.ayurcare.ayurcare_backend.buckets.BucketName;
import com.project2.ayurcare.ayurcare_backend.entity.MedicalRecord;
import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.repository.MedicalRecordRepository;

@Service
public class MedicalRecordService {
    
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AmazonS3 s3;

    @Autowired
    private ModelMapper modelMapper; // Inject ModelMapper
    

    public List<MedicalRecordDTO> getMedicalRecordsForUser(String medicaluserId) {
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatient_Medicaluser_MedicaluserId(medicaluserId);
        return medicalRecords.stream()
                .map(record -> modelMapper.map(record, MedicalRecordDTO.class))
                .collect(Collectors.toList());
    }

    public void save(String path, String fileName, Optional<Map<String, String>> optionalMetadata, byte[] bytes) {
        ObjectMetadata metaData = new ObjectMetadata();
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metaData::addUserMetadata);
            }
        });
        try (ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes)) {
            s3.putObject(path, fileName, inputStream, metaData);
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to store image to S3", e);
        }
    }


    //Upload prescription to AWS s3 bucket
    public void uploadUserMedicalRecord(String patientId, MultipartFile file) {
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file [" + file.getSize() + "]");
        }

        if (!file.getContentType().startsWith("image")) {
            throw new IllegalStateException("File must be an image [" + file.getContentType() + "]");
        }

        MedicalRecord medicalRecord = new MedicalRecord();
        medicalRecord.setPatient(new Patient(patientId));
        medicalRecord.setPrescriptionIssueDate(LocalDateTime.now()); // Use LocalDateTime.now()

        medicalRecordRepository.save(medicalRecord);

        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));

        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), patientId);
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        try {
            save(path, filename, Optional.of(metadata), file.getBytes());
            medicalRecord.setMedicalRecord(filename);
            medicalRecordRepository.save(medicalRecord);
            
            System.out.println("File uploaded successfully to path: " + path + " with filename: " + filename);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
    //Download prescription from AWS s3 bucket
    public byte[] downloadUserMedicalRecord(String patientId) {
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), patientId);

        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatient_PatientIdOrderByPrescriptionIssueDateDesc(patientId);

        if (medicalRecords.isEmpty()) {
            throw new IllegalStateException("No medical records found for patientId: " + patientId);
        }

        MedicalRecord medicalRecord = medicalRecords.get(0);
        String medicalRecordFilename = medicalRecord.getMedicalRecord();

        if (medicalRecordFilename != null) {
            System.out.println("Attempting to download file from path: " + path + " with filename: " + medicalRecordFilename);
            return download(path, medicalRecordFilename);
        } else {
            throw new IllegalStateException("No file associated with the medical record for patientId: " + patientId);
        }
    }
    
    public byte[] downloadUserMedicalRecordwithNameOfTheImage(String patientId, String medicalRecordFilename) {
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), patientId);

        // Find medical records for the given patientId
        List<MedicalRecord> medicalRecords = medicalRecordRepository.findByPatient_PatientIdOrderByPrescriptionIssueDateDesc(patientId);

        if (medicalRecords.isEmpty()) {
            throw new IllegalStateException("No medical records found for patientId: " + patientId);
        }

        // Check if any medical record has the given filename
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecordFilename.equals(medicalRecord.getMedicalRecord())) {
                System.out.println("Attempting to download file from path: " + path + " with filename: " + medicalRecordFilename);
                return download(path, medicalRecordFilename);
            }
        }

        // If no record is found with the given filename, throw an exception
        throw new IllegalStateException("No file associated with the medical record filename: " + medicalRecordFilename + " for patientId: " + patientId);
    }

    private byte[] download(String path, String key) {
        try {
            S3Object object = s3.getObject(path, key);
            return IOUtils.toByteArray(object.getObjectContent());
        } catch (AmazonServiceException | IOException e) {
            // Log the exception
            System.err.println("Failed to download file from path: " + path + " with key: " + key);
            throw new IllegalStateException("Failed to download file from S3", e);
        }
    }

}
