package com.pet.planet.domain.dao.specification;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import com.pet.planet.api.publication.request.SearchPublicationRequestDTO;
import com.pet.planet.domain.dao.model.sql.Publication;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gabrieldyck on 20/05/17.
 */
public class PublicationSpecification {


    public static Specification<Publication> findPublications(SearchPublicationRequestDTO requestDTO) {

        Long publicationId= requestDTO.getPublicationId();
        String name= requestDTO.getName();
        String petClass= requestDTO.getPetClass();
        String petBreed= requestDTO.getPetBreed();
        PublicationType publicationType= requestDTO.getPublicationType();
        String creationDateFrom= requestDTO.getDateFrom();
        String creationDateTo= requestDTO.getDateTo();
        PublicationStatus publicationStatus= requestDTO.getPublicationStatus();
        List<PointDTO> points= requestDTO.getBoundary();


        return new Specification<Publication>() {
            @Override
            public Predicate toPredicate(Root<Publication> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates= new ArrayList<>();

                if(publicationId!=null){
                    predicates.add(cb.equal(root.get("id"),publicationId));
                }

                if(publicationStatus!=null){
                    predicates.add(cb.equal(root.get("status"),publicationStatus));
                }

                if(name!=null){
                    predicates.add(cb.equal(root.get("petName"),name));
                }

                if(petClass!=null){
                    predicates.add(cb.equal(root.get("animalType"),petClass));

                }

                if(points !=null && !points.isEmpty()){

                    List<Float> lats = points.stream().map(PointDTO::getLatitude).collect(Collectors.toList());
                    float maxLat = Collections.max(lats);
                    float minLat = Collections.min(lats);

                    List<Float> lons = points.stream().map(PointDTO::getLongitude).collect(Collectors.toList());
                    float maxLon = Collections.max(lons);
                    float minLon = Collections.min(lons);
                    predicates.add(cb.between(root.get("latitude"),minLat,maxLat));
                    predicates.add(cb.between(root.get("longitude"),minLon,maxLon));

                }

                if(petBreed!=null){
                    predicates.add(cb.equal(root.get("animalBreed"),petBreed));
                }

                if(publicationType!=null){
                    predicates.add(cb.equal(root.get("publicationType"),publicationType));
                }

                if(StringUtils.isNotEmpty(creationDateFrom) && StringUtils.isNotEmpty(creationDateTo)){
                    predicates.add(cb.greaterThanOrEqualTo(root.get("creationDate"),DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(creationDateFrom).withHourOfDay(0).withMinuteOfHour(0).withSecondOfMinute(0)));
                    predicates.add(cb.lessThanOrEqualTo(root.get("creationDate"),DateTimeFormat.forPattern("dd/MM/yyyy").parseDateTime(creationDateTo).withHourOfDay(23).withMinuteOfHour(59).withSecondOfMinute(59)));
                }


                return cb.and(predicates.toArray(new Predicate[predicates.size()-1]));
            }
        };
    }


}
