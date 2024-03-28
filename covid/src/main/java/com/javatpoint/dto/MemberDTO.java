package com.javatpoint.dto;


import com.javatpoint.model.Address;
import com.javatpoint.model.Sick;
import com.javatpoint.model.Vaccination;

import java.time.LocalDate;
import java.util.List;

public class MemberDTO {
    private Long id;
    private String tz;
    private String firstName;
    private String lastName;
    private LocalDate dateBirth;
    private String phone;
    private String mobilePhone;
    private String imagePath;
    private String image;
    private Address address;
    private List<Vaccination> vaccination;
    private Sick sick;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTz() {
        return tz;
    }

    public void setTz(String tz) {
        this.tz = tz;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Vaccination> getVaccination() {
        return vaccination;
    }

    public void setVaccination(List<Vaccination> vaccination) {
        this.vaccination = vaccination;
    }

    public Sick getSick() {
        return sick;
    }

    public void setSick(Sick sick) {
        this.sick = sick;
    }
}
