package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Past;
import java.time.LocalDate;


@Entity
public class Vaccination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Past(message = "getting must be in the past")
    private LocalDate getting;
    private String manufacturer;

    @JsonIgnore
    @ManyToOne
    private Sick sick;

    public Vaccination() {
    }

    public Vaccination(Long id, LocalDate getting, String manufacturer, Sick sick) {
        this.id = id;
        this.getting = getting;
        this.manufacturer = manufacturer;
        this.sick = sick;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getGetting() {
        return getting;
    }

    public void setGetting(LocalDate getting) {
        this.getting = getting;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Sick getSick() {
        return sick;
    }

    public void setSick(Sick sick) {
        this.sick = sick;
    }
}
