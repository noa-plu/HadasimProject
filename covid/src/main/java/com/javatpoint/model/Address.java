package com.javatpoint.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String city;
    private String street;
    private String numBuild;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private Member member;

    public Address() {
    }

    public Address(Long id, String city, String street, String numBuild, Member member) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.numBuild = numBuild;
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNumBuild() {
        return numBuild;
    }

    public void setNumBuild(String numBuild) {
        this.numBuild = numBuild;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }
}
