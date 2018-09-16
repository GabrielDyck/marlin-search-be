package com.pet.planet.domain.transformer;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.transit.request.CreateHomeTransitRequestDTO;
import com.pet.planet.api.transit.request.CreateTransitTypeRequestDTO;
import com.pet.planet.api.transit.response.HomeTransitResponseDTO;
import com.pet.planet.api.transit.response.SearchTransitResponseDTO;
import com.pet.planet.api.transit.response.TransitTypeResponseDTO;
import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.dao.model.sql.TransitType;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.dao.repository.sql.HomeTransitDAO;
import com.pet.planet.domain.dao.repository.sql.TransitTypeDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class HomeTransitTransformer {

    @Autowired
    private TransitTypeDAO transitTypeDAO;


    public HomeTransit transform(CreateHomeTransitRequestDTO request, UserAccount user) {
        List<Long> idsForSave = request.getTransitTypes().stream().map(createTransitTypeRequestDTO -> createTransitTypeRequestDTO.getId()).collect(Collectors.toList());


        HomeTransit homeTransit = user.getHomeTransit();
        if (homeTransit == null) {
            homeTransit = new HomeTransit();
            homeTransit.setUser(user);
            homeTransit.setTransitTypes(new ArrayList<>());
        }
        List<TransitType> transitTypes = homeTransit.getTransitTypes();


        homeTransit.setMovility(request.isHasMovility());
        homeTransit.setUser(user);
        homeTransit.setLatitude(request.getLatitude());
        homeTransit.setLongitude(request.getLongitude());
        homeTransit.setContactName(request.getContactName());
        homeTransit.setMail(request.getEmail());
        homeTransit.setPhone(request.getPhone());
        for (CreateTransitTypeRequestDTO typeRequest : request.getTransitTypes()) {

            Long typeId = typeRequest.getId();
            TransitType transitType;

            if (typeId == null) {
                transitType = new TransitType();
                transitTypes.add(transitType);
            } else {
                Optional<TransitType> transitTypeOptional = transitTypes.stream().filter(type -> type.getId() == typeId).findFirst();
                if (transitTypeOptional.isPresent()) {
                    transitType = transitTypeOptional.get();
                } else {
                    throw new RuntimeException("TransitType edit is not owner for the transit id");
                }
            }
            transitType.setDescription(typeRequest.getDescription());
            transitType.setAnimalAge(typeRequest.getAnimalAge());
            transitType.setAnimalType(typeRequest.getAnimalType());
            transitType.setAnimalBreed(typeRequest.getAnimalBreed());
            transitType.setHomeTransit(homeTransit);
        }


        List<TransitType> transitForDelete = transitTypes.stream().filter(transitType -> !idsForSave.contains(transitType.getId()) && transitType.getId() != 0L).collect(Collectors.toList());
        transitTypes.removeAll(transitForDelete);
        transitForDelete.forEach(transitType -> transitTypeDAO.delete(transitType));

        return homeTransit;
    }

    public HomeTransitResponseDTO transform(HomeTransit homeTransit) {

        HomeTransitResponseDTO response = new HomeTransitResponseDTO();
        List<TransitTypeResponseDTO> transitResponseList = new ArrayList<>();

        if(homeTransit !=null){
        response.setContactName(homeTransit.getContactName());
        response.setId(homeTransit.getId());
        response.setLatitude(homeTransit.getLatitude());
        response.setLongitude(homeTransit.getLongitude());
        response.setMail(homeTransit.getMail());
        response.setMovility(homeTransit.isMovility());
        response.setPhone(homeTransit.getPhone());
        homeTransit.getTransitTypes().forEach(transitType -> {
            TransitTypeResponseDTO transitResponse = new TransitTypeResponseDTO();
            transitResponse.setId(transitType.getId());
            transitResponse.setAnimalAge(transitType.getAnimalAge());
            transitResponse.setAnimalBreed(transitType.getAnimalBreed());
            transitResponse.setAnimalType(transitType.getAnimalType());
            transitResponse.setDescription(transitType.getDescription());
            transitResponseList.add(transitResponse);

        });

        }
        response.setTransitTypes(transitResponseList);
        return response;
    }


    public List<SearchTransitResponseDTO> transform(List<TransitType> transits) {
        return transits.stream().map(transit ->
            transformTransit(transit)
        ).collect(Collectors.toList());


    }

    public SearchTransitResponseDTO transformTransit(TransitType transit) {
        SearchTransitResponseDTO response = new SearchTransitResponseDTO();
        response.setAnimalAge(transit.getAnimalAge());
        response.setAnimalBreed(transit.getAnimalBreed());
        response.setAnimalType(transit.getAnimalType());
        response.setContactName(transit.getHomeTransit().getContactName());
        response.setDescription(transit.getDescription());
        response.setLocation(new PointDTO(transit.getHomeTransit().getLatitude(), transit.getHomeTransit().getLongitude()));
        response.setMail(transit.getHomeTransit().getMail());
        response.setPhone(transit.getHomeTransit().getPhone());
        response.setTransitId(transit.getId());
        return response;
    }

}
