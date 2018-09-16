package com.pet.planet.api.transit.response;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class HomeTransitResponseDTO {


    private long id;

    protected List<TransitTypeResponseDTO> transitTypes;

    protected String contactName;

    protected String phone;

    protected String mail;

    protected float latitude;

    protected float longitude;

    private boolean movility;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<TransitTypeResponseDTO> getTransitTypes() {
        return transitTypes;
    }

    public void setTransitTypes(List<TransitTypeResponseDTO> transitTypes) {
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

    public boolean isMovility() {
        return movility;
    }

    public void setMovility(boolean movility) {
        this.movility = movility;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
