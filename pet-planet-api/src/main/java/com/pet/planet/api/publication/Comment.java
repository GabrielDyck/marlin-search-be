package com.pet.planet.api.publication;

import org.joda.time.DateTime;

import java.io.File;
import java.util.Date;

/**
 * Created by gabrieldyck on 25/03/17.
 */
public class Comment {


    private Long userId;
    private String userName;
    private String content;
    private Date creationDate;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
