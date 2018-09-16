package com.pet.planet.api.location;

/**
 * Created by gabrieldyck on 14/05/17.
 */
public class PointDTO {

    private float latitude;
    private float longitude;


    public PointDTO() {

    }


    public PointDTO(float latitude, float longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public float getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }


    @Override
    public String toString() {
        return "PointDTO{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
