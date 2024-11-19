package com.project2.ayurcare.ayurcare_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "channelling")
public class Channelling {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Channelling_id", nullable = false)
    private Integer Channelling_id;

    @Column(name = "fee" , nullable = false)
    private String Fee;

    public Channelling() {}

    public Channelling(Integer channelling_id, String fee) {
        Channelling_id = channelling_id;
        Fee = fee;
    }

    public Integer getChannelling_id() {
        return Channelling_id;
    }

    public void setChannelling_id(Integer channelling_id) {
        Channelling_id = channelling_id;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }
}
