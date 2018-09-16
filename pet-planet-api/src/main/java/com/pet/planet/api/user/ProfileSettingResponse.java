package com.pet.planet.api.user;

/**
 * Created by gabrieldyck on 04/05/17.
 */
public class ProfileSettingResponse {

    private String fullname;
    private String biography;
    private String location;
    private byte[] photo;


    public ProfileSettingResponse(String fullname, String biography, String location, byte[] photo) {
        this.fullname = fullname;
        this.biography = biography;
        this.location = location;
        this.photo=photo;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
}
