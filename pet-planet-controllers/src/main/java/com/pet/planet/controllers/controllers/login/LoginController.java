package com.pet.planet.controllers.controllers.login;

import com.pet.planet.api.login.LoginRequest;
import com.pet.planet.api.login.LoginResponseType;
import com.pet.planet.controllers.controllers.publication.PublicationController;
import com.pet.planet.domain.service.login.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gabrieldyck on 24/03/17.
 */
@RequestMapping("login")
@Controller
@CrossOrigin
public class LoginController {

    @Autowired
    private LoginService loginService;
    private final Logger LOGGER= LoggerFactory.getLogger(LoginController.class);


    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String response= loginService.login(request);
        LOGGER.info("User: {} trying to login. Result: {}",response);

        return new ResponseEntity<String>(response, HttpStatus.OK);
    }

}
