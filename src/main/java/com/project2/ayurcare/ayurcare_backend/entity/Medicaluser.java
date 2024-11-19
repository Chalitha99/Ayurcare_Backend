//package com.project2.ayurcare.ayurcare_backend.entity;
//
//import java.util.Collection;
//import java.util.List;
//
//import com.project2.ayurcare.ayurcare_backend.entity.listener.UserEntityListener;
//
//import jakarta.persistence.*;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//@Entity
//@Table(name = "medicaluser")
//@EntityListeners(UserEntityListener.class)
//public class Medicaluser implements UserDetails{
//
//
//	@Id
//	@Column(name = "medicaluser_id")
//	private String medicaluserId;
//
//	@Column(name = "medicaluser_firstname")
//	private String medicaluserFirstname;
//
//	@Column(name = "medicaluser_lastname")
//	private String medicaluserLastname;
//
//	@Column(name = "medicaluser_email")
//	private String medicaluserEmail;
//
//	@Column(name="medicaluser_password")
//	private String medicalUserPassword;
//
//	@Column(name = "medicaluser_phoneno")
//	private String medicaluserPhoneno;
//
//	@Lob
//	@Column(name = "medicaluser_photo")
//	private byte[] medicaluserPhoto;
//
//	@Column(name = "medicaluser_address")
//	private String medicaluserAddress;
//
//	@Enumerated(value = EnumType.STRING)
//	Role role;
//
//	@Column(name = "medicaluser_Intreatment")
//	private String medicaluserIntreatment;
//
//	@OneToMany(mappedBy = "medicaluser")
//	private List<Patient> patients;
//
//	@OneToMany(mappedBy = "medicaluser")
//	private List<Payment> payments;
//
//	@OneToMany(mappedBy = "medicaluser", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Token> tokens;
//
//	public Medicaluser() {
//
//	}
//
//	public Medicaluser(String medicaluserId, String medicaluserFirstname, String medicaluserLastname, String medicaluserEmail, String medicalUserPassword, String medicaluserPhoneno, byte[] medicaluserPhoto, String medicaluserAddress, Role role, String medicaluserIntreatment, List<Patient> patients, List<Payment> payments, List<Token> tokens) {
//		this.medicaluserId = medicaluserId;
//		this.medicaluserFirstname = medicaluserFirstname;
//		this.medicaluserLastname = medicaluserLastname;
//		this.medicaluserEmail = medicaluserEmail;
//		this.medicalUserPassword = medicalUserPassword;
//		this.medicaluserPhoneno = medicaluserPhoneno;
//		this.medicaluserPhoto = medicaluserPhoto;
//		this.medicaluserAddress = medicaluserAddress;
//		this.role = role;
//		this.medicaluserIntreatment = medicaluserIntreatment;
//		this.patients = patients;
//		this.payments = payments;
//		this.tokens = tokens;
//	}
//
//	public String getMedicaluserId() {
//		return medicaluserId;
//	}
//
//	public void setMedicaluserId(String medicaluserId) {
//		this.medicaluserId = medicaluserId;
//	}
//
//	public String getMedicaluserFirstname() {
//		return medicaluserFirstname;
//	}
//
//	public void setMedicaluserFirstname(String medicaluserFirstname) {
//		this.medicaluserFirstname = medicaluserFirstname;
//	}
//
//	public String getMedicaluserLastname() {
//		return medicaluserLastname;
//	}
//
//	public void setMedicaluserLastname(String medicaluserLastname) {
//		this.medicaluserLastname = medicaluserLastname;
//	}
//
//	public String getMedicaluserEmail() {
//		return medicaluserEmail;
//	}
//
//	public void setMedicaluserEmail(String medicaluserEmail) {
//		this.medicaluserEmail = medicaluserEmail;
//	}
//
//	public String getMedicaluserPhoneno() {
//		return medicaluserPhoneno;
//	}
//
//	public void setMedicaluserPhoneno(String medicaluserPhoneno) {
//		this.medicaluserPhoneno = medicaluserPhoneno;
//	}
//
//	public byte[] getMedicaluserPhoto() {
//		return medicaluserPhoto;
//	}
//
//	public void setMedicaluserPhoto(byte[] medicaluserPhoto) {
//		this.medicaluserPhoto = medicaluserPhoto;
//	}
//
//	public String getMedicaluserAddress() {
//		return medicaluserAddress;
//	}
//
//	public void setMedicaluserAddress(String medicaluserAddress) {
//		this.medicaluserAddress = medicaluserAddress;
//	}
//
//	public String getMedicaluserIntreatment() {
//		return medicaluserIntreatment;
//	}
//
//	public void setMedicaluserIntreatment(String medicaluserIntreatment) {
//		this.medicaluserIntreatment = medicaluserIntreatment;
//	}
//
//	public List<Patient> getPatients() {
//		return patients;
//	}
//
//	public void setPatients(List<Patient> patients) {
//		this.patients = patients;
//	}
//
//	public List<Payment> getPayments() {
//		return payments;
//	}
//
//	public void setPayments(List<Payment> payments) {
//		this.payments = payments;
//	}
//
//	public String getMedicalUserPassword() {
//		return medicalUserPassword;
//	}
//
//	public void setMedicalUserPassword(String medicalUserPassword) {
//		this.medicalUserPassword = medicalUserPassword;
//	}
//
//	public Role getRole() {
//		return role;
//	}
//
//	public void setRole(Role role) {
//		this.role = role;
//	}
//
//	public List<Token> getTokens() {
//		return tokens;
//	}
//
//	public void setTokens(List<Token> tokens) {
//		this.tokens = tokens;
//	}
//
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		return List.of(new SimpleGrantedAuthority(role.name()));
//	}
//
//	@Override
//	public String getPassword() {
//		return medicalUserPassword;
//	}
//
//	@Override
//	public String getUsername() {
//		return medicaluserEmail;
//	}
//
//	@Override
//	public boolean isAccountNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		return true;
//	}
//}



