package com.project2.ayurcare.ayurcare_backend.entity;

import java.sql.Date;

import com.project2.ayurcare.ayurcare_backend.entity.listener.PaymentEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "payment")
@EntityListeners(PaymentEntityListener.class)
public class Payment {

	@Id
	@Column(name = "payment_id")
	private String paymentId;

	@Column(name="razor_id")
	private String razorpayOrderId;

	@Column(name = "razor_signature")
	private String razorpaySignature;

	@Column(name = "razor_pay_id")
	private String razorpayPaymentId;

	@ManyToOne
	@JoinColumn(name = "medicaluser_id", nullable = false)
	private Medicaluser medicaluser;

	public Payment(String paymentId, String razorpayOrderId, String razorpaySignature, String razorpayPaymentId, Medicaluser medicaluser) {
		this.paymentId = paymentId;
		this.razorpayOrderId = razorpayOrderId;
		this.razorpaySignature = razorpaySignature;
		this.razorpayPaymentId = razorpayPaymentId;
		this.medicaluser = medicaluser;
	}

	public Payment() {}

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

	public Medicaluser getMedicaluser() {
		return medicaluser;
	}

	public void setMedicaluser(Medicaluser medicaluser) {
		this.medicaluser = medicaluser;
	}
}
