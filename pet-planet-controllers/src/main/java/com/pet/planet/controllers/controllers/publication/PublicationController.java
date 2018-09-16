package com.pet.planet.controllers.controllers.publication;

import com.pet.planet.api.publication.GenderStatus;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.PublicationType;
import com.pet.planet.api.publication.SizeStatus;
import com.pet.planet.api.publication.request.CreateCommentRequest;
import com.pet.planet.api.publication.request.CreatePublicationRequestDTO;
import com.pet.planet.api.publication.request.EditPublicationRequestDTO;
import com.pet.planet.api.publication.request.SearchPublicationRequestDTO;
import com.pet.planet.api.publication.response.PageResponse;
import com.pet.planet.api.publication.response.SearchResponseDTO;
import com.pet.planet.controllers.controllers.user.UserController;
import com.pet.planet.domain.dao.model.mongo.KVComment;
import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.dao.specification.PublicationSpecification;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.service.publication.CommentService;
import com.pet.planet.domain.service.publication.PublicationService;
import com.pet.planet.domain.service.user.UserService;
import com.pet.planet.domain.transformer.CommentTransformer;
import com.pet.planet.domain.transformer.PublicationTransformer;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by gabrieldyck on 14/05/17.
 */
@Controller
@CrossOrigin
@RequestMapping("/publication")
public class PublicationController {

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;


    @Autowired
    private PublicationService publicationService;

    private final Logger LOGGER = LoggerFactory.getLogger(PublicationController.class);
    private final int LIMIT=50;

    @ResponseBody
    @RequestMapping(value = "create", method = RequestMethod.POST)
    public ResponseEntity<Long> createPublication(@RequestBody CreatePublicationRequestDTO requestDTO) {

        LOGGER.info("User: {} trying to create a publication {}", requestDTO.getUsername(), requestDTO.toString());
        if (requestDTO.getDescription() != null && requestDTO.getType() != null && requestDTO.getPetClass() != null && requestDTO.getLocation() != null && requestDTO.getTitle() != null) {
            boolean isOk;
            UserAccount userAccount = null;
            if (requestDTO.getUsername() != null) {

                userAccount = userService.findByUsername(requestDTO.getUsername());
                isOk = userService.validateAccessToken(userAccount, requestDTO.getAccessToken());
            } else {
                isOk = true;
            }
            if (isOk) {
                Long publicationId = publicationService.createPublication(requestDTO, userAccount).getId();
                LOGGER.info("User: {} trying  create a publicationId {} successfully", requestDTO.getUsername(), publicationId.toString());

                return new ResponseEntity<>(publicationId, HttpStatus.OK);
            }

        }
        LOGGER.info("User: {} cannot create a publication {}. Because parameter requirement are not success", requestDTO.getUsername(), requestDTO.toString());

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    }


    @ResponseBody
    @RequestMapping(value = "publication-types", method = RequestMethod.GET)
    public List<PublicationType> getPublicationTypes() {
        return Arrays.asList(PublicationType.values());
    }

