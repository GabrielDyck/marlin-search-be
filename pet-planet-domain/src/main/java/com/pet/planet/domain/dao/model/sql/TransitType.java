package com.pet.planet.domain.dao.model.sql;


import com.pet.planet.api.AnimalAge;

import javax.persistence.*;

@Entity
@Table(name = "TRANSIT_TYPE")
public class TransitType {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOME_TRANSIT_ID", nullable = false)
    private HomeTransit homeTransit;

    @Column(name = "ANIMAL_TYPE")
    protected String animalType;

    @Column(name = "ANIMAL_BREED")
    protected String animalBreed;

    @Column(name = "ANIMAL_AGE")
    @Enumerated(EnumType.STRING)
    protected AnimalAge animalAge;

    @Column(name = "DESCRIPTION")
    private String description;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public HomeTransit getHomeTransit() {
        return homeTransit;
    }

    public void setHomeTransit(HomeTransit homeTransit) {
        this.homeTransit = homeTransit;
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

    public AnimalAge getAnimalAge() {
        return animalAge;
    }

    public void setAnimalAge(AnimalAge animalAge) {
        this.animalAge = animalAge;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
