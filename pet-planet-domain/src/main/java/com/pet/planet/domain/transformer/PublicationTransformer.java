package com.pet.planet.domain.transformer;

import com.google.common.collect.Lists;
import com.pet.planet.api.location.PointDTO;
import com.pet.planet.api.publication.PublicationStatus;
import com.pet.planet.api.publication.request.CreatePublicationRequestDTO;
import com.pet.planet.api.publication.request.EditPublicationRequestDTO;
import com.pet.planet.api.publication.response.SearchResponseDTO;
import com.pet.planet.domain.dao.model.sql.Image;
import com.pet.planet.domain.dao.model.sql.Publication;
import com.pet.planet.domain.dao.model.sql.UserAccount;
import com.pet.planet.domain.utils.ImageResizer;
import org.joda.time.DateTime;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by gabrieldyck on 20/05/17.
 */

public class PublicationTransformer {

    private static  int scaledWidth = 600;
    private static int scaledHeight = 600;

    public static List<SearchResponseDTO> entitiesToDtos(List<Publication> publications,boolean allPhotos) {
        return publications.stream().map(publication -> entityToDTO(publication,allPhotos)).collect(Collectors.toList());
    }


    public static Publication createPublication(CreatePublicationRequestDTO requestDTO, UserAccount userAccount) {

        Publication publication = new Publication();

        publication.setStatus(PublicationStatus.OPEN);

        return transformToPublication(requestDTO, userAccount, publication);
    }

    private static Publication transformToPublication(CreatePublicationRequestDTO requestDTO, UserAccount userAccount, Publication publication) {
        publication.setTitle(requestDTO.getTitle());
        publication.setEmail(requestDTO.getEmail());
        publication.setContactName(requestDTO.getContactName());
        publication.setPhone(requestDTO.getPhone());
        publication.setCreationDate(DateTime.now());
        publication.setColor(requestDTO.getColor());
        publication.setSize(requestDTO.getSize());
        publication.setStringLocation(requestDTO.getStringLocation());
        publication.setAnimalBreed(requestDTO.getPetBreed());
        publication.setAnimalType(requestDTO.getPetClass());
        publication.setPublicationType(requestDTO.getType());
        publication.setDescription(requestDTO.getDescription());
        publication.setLatitude(requestDTO.getLocation().getLatitude());
        publication.setLongitude(requestDTO.getLocation().getLongitude());
        publication.setUserAccount(userAccount);
        publication.setGender(requestDTO.getGender());
        publication.setPetName(requestDTO.getPetName());

        if(requestDTO.getImages() !=null && !requestDTO.getImages().isEmpty()){
            publication.setImages(Lists.newArrayList(requestDTO.getImages().stream().map(imageEncoded ->{

                try {
                    String base64Image = imageEncoded.split(",")[1];
                    byte[] imageBytes = javax.xml.bind.DatatypeConverter.parseBase64Binary(base64Image);
                    ByteArrayInputStream bis = new ByteArrayInputStream(imageBytes);
                    BufferedImage image = ImageIO.read(bis);
                    return new Image(ImageResizer.resize(image,scaledWidth,scaledHeight));

                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }).collect(Collectors.toList())));
        }


        return publication;
    }


    public static Publication editPublicationTransform(EditPublicationRequestDTO request,UserAccount user){

        Publication publication= new Publication();
        publication.setStatus(request.getStatus());
        publication.setId(request.getId());
        return transformToPublication(request,user,publication);

    }


    public static SearchResponseDTO entityToDTO(Publication publication,boolean allPhotos) {

        SearchResponseDTO responseDTO = new SearchResponseDTO();
        responseDTO.setId(publication.getId());
        responseDTO.setEmail(publication.getEmail());
        responseDTO.setContactName(publication.getContactName());
        responseDTO.setDescription(publication.getDescription());
        responseDTO.setLocation(new PointDTO(publication.getLatitude(), publication.getLongitude()));
        responseDTO.setStatus(publication.getStatus());
        responseDTO.setPetBreed(publication.getAnimalBreed());
        responseDTO.setPetClass(publication.getAnimalType());
        responseDTO.setPhone(publication.getPhone());
        responseDTO.setTitle(publication.getTitle());
        responseDTO.setCreationDate(publication.getCreationDate());
        responseDTO.setType(publication.getPublicationType());
        responseDTO.setColor(publication.getColor());
        responseDTO.setSize(publication.getSize());
        responseDTO.setStringLocation(publication.getStringLocation());
        responseDTO.setGender(publication.getGender());
        responseDTO.setPetName(publication.getPetName());



        List<String> photos=new ArrayList<>();

        if(allPhotos){
            publication.getImages().forEach(image -> photos.add(new String(image.getImage())));
        }else{
            if(!publication.getImages().isEmpty()){
                photos.add(new String(publication.getImages().get(0).getImage()));
            }
        }
        responseDTO.setImages(photos);

        return responseDTO;


    }

    public static String getImage(Publication publication) {



            if(!publication.getImages().isEmpty()) {
                return new String(publication.getImages().get(0).getImage());
            }
            else{
                return "";
            }




    }



    public static SearchResponseDTO getLocation(Publication publication) {

        SearchResponseDTO responseDTO = new SearchResponseDTO();

        responseDTO.setLocation(new PointDTO(publication.getLatitude(), publication.getLongitude()));

        return responseDTO;


    }



}
