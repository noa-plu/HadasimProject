package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Sick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Past(message = "positive must be in the past")
    private LocalDate positive;
    @Past(message = "negative must be in the past")
    private LocalDate negative;

    @OneToMany(mappedBy = "sick", cascade = CascadeType.ALL)
    @Size(max = 4)
    private List<Vaccination> vaccination;

    @JsonIgnore
    @OneToOne( cascade = CascadeType.ALL)
    private Member member;

    public Sick() {
    }

    public Sick(Long id, LocalDate positive, LocalDate negative, List<Vaccination> vaccination, Member member) {
        this.id = id;
        this.positive = positive;
        this.negative = negative;
        this.vaccination=vaccination;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getPositive() {
        return positive;
    }

    public void setPositive(LocalDate positive) {
        this.positive = positive;
    }

    public LocalDate getNegative() {
        return negative;
    }

    public void setNegative(LocalDate negative) {
        this.negative = negative;
    }

    public List<Vaccination> getVaccination() {
        return vaccination;
    }

    public void setVaccination(List<Vaccination> vaccination) {
        this.vaccination = vaccination;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}

