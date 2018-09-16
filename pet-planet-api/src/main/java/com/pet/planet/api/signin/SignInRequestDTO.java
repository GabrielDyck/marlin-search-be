package com.pet.planet.api.signin;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * Created by gabrieldyck on 19/03/17.
 */
public class SignInRequestDTO {

    private String fullname;

    private String username;

    private String password;

    private String mail;

    private String phone;

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this);
    }

}
