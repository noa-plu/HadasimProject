package com.javatpoint.model;

import java.time.LocalDate;
import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull(message = "Identity is mandatory")
    @Size(max = 9,min = 9)
    private String tz;
    @NotNull(message = " First Name is mandatory")
    private String firstName;
    @NotNull(message = " last Name is mandatory")
    private String lastName;
    @NotNull(message = "Name is mandatory")
    @Past(message = "birth Date must be in the past")
    private LocalDate dateBirth;
    @Size(max = 10,min = 9, message = "Phone1 number must be between 9 and 10 characters")
    private String phone;
    @Size(max = 10,min = 10, message = "Cell phone number must be 10 characters")
    private String mobilePhone;
    private String image;

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
    private Address address;

    @OneToOne(mappedBy = "member",cascade = CascadeType.ALL)
    private Sick sick;

    public Member() {
    }
    public Member(Long id, String tz, String firstName, String lastName, LocalDate dateBirth, String phone, String mobilePhone, String image, Address address, Sick sick) {
        this.id = id;
        this.tz = tz;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateBirth = dateBirth;
        this.phone = phone;
        this.mobilePhone = mobilePhone;
        this.image=image;
        this.address = address;
        this.sick = sick;
    }

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

    public Sick getSick() {
        return sick;
    }

    public void setSick(Sick sick) {
        this.sick = sick;
    }
}
