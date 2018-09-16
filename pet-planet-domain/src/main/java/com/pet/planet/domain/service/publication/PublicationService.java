package com.pet.planet.domain.service.publication;

import com.pet.planet.api.publication.request.CreatePublicationRequestDTO;
import com.pet.planet.api.publication.request.EditPublicationRequestDTO;
import com.pet.planet.api.publication.response.SearchResponseDTO;
import com.pet.planet.domain.dao.repository.sql.PublicationDAO;
import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.service.user.UserService;
import com.pet.planet.domain.transformer.PublicationTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by gabrieldyck on 20/05/17.
 */
@Service
public class PublicationService {

    @Autowired
    private UserService userService;

    @Autowired
    private PublicationDAO publicationDAO;

    private final Logger LOGGER= LoggerFactory.getLogger(PublicationService.class);

    public Publication createPublication(CreatePublicationRequestDTO requestDTO, UserAccount userAccount) {

        return publicationDAO.save(PublicationTransformer.createPublication(requestDTO, userAccount));

    }



    public Publication findById(Long id){
        return publicationDAO.findById(id);
    }


    public void saveEdited(EditPublicationRequestDTO requestDTO, UserAccount userAccount){

         publicationDAO.save(PublicationTransformer.editPublicationTransform(requestDTO, userAccount));

    }



    @Transactional
    public SearchResponseDTO getSearchById(Long id){

        return PublicationTransformer.entityToDTO(publicationDAO.findById(id),true);
    }

    @Transactional
    public String getImageByPublicationId(Long id){

        return PublicationTransformer.getImage(publicationDAO.findById(id));
    }

    @Transactional
    public SearchResponseDTO getLocation(Long id){

        return PublicationTransformer.getLocation(publicationDAO.findById(id));
    }



    public Page<Publication> findAll(Specification<Publication> specification, Pageable pageRequest){
       return  this.publicationDAO.findAll(specification,pageRequest);
    }


    public Page<Publication> findAll(Pageable pageRequest){
        return  this.publicationDAO.findAll(pageRequest);
    }
    public List<Publication> getPublicationsByUserAccountId(long id){
        return this.publicationDAO.findByUserAccount_Id(id);
    }

    @Transactional
    public boolean isAuthorizedToEditPublication(String user, String accessToken, Long publicationId){
        Publication publication= this.findById(publicationId);
        if(publication !=null){
            UserAccount userAccount= publication.getUserAccount();
            if(userAccount.getUsername().equalsIgnoreCase(user)){
                return userService.validateAccessToken(user, accessToken);
            };
        }
        return false;
    }

}

