package com.pet.planet.domain.service.user;

import com.pet.planet.api.user.ProfileSettingRequest;
import com.pet.planet.api.user.ProfileSettingResponse;
import com.pet.planet.domain.dao.repository.sql.UserAccountDao;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.service.mail.MailSender;
import com.pet.planet.domain.utils.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.annotation.adapters.HexBinaryAdapter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by gabrieldyck on 29/04/17.
 */
@Service
public class UserService {

    @Autowired
    private UserAccountDao userAccountDao;

    public boolean recoverPassword(String mail) {
        boolean recoverPassword = false;
        UserAccount userAccount = userAccountDao.findByMail(mail);
        if (userAccount != null) {
            MessageDigest md = null;
            try {
                md = MessageDigest.getInstance("SHA-256");
                String randomPassword = randomPassword().toUpperCase();
                md.update(randomPassword.getBytes("UTF-8"));
                String sha256Pass = new HexBinaryAdapter().marshal(md.digest());
                userAccount.setPassToken(sha256Pass);
                userAccountDao.save(userAccount);
                recoverPassword = true;
                MailSender.generateAndSendEmail("Contraseña reestablecida", "Hemos reestablecido su contraseña. Su nueva contraseña es : " + randomPassword, mail);
            } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
                e.printStackTrace();
            }

        }
        return recoverPassword;
    }


    public String regenerateAccessToken(UserAccount userAccount, String token) {
        boolean isTokenOk = validateAccessToken(userAccount, token);
        String accessToken = null;
        if (isTokenOk) {
            accessToken = AccessToken.generateAccessToken(userAccount.getUsername());
            userAccount.setAccessToken(accessToken);
            userAccountDao.save(userAccount);
        }
        return accessToken;
    }

    public boolean validateAccessToken(UserAccount userAccount, String accessToken) {
        boolean validateAccessToken = false;
        if (userAccount != null) {
            validateAccessToken = (AccessToken.buildAccessToken(userAccount.getUsername(), accessToken)).equalsIgnoreCase(userAccount.getAccessToken());
        }
        return validateAccessToken;
    }

    public boolean validateAccessToken(String user, String accessToken) {
        UserAccount userAccount = getUserAccount(user);
        boolean validateAccessToken = false;
        if (userAccount != null) {
            validateAccessToken = (AccessToken.buildAccessToken(user, accessToken)).equalsIgnoreCase(userAccount.getAccessToken());
        }
        return validateAccessToken;
    }

    private UserAccount getUserAccount(String user) {
        return userAccountDao.findByUsername(user);
    }

    public boolean changePassword(UserAccount userAccount, String oldPassword, String newPassword) {
        boolean changePassword = false;
        if (isPasswordCorrect(userAccount, oldPassword)) {
            userAccount.setPassToken(newPassword);
            userAccountDao.save(userAccount);
            changePassword = true;
        }
        return changePassword;
    }

    private boolean isPasswordCorrect(UserAccount userAccount, String oldPassword) {
        return userAccount.getPassToken().equalsIgnoreCase(oldPassword);
    }

    public ProfileSettingResponse getProfileSetting(String username, String accessToken) {
        UserAccount userAccount = getUserAccount(username);
        ProfileSettingResponse response = null;
        if (validateAccessToken(userAccount, accessToken)) {
            response = new ProfileSettingResponse(userAccount.getFullname(), userAccount.getBiography(), userAccount.getLocation(), userAccount.getPhoto());

        }
        return response;
    }

    public boolean setProfileSetting(ProfileSettingRequest profileSettingRequest) {
        boolean response = false;
        UserAccount userAccount = getUserAccount(profileSettingRequest.getUsername());
        if (validateAccessToken(userAccount, profileSettingRequest.getToken())) {
            userAccount.setFullname(profileSettingRequest.getFullname());
            userAccount.setBiography(profileSettingRequest.getBiography());
            userAccount.setLocation(profileSettingRequest.getLocation());

            userAccountDao.save(userAccount);
            response = true;


        }
        return response;
    }


    public boolean saveProfilePhoto(String username, String accessToken, byte[] photo) {
        boolean response = false;
        UserAccount userAccount = getUserAccount(username);
        if (validateAccessToken(userAccount, accessToken)) {
            userAccount.setPhoto(photo);
            userAccountDao.save(userAccount);
            response = true;
        }
        return response;
    }

    public UserAccount findByUsername(String username) {
        return userAccountDao.findByUsername(username);
    }

    private String randomPassword() {
        return UUID.randomUUID().toString();
    }

    public Long validateAccessTokenAndReturnId(String username, String accessToken) {
        UserAccount userAccount = getUserAccount(username);
        boolean validateAccessToken;
        if (userAccount != null) {
            validateAccessToken = (AccessToken.buildAccessToken(username, accessToken)).equalsIgnoreCase(userAccount.getAccessToken());
        if(validateAccessToken){
            return userAccount.getId();
        }
        }
        return null;
    }

}
