package com.project2.ayurcare.ayurcare_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "is_logged_out")
    private Boolean loggedOut;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private Medicaluser medicaluser;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public Medicaluser getMedicaluser() {
        return medicaluser;
    }

    public void setMedicaluser(Medicaluser medicaluser) {
        this.medicaluser = medicaluser;
    }
}
