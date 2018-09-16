package com.pet.planet.controllers.controllers.login;

import com.pet.planet.api.signin.SignInRequestDTO;
import com.pet.planet.domain.service.signin.SignInService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gabrieldyck on 18/03/17.
 */
@Controller
@CrossOrigin
@RequestMapping(value = "sign-in")
public class SignInController {

    @Autowired
    private SignInService signInService;

    private final Logger LOGGER = LoggerFactory.getLogger(SignInController.class);

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> signIn(@RequestBody SignInRequestDTO request) {
        LOGGER.info("User: {} trying to register with {}",request.getUsername(),request.toString());
        signInService.createUser(request);
        LOGGER.info("User: {} trying to registered OK",request.getUsername());

        return new ResponseEntity<String>("ok", HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "valid-email", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkExistingEmail(@RequestParam String mail) {
        return new ResponseEntity<Boolean>(signInService.validEmail(mail), HttpStatus.OK);

    }

    @ResponseBody
    @RequestMapping(value = "valid-user", method = RequestMethod.GET)
    public ResponseEntity<Boolean> checkExistingUser(@RequestParam String user) {
        return new ResponseEntity<Boolean>(signInService.validUser(user), HttpStatus.OK);

    }
}
