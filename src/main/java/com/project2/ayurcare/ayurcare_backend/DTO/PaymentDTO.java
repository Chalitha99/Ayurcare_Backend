package com.project2.ayurcare.ayurcare_backend.DTO;

public class PaymentDTO {

    private String paymentId;
    private String razorpayOrderId;
    private String razorpaySignature;
    private String razorpayPaymentId;
    private String medicaluserId;

    public PaymentDTO() {}

    public PaymentDTO(String paymentId, String razorpayOrderId, String razorpaySignature, String razorpayPaymentId, String medicaluserId) {
        this.paymentId = paymentId;
        this.razorpayOrderId = razorpayOrderId;
        this.razorpaySignature = razorpaySignature;
        this.razorpayPaymentId = razorpayPaymentId;
        this.medicaluserId = medicaluserId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }

    public String getRazorpaySignature() {
        return razorpaySignature;
    }

    public void setRazorpaySignature(String razorpaySignature) {
        this.razorpaySignature = razorpaySignature;
    }

    public String getRazorpayPaymentId() {
        return razorpayPaymentId;
    }

    public void setRazorpayPaymentId(String razorpayPaymentId) {
        this.razorpayPaymentId = razorpayPaymentId;
    }

    public String getMedicaluserId() {
        return medicaluserId;
    }

    public void setMedicaluserId(String medicaluserId) {
        this.medicaluserId = medicaluserId;
    }
}
