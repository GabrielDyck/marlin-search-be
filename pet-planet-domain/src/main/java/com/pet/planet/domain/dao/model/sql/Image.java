package com.pet.planet.domain.dao.model.sql;


import javax.persistence.*;

@Entity
@Table(name = "IMAGE")
public class Image {

    @GeneratedValue
    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "IMAGE")
    @Lob
    protected byte[] image;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Image(byte[] image) {
        this.image = image;
    }

    public Image() {
    }

}
