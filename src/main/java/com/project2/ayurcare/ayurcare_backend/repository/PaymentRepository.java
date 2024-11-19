package com.project2.ayurcare.ayurcare_backend.repository;

import com.project2.ayurcare.ayurcare_backend.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PaymentRepository extends JpaRepository<Payment, String> {

    @Query("SELECT COUNT(p) FROM Payment p")
    int countAllPayment();

}
