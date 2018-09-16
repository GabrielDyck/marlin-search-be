package com.pet.planet.api.publication.response;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.*;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.List;

/**
 * Created by gabrieldyck on 20/05/17.
 */
public class SearchResponseDTO implements Serializable {

    private long id;
    private String title;
    private String description;
    private List<String> images;
    private PublicationType type;
    private PublicationStatus status;
    private List<Comment> comments;
    private PointDTO location;
    private String contactName;
    private String phone;
    private String email;
    private String petName;
    private String petClass;
    private String petBreed;
    private DateTime creationDate;
    private String stringLocation;
    private String color;
    private SizeStatus size;
    private GenderStatus gender;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public PublicationType getType() {
        return type;
    }

    public void setType(PublicationType type) {
        this.type = type;
    }

    public PublicationStatus getStatus() {
        return status;
    }

    public void setStatus(PublicationStatus status) {
        this.status = status;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
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
}
