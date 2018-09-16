package com.pet.planet.api.publication.request;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import com.pet.planet.api.publication.GenderStatus;
import com.pet.planet.api.publication.SizeStatus;

import java.util.List;

/**
 * Created by gabrieldyck on 14/05/17.
 */
public class CreatePublicationRequestDTO {

    private String username;
    private String accessToken;
    private  String title;
    private String description;
    private List<String> images;
    private PointDTO location;
    private String contactName;
    private String phone;
    private String email;
    private String petName;
    private String petClass;
    private String petBreed;
    private PublicationType type;
    private PublicationStatus status;
    private String stringLocation;
    private String color;
    private SizeStatus size;
    private GenderStatus gender;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public PointDTO getLocation() {
        return location;
    }

    public void setLocation(PointDTO location) {
        this.location = location;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public PublicationType getType() {
        return type;
    }

    public void setType(PublicationType type) {
        this.type = type;
    }

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

    public String getPetClass() {
        return petClass;
    }

    public void setPetClass(String petClass) {
        this.petClass = petClass;
    }

    public String getPetBreed() {
        return petBreed;
    }

    public void setPetBreed(String petBreed) {
        this.petBreed = petBreed;
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public String getStringLocation() {
        return stringLocation;
    }

    public void setStringLocation(String stringLocation) {
        this.stringLocation = stringLocation;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public SizeStatus getSize() {
        return size;
    }

    public void setSize(SizeStatus size) {
        this.size = size;
    }

    public GenderStatus getGender() {
        return gender;
    }

    public void setGender(GenderStatus gender) {
        this.gender = gender;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    @Override
    public String toString() {
        return "CreatePublicationRequestDTO{" +
                "username='" + username + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", location=" + location +
                ", contactName='" + contactName + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", petClass='" + petClass + '\'' +
                ", petBreed='" + petBreed + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", stringLocation='" + stringLocation + '\'' +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", gender='" + gender + '\'' +
                ", petName='" + petName + '\'' +

                '}';
    }
}
