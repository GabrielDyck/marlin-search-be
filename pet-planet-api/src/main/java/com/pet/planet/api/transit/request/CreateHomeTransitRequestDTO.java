package com.pet.planet.api.transit.request;

import com.pet.planet.api.location.PointDTO;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

import java.util.List;

public class CreateHomeTransitRequestDTO {

    private String username;
    private String accessToken;
    private String contactName;
    private String phone;
    private String email;
    private Long homeTransitId;
    private boolean hasMovility;
    private float longitude;
    private float latitude;

    List<CreateTransitTypeRequestDTO> transitTypes;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getHomeTransitId() {
        return homeTransitId;
    }

    public void setHomeTransitId(Long homeTransitId) {
        this.homeTransitId = homeTransitId;
    }

    public boolean isHasMovility() {
        return hasMovility;
    }

    public void setHasMovility(boolean hasMovility) {
        this.hasMovility = hasMovility;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public List<CreateTransitTypeRequestDTO> getTransitTypes() {
        return transitTypes;
    }

    public void setTransitTypes(List<CreateTransitTypeRequestDTO> transitTypes) {
        this.transitTypes = transitTypes;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
