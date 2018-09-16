package com.pet.planet.api.transit.request;

import com.pet.planet.api.AnimalAge;
import com.pet.planet.api.location.PointDTO;

import java.util.List;

public class SearchTransitRequestDTO {

    protected Long transitId;

    protected String animalType;

    protected String animalBreed;

    protected AnimalAge animalAge;

 protected List<PointDTO> boundary;
    protected Integer range;


    private int limit;
    private int page;

    public Long getTransitId() {
        return transitId;
    }

    public void setTransitId(Long transitId) {
        this.transitId = transitId;
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

    public List<PointDTO> getBoundary() {
        return boundary;
    }

    public void setBoundary(List<PointDTO> boundary) {
        this.boundary = boundary;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
