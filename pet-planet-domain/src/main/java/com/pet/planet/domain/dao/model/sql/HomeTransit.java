package com.pet.planet.domain.dao.model.sql;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "HOME_TRANSIT")
public class HomeTransit {

    @Id
    @GeneratedValue
    @Column(name="ID")
    private long id;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ACCOUNT_ID", nullable = false)
    private UserAccount user;

    @Cascade(value = org.hibernate.annotations.CascadeType.ALL)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "homeTransit")
    private List<TransitType> transitTypes;

    @Column(name = "CONTACT_NAME")
    protected String contactName;


    @Column(name = "PHONE")
    protected String phone;


    @Column(name = "MAIL")
    protected String mail;

    @Column(name = "LATITUDE")
    protected float latitude;

    @Column(name = "LONGITUDE")
    protected float longitude;


    @Column(name = "HAS_MOVILITY")
    private boolean movility;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public boolean isMovility() {
        return movility;
    }

    public void setMovility(boolean movility) {
        this.movility = movility;
    }

    public List<TransitType> getTransitTypes() {
        return transitTypes;
    }

    public void setTransitTypes(List<TransitType> transitTypes) {
        this.transitTypes = transitTypes;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
}
