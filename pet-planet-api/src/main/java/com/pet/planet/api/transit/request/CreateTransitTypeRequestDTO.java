package com.pet.planet.api.transit.request;

import com.pet.planet.api.AnimalAge;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;


public class CreateTransitTypeRequestDTO {


    private Long id;

    protected String animalType;

    protected String animalBreed;

    protected AnimalAge animalAge;

    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }
}
