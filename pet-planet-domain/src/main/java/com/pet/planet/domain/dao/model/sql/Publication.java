package com.pet.planet.domain.dao.model.sql;

import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import com.pet.planet.api.publication.GenderStatus;
import com.pet.planet.api.publication.SizeStatus;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;


/**
 * Created by gabrieldyck on 14/05/17.
 */

@Entity
@Table(name = "PUBLICATION")
public  class Publication {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    protected Long id;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ACCOUNT_ID", foreignKey = @ForeignKey(name = "FK_USER_ACCOUNT"))
    private UserAccount userAccount;

    @Column(name = "PUBLICATION_STATUS")
    @Enumerated(EnumType.STRING)
    private PublicationStatus status;

    @Column(name = "PUBLICATION_TYPE")
    @Enumerated(EnumType.STRING)
    private PublicationType publicationType;

    @Column(name = "CREATION_DATE")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationDate;

    @Column(name = "TITLE")
    protected String title;

    @Column(name = "DESCRIPTION")
    protected String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "IMAGE_ID", foreignKey = @ForeignKey(name = "FK_IMAGE"))
    protected List<Image> images;

    @Column(name = "STRING_LOCATION")
    protected String stringLocation;


    @Column(name = "COLOR")
    protected String color;


    @Column(name = "SIZE")
    protected SizeStatus size;

    @Column(name = "SEX")
    private GenderStatus gender;


    @Column(name = "LATITUDE")
    protected float latitude;

    @Column(name = "LONGITUDE")
    protected float longitude;

    @Column(name = "PET_NAME")
    protected  String petName;

    @Column(name = "CONTACT_NAME")
    protected String contactName;

    @Column(name = "PHONE")
    protected String phone;

    @Column(name = "EMAIL")
    protected String email;

    @Column(name = "ANIMAL_TYPE")
    protected String animalType;

    @Column(name = "ANIMAL_BREED")
    protected String animalBreed;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }


    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
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

    public String getAnimalType() {
        return animalType;
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

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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
}
