package com.pet.planet.controllers.controllers;

import com.pet.planet.api.UserRequestDTO;
import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.response.PageResponse;
import com.pet.planet.api.transit.request.CreateHomeTransitRequestDTO;
import com.pet.planet.api.transit.request.SearchTransitRequestDTO;
import com.pet.planet.api.transit.response.HomeTransitResponseDTO;
import com.pet.planet.api.transit.response.SearchTransitResponseDTO;
import com.pet.planet.controllers.controllers.user.UserController;
import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.service.transit.TransitService;
import com.pet.planet.domain.service.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/transit")
public class TransitController {


    @Autowired
    private TransitService transitService;


    @Autowired
    private UserService userService;

    private final Logger LOGGER= LoggerFactory.getLogger(TransitController.class);


    @RequestMapping(value = "create",method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<HomeTransitResponseDTO> createTransitHome(@RequestBody CreateHomeTransitRequestDTO request) {
        LOGGER.info("User: {} trying to create HomeTransit: {} ",request.getUsername(),request.toString());
        UserAccount user = userService.findByUsername(request.getUsername());
        if (userService.validateAccessToken(user, request.getAccessToken())) {
            HomeTransitResponseDTO response= transitService.createHomeTransit(request, user);
            LOGGER.info("User: {} create HomeTransit: {}  succesfully",request.getUsername(),response.toString());

            return new ResponseEntity(response,HttpStatus.OK);
        }
        LOGGER.info("User: {} not autorize to create HomeTransit. Not valide access ",request.getUsername());

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "getTransit",method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<HomeTransitResponseDTO> getTransit(@RequestBody UserRequestDTO request) {
        LOGGER.info("User: {} trying to get his own HomeTransit",request.getUser());

        UserAccount user = userService.findByUsername(request.getUser());
        if (userService.validateAccessToken(user, request.getAccessToken())) {
            HomeTransitResponseDTO responseDTO= transitService.getHomeTransit(user);
            LOGGER.info("User: {} get his own HomeTransit successfully",request.getUser());

            return new ResponseEntity<>(responseDTO,HttpStatus.OK);
        }
        LOGGER.info("User: {} not authorize to get his own HomeTransit. Not valide access",request.getUser());

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(value = "location",method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<PointDTO> getLocation(@RequestBody UserRequestDTO request) {
        LOGGER.info("User: {} trying to get his HomeTransit location",request.getUser());

            UserAccount user = userService.findByUsername(request.getUser());
        if (userService.validateAccessToken(user, request.getAccessToken())) {
            HomeTransit home= user.getHomeTransit();
            if(home!=null){
                LOGGER.info("User: {}  get his HomeTransit location successfully",request.getUser());
                return new ResponseEntity<>(new PointDTO(home.getLatitude(),home.getLongitude()),HttpStatus.OK);
            }
            LOGGER.info("User: {} not has HomeTransit location",request.getUser());

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        LOGGER.info("User: {} cannot get his HomeTransit location. Not valide access",request.getUser());

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    @RequestMapping(value = "searchTransits",method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<PageResponse<SearchTransitResponseDTO>> getTransit(@RequestBody SearchTransitRequestDTO request) {
        PageResponse<SearchTransitResponseDTO> responseDTO= transitService.getTransits(request);
            return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }

    @RequestMapping(value = "searchTransitsDetail",method = RequestMethod.GET)
    @Transactional
    public ResponseEntity<SearchTransitResponseDTO> getTransit(@RequestParam long id) {
        SearchTransitResponseDTO responseDTO= transitService.getTransitDetail(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }

    @RequestMapping(value = "transit-type-location",method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<SearchTransitResponseDTO> getTransitTypeLocation(@RequestParam long id) {
        SearchTransitResponseDTO responseDTO= transitService.getTransitDetail(id);
        return new ResponseEntity<>(responseDTO,HttpStatus.OK);

    }
}
