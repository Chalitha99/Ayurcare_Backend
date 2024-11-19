package com.project2.ayurcare.ayurcare_backend.entity.listener;

import com.project2.ayurcare.ayurcare_backend.entity.MedicineProducts;
import com.project2.ayurcare.ayurcare_backend.util.CustomIdGenerator;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MedicineProductEntityListner {

    private static CustomIdGenerator idGenerator;

    @Autowired
    public void setIdGenerator(CustomIdGenerator idGenerator) {
        this.idGenerator = idGenerator;
    }

    @PrePersist
    public void prePersist(MedicineProducts medicineProducts) {
        if (medicineProducts.getProductId() == null || medicineProducts.getProductId().isEmpty()) {
            medicineProducts.setProductId(idGenerator.generateId("ACMP", "MedicineProducts", "productId"));
        }


    }
}
