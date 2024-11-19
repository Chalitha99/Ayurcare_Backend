package com.project2.ayurcare.ayurcare_backend.repository;

import com.project2.ayurcare.ayurcare_backend.entity.Patient;
import com.project2.ayurcare.ayurcare_backend.entity.TimeSlot;
import com.project2.ayurcare.ayurcare_backend.entity.VideoCall;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface VideoCallRepository extends JpaRepository<VideoCall, Integer> {

    @Query("SELECT m.medicaluserId AS medicaluserId, " +
            "p.patientId AS patientId, " +
            "vc.videoCallId AS videoCallId, " +
            "CONCAT(m.medicaluserFirstname, ' ', m.medicaluserLastname) AS patientName, " +
            "ts.startTime AS startTime, " +
            "ts.endTime AS endTime, " +
            "c.categories AS category, " +
            "vc.status AS status " +
            "FROM VideoCall vc " +
            "JOIN vc.timeSlot ts " +
            "JOIN vc.patient p " +
            "JOIN p.medicaluser m " +
            "JOIN ts.category c " +
            "WHERE ts.date = :date")
    List<Object[]> findVideoCallsByDate(@Param("date") LocalDate date);


}