    @ResponseBody
    @RequestMapping(value = "search", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<PageResponse> searchPublication(@RequestBody SearchPublicationRequestDTO requestDTO) {

        if (requestDTO.getPublicationId() != null || requestDTO.getDateFrom() != null || requestDTO.getDateTo() != null) {
            LOGGER.info("Find publication by specification: " + requestDTO.toString());

            Page<Publication> page = publicationService.findAll(PublicationSpecification.findPublications(requestDTO), new PageRequest(requestDTO.getPage(), requestDTO.getLimit()));

            PageResponse pageResponse = new PageResponse();
            pageResponse.setContent(PublicationTransformer.entitiesToDtos(page.getContent(), false));
            pageResponse.setTotalPages(page.getTotalPages());
            pageResponse.setPage(page.getNumber());
            pageResponse.setLast(page.isLast());

            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "detail")
    public ResponseEntity<SearchResponseDTO> getPublication(@RequestParam("id") Long id) {
        SearchResponseDTO searchResponseDTO = publicationService.getSearchById(id);
        searchResponseDTO.setComments(CommentTransformer.commentToDto(commentService.findCommentsById(id)));
        if (searchResponseDTO != null) {
            return new ResponseEntity<>(searchResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @RequestMapping(value = "getImage", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] getImage(@RequestParam("id") Long id) throws IOException {
        String base64= publicationService.getImageByPublicationId(id);
        BASE64Decoder decoder = new BASE64Decoder();

        InputStream in =  new ByteArrayInputStream(decoder.decodeBuffer(base64));
        return IOUtils.toByteArray(in);
    }

    @ResponseBody
    @RequestMapping(value = "location")
    public ResponseEntity<SearchResponseDTO> getLocation(@RequestParam("id") Long id) {
        SearchResponseDTO searchResponseDTO = publicationService.getLocation(id);
        if (searchResponseDTO != null) {
            return new ResponseEntity<>(searchResponseDTO, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }


    @ResponseBody
    @RequestMapping(value = "createComment", method = RequestMethod.POST)
    public void createComment(@RequestBody CreateCommentRequest comment) {
        comment.setCreationDate(DateTime.now().toDate());
        Long id = comment.getPublicationId();

        synchronized (id) {
            KVComment kvComment = commentService.findKVCommentById(id);

            if (kvComment == null) {
                kvComment = new KVComment();
                kvComment.setPublicationId(id);
                kvComment.setComments(new ArrayList<>());
            }

            kvComment.addComment(CommentTransformer.dtoToComment(comment));
            commentService.save(kvComment);
        }
    }

    @ResponseBody
    @RequestMapping(value = "is-authotize-to-edit-publication", method = RequestMethod.GET)
    public boolean isAuthorizeToEditPublication(@RequestParam String user, @RequestParam String accessToken, @RequestParam Long publicationId) {
        boolean isAuthorize = publicationService.isAuthorizedToEditPublication(user, accessToken, publicationId);
        LOGGER.info("User: {} trying to edit a publicationId {} and is authorized? {}", user, publicationId.toString(), isAuthorize);

        return isAuthorize;
    }


    @ResponseBody
    @RequestMapping(value = "edit-publication", method = RequestMethod.POST)
    public ResponseEntity<Long> editPublication(@RequestBody EditPublicationRequestDTO request) {
        LOGGER.info("User: {} trying to edit a publication {}", request.getUsername(), request.toString());

        if ((request.getDescription() != null && request.getType() != null && request.getPetClass() != null && request.getLocation() != null && request.getTitle() != null) && this.isAuthorizeToEditPublication(request.getUsername(), request.getAccessToken(), request.getId())) {
            UserAccount userAccount = userService.findByUsername(request.getUsername());

            if (userAccount != null) {
                publicationService.saveEdited(request, userAccount);
                LOGGER.info("User: {} edit a publicationId {} successfully", request.getUsername(), request.getId());

                return new ResponseEntity(request.getId(), HttpStatus.OK);
            }
        }
        LOGGER.info("User: {} cannot edit a publicationId {}. Not validate", request.getUsername(), request.getId());

        return new ResponseEntity(HttpStatus.BAD_REQUEST);
    }



    @ResponseBody
    @RequestMapping(value = "getLastPublication", method = RequestMethod.POST)
    @Transactional
    public ResponseEntity<PageResponse> getLastPublication(@RequestBody SearchPublicationRequestDTO requestDTO) {

            Page<Publication> page = publicationService.findAll( new PageRequest(requestDTO.getPage(), LIMIT,new Sort(Sort.Direction.ASC, "id")));

            PageResponse pageResponse = new PageResponse();
            pageResponse.setContent(PublicationTransformer.entitiesToDtos(page.getContent(), false));
            pageResponse.setTotalPages(page.getTotalPages());
            pageResponse.setPage(page.getNumber());
            pageResponse.setLast(page.isLast());

            return new ResponseEntity<>(pageResponse, HttpStatus.OK);
        }

    @ResponseBody
    @RequestMapping(value = "publication-status", method = RequestMethod.GET)
    public List<PublicationStatus> getPublicationStatus() {
        return Arrays.asList(PublicationStatus.values());
    }


    @ResponseBody
    @RequestMapping(value = "animal-gender", method = RequestMethod.GET)
    public List<GenderStatus> getGenders() {
        return Arrays.asList(GenderStatus.values());
    }

    @ResponseBody
    @RequestMapping(value = "animal-size", method = RequestMethod.GET)
    public List<SizeStatus> getAnimalSize() {
        return Arrays.asList(SizeStatus.values());
    }


}
