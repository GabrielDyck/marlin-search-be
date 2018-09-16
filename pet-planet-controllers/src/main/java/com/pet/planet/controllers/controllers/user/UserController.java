package com.pet.planet.controllers.controllers.user;

import com.pet.planet.api.publication.response.PublicationTitleId;
import com.pet.planet.api.user.ChangePasswordRequest;
import com.pet.planet.api.user.ProfileSettingRequest;
import com.pet.planet.api.user.ProfileSettingResponse;
import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.service.publication.PublicationService;
import com.pet.planet.domain.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gabrieldyck on 29/04/17.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "user")
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private PublicationService publicationService;

    private final Logger LOGGER= LoggerFactory.getLogger(UserController.class);

    @ResponseBody
    @RequestMapping(value = "/regenerate-at", method = RequestMethod.GET)
    public ResponseEntity<String> regenerateAccessToken(@RequestParam String username, @RequestParam String token) {
        UserAccount userAccount = userService.findByUsername(username);
        String accessToken = userService.regenerateAccessToken(userAccount, token);
        if (accessToken != null) {
            return new ResponseEntity<String>(accessToken, HttpStatus.OK);
        } else {
            return new ResponseEntity<String>("User doesn't exists", HttpStatus.BAD_REQUEST);
        }


    }

    @ResponseBody
    @RequestMapping(value = "/validate-at", method = RequestMethod.GET)
    public ResponseEntity validateAccessToken(@RequestParam String username, @RequestParam String accessToken) {
        UserAccount userAccount = userService.findByUsername(username);
        if (userService.validateAccessToken(userAccount, accessToken)) {
            return new ResponseEntity(accessToken, HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/recover-pw", method = RequestMethod.GET)
    public ResponseEntity recoverPw(@RequestParam String mail) {
        LOGGER.info("Mail: {} is trying to recover password",mail);
        if (userService.recoverPassword(mail)) {
            LOGGER.info("Mail: {} new password sent to his email",mail);

            return new ResponseEntity(HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }


    @ResponseBody
    @RequestMapping(value = "/change-pw", method = RequestMethod.POST)
    public ResponseEntity validateAccessToken(@RequestBody ChangePasswordRequest changePasswordRequest) {
        LOGGER.info("Username: {} is trying to change password",changePasswordRequest.getUsername());

        UserAccount userAccount = userService.findByUsername(changePasswordRequest.getUsername());
        if (userService.validateAccessToken(userAccount, changePasswordRequest.getToken()) && userService.changePassword(userAccount, changePasswordRequest.getOldPassword(), changePasswordRequest.getNewPassword())) {
            LOGGER.info("Username: {} is change password success",changePasswordRequest.getUsername());

            return new ResponseEntity(HttpStatus.OK);
        }
        LOGGER.info("Username: {} is not authorize to change password. Validate error.",changePasswordRequest.getUsername());

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }


    @ResponseBody
    @RequestMapping(value = "/get-profile-settings", method = RequestMethod.POST)
    public ResponseEntity<ProfileSettingResponse> getProfileSetting(@RequestBody ProfileSettingRequest profileSettingRequest) {
        ProfileSettingResponse profileSettingResponse = userService.getProfileSetting(profileSettingRequest.getUsername(), profileSettingRequest.getToken());
        if (profileSettingRequest != null) {
            return new ResponseEntity<ProfileSettingResponse>(profileSettingResponse, HttpStatus.OK);
        }
        return new ResponseEntity<ProfileSettingResponse>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "/set-profile-settings", method = RequestMethod.POST)
    public ResponseEntity<ProfileSettingResponse> setProfileSetting(@RequestBody ProfileSettingRequest profileSettingRequest) {
        if (userService.setProfileSetting(profileSettingRequest)) {
            return new ResponseEntity<ProfileSettingResponse>(HttpStatus.OK);
        }
        return new ResponseEntity<ProfileSettingResponse>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "/save-image", method = RequestMethod.POST)
    public ResponseEntity upload(@RequestParam CommonsMultipartFile file, @RequestParam String username, @RequestParam String accessToken) {
        byte photo[] = file.getBytes();
        if (userService.saveProfilePhoto(username, accessToken, photo)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "get-publications", method = RequestMethod.GET)
    public ResponseEntity<List<PublicationTitleId>> getPublications(@RequestParam String user, @RequestParam String accessToken) {
        Long id = userService.validateAccessTokenAndReturnId(user, accessToken);

        if (id != null) {
            List<PublicationTitleId> titles = publicationService.getPublicationsByUserAccountId(id).stream().map(publication -> new PublicationTitleId(publication.getTitle(),publication.getId(),publication.getCreationDate()))
                    .collect(Collectors.toList());
            return new ResponseEntity<>(titles,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }





}
