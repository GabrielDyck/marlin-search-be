package com.pet.planet.api.publication.request;

public class EditPublicationRequestDTO  extends CreatePublicationRequestDTO{


    private long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return super.toString() +
                "id=" + id +
                '}';
    }
}
