package com.pet.planet.domain.utils;

import org.joda.time.DateTime;

/**
 * Created by gabrieldyck on 29/04/17.
 */
public class AccessToken {

    public static String generateAccessToken(String username){
        return username + "-" + DateTime.now().getMillis();
    }

    public static String buildAccessToken(String username,String token){

        return username + "-" + token;
    }
}