package com.project2.ayurcare.ayurcare_backend.entity;

import java.util.Collection;
import java.util.List;

import com.project2.ayurcare.ayurcare_backend.entity.listener.UserEntityListener;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "medicaluser")
@EntityListeners(UserEntityListener.class)
public class Medicaluser implements UserDetails{


	@Id
	@Column(name = "medicaluser_id")
	private String medicaluserId;

	@Column(name = "medicaluser_firstname")
	private String medicaluserFirstname;

	@Column(name = "medicaluser_lastname")
	private String medicaluserLastname;

	@Column(name = "medicaluser_email")
	private String medicaluserEmail;

	@Column(name="medicaluser_password")
	private String medicalUserPassword;

	@Column(name = "medicaluser_phoneno")
	private String medicaluserPhoneno;

	@Lob
	@Column(name = "medicaluser_photo")
	private byte[] medicaluserPhoto;

	@Column(name = "medicaluser_address")
	private String medicaluserAddress;

	@Enumerated(value = EnumType.STRING)
	Role role;

	@Column(name = "medicaluser_Intreatment")
	private String medicaluserIntreatment;

	@OneToMany(mappedBy = "medicaluser")
	private List<Patient> patients;

	@OneToMany(mappedBy = "medicaluser")
	private List<Payment> payments;

	@OneToMany(mappedBy = "medicaluser", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Token> tokens;

	@Column(name = "is_deleted", nullable = false)
	private boolean isDeleted = false;


	public Medicaluser() {

	}

	public Medicaluser(String medicaluserId, String medicaluserFirstname, String medicaluserLastname, String medicaluserEmail, String medicalUserPassword, String medicaluserPhoneno, byte[] medicaluserPhoto, String medicaluserAddress, Role role, String medicaluserIntreatment, List<Patient> patients, List<Payment> payments, List<Token> tokens,boolean isDeleted) {
		this.medicaluserId = medicaluserId;
		this.medicaluserFirstname = medicaluserFirstname;
		this.medicaluserLastname = medicaluserLastname;
		this.medicaluserEmail = medicaluserEmail;
		this.medicalUserPassword = medicalUserPassword;
		this.medicaluserPhoneno = medicaluserPhoneno;
		this.medicaluserPhoto = medicaluserPhoto;
		this.medicaluserAddress = medicaluserAddress;
		this.role = role;
		this.medicaluserIntreatment = medicaluserIntreatment;
		this.patients = patients;
		this.payments = payments;
		this.tokens = tokens;
		this.isDeleted = isDeleted;
	}

	public String getMedicaluserId() {
		return medicaluserId;
	}

	public void setMedicaluserId(String medicaluserId) {
		this.medicaluserId = medicaluserId;
	}

	public String getMedicaluserFirstname() {
		return medicaluserFirstname;
	}

	public void setMedicaluserFirstname(String medicaluserFirstname) {
		this.medicaluserFirstname = medicaluserFirstname;
	}

	public String getMedicaluserLastname() {
		return medicaluserLastname;
	}

	public void setMedicaluserLastname(String medicaluserLastname) {
		this.medicaluserLastname = medicaluserLastname;
	}

	public String getMedicaluserEmail() {
		return medicaluserEmail;
	}

	public void setMedicaluserEmail(String medicaluserEmail) {
		this.medicaluserEmail = medicaluserEmail;
	}

	public String getMedicaluserPhoneno() {
		return medicaluserPhoneno;
	}

	public void setMedicaluserPhoneno(String medicaluserPhoneno) {
		this.medicaluserPhoneno = medicaluserPhoneno;
	}

	public byte[] getMedicaluserPhoto() {
		return medicaluserPhoto;
	}

	public void setMedicaluserPhoto(byte[] medicaluserPhoto) {
		this.medicaluserPhoto = medicaluserPhoto;
	}

	public String getMedicaluserAddress() {
		return medicaluserAddress;
	}

	public void setMedicaluserAddress(String medicaluserAddress) {
		this.medicaluserAddress = medicaluserAddress;
	}

	public String getMedicaluserIntreatment() {
		return medicaluserIntreatment;
	}

	public void setMedicaluserIntreatment(String medicaluserIntreatment) {
		this.medicaluserIntreatment = medicaluserIntreatment;
	}

	public List<Patient> getPatients() {
		return patients;
	}

	public void setPatients(List<Patient> patients) {
		this.patients = patients;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public String getMedicalUserPassword() {
		return medicalUserPassword;
	}

	public void setMedicalUserPassword(String medicalUserPassword) {
		this.medicalUserPassword = medicalUserPassword;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<Token> getTokens() {
		return tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public boolean isDeleted() {
		return isDeleted;
	}

	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {
		return medicalUserPassword;
	}

	@Override
	public String getUsername() {
		return medicaluserEmail;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
