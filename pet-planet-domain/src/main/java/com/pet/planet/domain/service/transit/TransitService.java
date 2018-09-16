package com.pet.planet.domain.service.transit;

import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.response.PageResponse;
import com.pet.planet.api.transit.request.CreateHomeTransitRequestDTO;
import com.pet.planet.api.transit.request.SearchTransitRequestDTO;
import com.pet.planet.api.transit.response.HomeTransitResponseDTO;
import com.pet.planet.api.transit.response.SearchTransitResponseDTO;
import com.pet.planet.domain.dao.model.sql.HomeTransit;
import com.pet.planet.domain.dao.model.sql.TransitType;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.dao.repository.sql.HomeTransitDAO;
import com.pet.planet.domain.dao.repository.sql.TransitTypeDAO;
import com.pet.planet.domain.dao.specification.TransitsSpecification;
import com.pet.planet.domain.transformer.HomeTransitTransformer;
import com.pet.planet.domain.transformer.PublicationTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransitService {

    @Autowired
    private HomeTransitDAO homeTransitDAO;

    @Autowired
    private TransitTypeDAO transitTypeDAO;

    @Autowired
    private HomeTransitTransformer transitTransformer;


    public HomeTransitResponseDTO createHomeTransit(CreateHomeTransitRequestDTO request, UserAccount user) {
        HomeTransit homeTransit = transitTransformer.transform(request, user);
        homeTransit.getTransitTypes().forEach(transitType -> transitType= transitTypeDAO.save(transitType));
        homeTransitDAO.save(homeTransit);
        return transitTransformer.transform(homeTransit);
    }


    public HomeTransitResponseDTO getHomeTransit(UserAccount user) {
        HomeTransit homeTransit = user.getHomeTransit();
        return transitTransformer.transform(homeTransit);
    }


    public PageResponse<SearchTransitResponseDTO> getTransits(SearchTransitRequestDTO request) {
        Page<TransitType> page= transitTypeDAO.findAll(TransitsSpecification.findTransits(request), new PageRequest(request.getPage(), request.getLimit()));
        PageResponse pageResponse = new PageResponse();
        pageResponse.setContent(transitTransformer.transform(page.getContent()));
        pageResponse.setTotalPages(page.getTotalPages());
        pageResponse.setPage(page.getNumber());
        pageResponse.setLast(page.isLast());
        return pageResponse;
    }

    public SearchTransitResponseDTO getTransitDetail(long id) {
        TransitType transit= transitTypeDAO.findById(id);
        return transitTransformer.transformTransit(transit);
    }

}
