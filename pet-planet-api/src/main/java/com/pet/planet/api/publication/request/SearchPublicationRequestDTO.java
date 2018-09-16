package com.pet.planet.api.publication.request;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import org.joda.time.DateTime;

import java.util.List;

/**
 * Created by gabrieldyck on 20/05/17.
 */

public class SearchPublicationRequestDTO {

    private Long publicationId;
    private String name;
    private String petClass;
    private String petBreed;
    private PublicationType publicationType;
    private List<PointDTO> boundary;
    private PublicationStatus publicationStatus;
    private Integer range;
    private String dateFrom;
    private String dateTo;
    private int limit;
    private int page;


    public Long getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Long publicationId) {
        this.publicationId = publicationId;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public void setPublicationType(PublicationType publicationType) {
        this.publicationType = publicationType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PointDTO> getBoundary() {
        return boundary;
    }

    public void setBoundary(List<PointDTO> boundary) {
        this.boundary = boundary;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
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

    public PublicationStatus getPublicationStatus() {
        return publicationStatus;
    }

    public void setPublicationStatus(PublicationStatus publicationStatus) {
        this.publicationStatus = publicationStatus;
    }

    @Override
    public String toString() {
        return "SearchPublicationRequestDTO{" +
                "publicationId=" + publicationId +
                ", name='" + name + '\'' +
                ", petClass='" + petClass + '\'' +
                ", petBreed='" + petBreed + '\'' +
                ", publicationType=" + publicationType +
                ", boundary=" + boundary +
                ", publicationStatus=" + publicationStatus +
                ", range=" + range +
                ", dateFrom='" + dateFrom + '\'' +
                ", dateTo='" + dateTo + '\'' +
                ", limit=" + limit +
                ", page=" + page +
                '}';
    }
}
