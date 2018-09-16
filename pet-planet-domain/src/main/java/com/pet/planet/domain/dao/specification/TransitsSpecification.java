package com.pet.planet.domain.dao.specification;

import com.pet.planet.api.AnimalAge;
import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import com.pet.planet.api.publication.request.SearchPublicationRequestDTO;
import com.pet.planet.api.transit.request.SearchTransitRequestDTO;
import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.dao.model.sql.TransitType;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.format.DateTimeFormat;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TransitsSpecification {



    public static Specification<TransitType> findTransits(SearchTransitRequestDTO requestDTO) {

        Long transitId= requestDTO.getTransitId();
        AnimalAge animalAge= requestDTO.getAnimalAge();
        String animalType= requestDTO.getAnimalType();
        String animalBreed= requestDTO.getAnimalBreed();
        List<PointDTO> points= requestDTO.getBoundary();


        return new Specification<TransitType>() {
            @Override
            public Predicate toPredicate(Root<TransitType> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates= new ArrayList<>();

                Join<TransitType,HomeTransit> homeTransitJoin= root.join("homeTransit");

                if(transitId!=null){
                    predicates.add(cb.equal(root.get("id"),transitId));
                }

                if(animalBreed!=null){
                    predicates.add(cb.equal(root.get("animalBreed"),animalBreed));

                }

                if(animalType!=null){
                    predicates.add(cb.equal(root.get("animalType"),animalType));

                }

                if(animalAge!=null){
                    predicates.add(cb.equal(root.get("animalAge"),animalAge));

                }

                if(points !=null && !points.isEmpty()){

                    List<Float> lats = points.stream().map(PointDTO::getLatitude).collect(Collectors.toList());
                    float maxLat = Collections.max(lats);
                    float minLat = Collections.min(lats);

                    List<Float> lons = points.stream().map(PointDTO::getLongitude).collect(Collectors.toList());
                    float maxLon = Collections.max(lons);
                    float minLon = Collections.min(lons);
                    predicates.add(cb.between(homeTransitJoin.get("latitude"),minLat,maxLat));
                    predicates.add(cb.between(homeTransitJoin.get("longitude"),minLon,maxLon));

                }

                return cb.and(predicates.toArray(new Predicate[predicates.size()-1]));
            }
        };
    }


}
