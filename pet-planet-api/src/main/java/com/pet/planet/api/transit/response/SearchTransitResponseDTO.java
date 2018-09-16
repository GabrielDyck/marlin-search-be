package com.pet.planet.api.transit.response;

import com.pet.planet.api.AnimalAge;
import com.pet.planet.api.location.PointDTO;

public class SearchTransitResponseDTO {


    protected Long transitId;

    protected String animalType;

    protected String animalBreed;

    protected AnimalAge animalAge;

    protected String description;


    protected PointDTO location;

    protected String contactName;


    protected String phone;


    protected String mail;

    public Long getTransitId() {
        return transitId;
    }

    public void setTransitId(Long transitId) {
        this.transitId = transitId;
    }

    public String getAnimalType() {
        return animalType;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public String getAnimalBreed() {
        return animalBreed;
    }

    public void setAnimalBreed(String animalBreed) {
        this.animalBreed = animalBreed;
    }

    public AnimalAge getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(AnimalAge animalAge) {
        this.animalAge = animalAge;
    }

    public PointDTO getLocation() {
        return location;
    }

    public void setLocation(PointDTO location) {
        this.location = location;
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
}


