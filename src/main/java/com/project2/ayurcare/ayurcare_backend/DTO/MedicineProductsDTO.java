package com.project2.ayurcare.ayurcare_backend.DTO;



import java.sql.Date;

public class MedicineProductsDTO {


    private String productId;
    private String productName;
    private String productType;
    private int productUnits;
    private Date lastUpdate;

    public MedicineProductsDTO() {
    }

    public MedicineProductsDTO(String productId, String productName, String productType, int productUnits, Date lastUpdate) {
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
