package com.project2.ayurcare.ayurcare_backend.entity;

import com.project2.ayurcare.ayurcare_backend.entity.listener.MedicineProductEntityListner;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "MedicineProducts")
@EntityListeners(MedicineProductEntityListner.class)

    public class MedicineProducts {

        @Id
        @Column(name = "productId", nullable = false, length = 100)
        private String productId;

        @Column(name = "productName", nullable = false, length = 100)
        private String productName;

        @Column(name = "productType", nullable = false, length = 100)
        private String productType;

        @Column(name = "productUnits", nullable = false, length = 100)
        private int productUnits;

        @Column(name = "lastUpdate", nullable = false, length = 100)
        @Temporal(TemporalType.DATE)
        private Date lastUpdate;

    public MedicineProducts() {
    }

    public MedicineProducts(String productId, String productName, String productType, int productUnits, Date lastUpdate) {
        this.productId = productId;
        this.productName = productName;
        this.productType = productType;
        this.productUnits = productUnits;
        this.lastUpdate = lastUpdate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public int getProductUnits() {
        return productUnits;
    }

    public void setProductUnits(int productUnits) {
        this.productUnits = productUnits;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
