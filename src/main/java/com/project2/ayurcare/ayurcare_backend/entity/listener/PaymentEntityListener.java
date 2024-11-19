package com.project2.ayurcare.ayurcare_backend.entity.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project2.ayurcare.ayurcare_backend.entity.Payment;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;

import jakarta.persistence.PrePersist;

@Component
public class PaymentEntityListener {
    private static CustomIdGenerator idGenerator;

    @Autowired
    public void setIdGenerator(CustomIdGenerator idGenerator) {
        PaymentEntityListener.idGenerator = idGenerator;
    }

    @PrePersist
    public void prePersist(Payment payment) {
        if (payment.getPaymentId() == null || payment.getPaymentId().isEmpty()) {
            payment.setPaymentId(idGenerator.generateId("ACP", "Payment", "paymentId"));
        }
    }
}
