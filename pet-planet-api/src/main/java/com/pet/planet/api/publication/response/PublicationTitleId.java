package com.pet.planet.api.publication.response;

import org.joda.time.DateTime;

/**
 * Created by gabrieldyck on 23/08/17.
 */
public class PublicationTitleId {

    private String title;
    private long id;
    private DateTime creationDate;

    public PublicationTitleId(String title, long id, DateTime creationDate) {
        this.title = title;
        this.id = id;
        this.creationDate = creationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public DateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(DateTime creationDate) {
        this.creationDate = creationDate;
    }
}
