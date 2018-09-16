package com.pet.planet.domain.dao.model.sql;

import javax.persistence.*;
import java.sql.Blob;

/**
 * Created by gabrieldyck on 19/03/17.
 */
@Entity
@Table(name = "USER_ACCOUNT")
public class UserAccount {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "FULLNAME")
    private String fullname;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "PASS_TOKEN")
    private String passToken;

    @Column(name = "MAIL")
    private String mail;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "PHOTO")
    private byte[] photo;

    @Column(name = "VALIDATED")
    private char validated;

    @Column(name = "BIOGRAPHY")
    private String biography;

    @Column(name = "LOCATION")
    private String location;


    @OneToOne(fetch = FetchType.LAZY, mappedBy = "user")
    @JoinColumn(name = "HOME_TRANSIT_ID")
    private HomeTransit homeTransit;

    @Column(name = "ACCESS_TOKEN")
    private String accessToken;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassToken() {
        return passToken;
    }

    public void setPassToken(String passToken) {
        this.passToken = passToken;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public char getValidated() {
        return validated;
    }

    public void setValidated(char validated) {
        this.validated = validated;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public HomeTransit getHomeTransit() {
        return homeTransit;
    }

    public void setHomeTransit(HomeTransit homeTransit) {
        this.homeTransit = homeTransit;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}

