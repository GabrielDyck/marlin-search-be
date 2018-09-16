package com.pet.planet.controllers.controllers.interfaces;

import com.pet.planet.api.AnimalAge;
import com.pet.planet.api.publication.PublicationType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by gabrieldyck on 19/06/17.
 */
@Controller
@CrossOrigin
@RequestMapping("/interface")
public class Interface {

    @ResponseBody
    @RequestMapping(value = "/publication-types",method = RequestMethod.GET)
    public List<PublicationType> getPublicationTypes() {

        return Arrays.asList(PublicationType.values());
    }

    @ResponseBody
    @RequestMapping(value = "/dog-breed",method = RequestMethod.GET)
    public List<String> getDogBreed() {

        return Arrays.asList(new String[]{"Bichon Habanero", "Boston Terrier", "Cairn Terrier", "Caniche", "Carlino",
                "Cavalier King Charles", "Chin Japones", "Shih Tzu", "Scottish Terrier", "Schnauzer", "Chinese Crested",
                "Bichon Maltes", "Lebrel Italiano", "Chihuahua", "Pequines", "Lhasa Apso", "Jack Rusel Terrier", "Coton de Tulear",
                "Terrier Ingles", "Pincher", "Spaniel Continental", "Teckel", "Airedale Terrier", "Antiguo Pastor Ingles", "Fox Terrier",
                "Yorkshire Terrier", "Bulldog Frances", "Pomeranio", "Rottweiler", "Bull Terrier", "Bulldog Ingles", "Grifon de Bruselas",
                "Beagle Pelo Corto", "Basset Hound", "American Staffordshire", "Australian Cattle", "Puli", "Bearded Collie", "Kerry Blue Terrier",
                "Border Collie", "Braco Frances", "Salchicha", "Cocker Spaniel Ingles", "Dalmata", "Epagneul Breton", "Chow-Chow", "Cocker Spaniel Americano",
                "Pastor Catalan", "Pastor Escoces", "Perro De Agua Español", "Boxer", "Setter Ingles", "Setter Irlandes", "Shar Pei", "Podenco Ibicenco",
                "Staffordshire Bull Terrier", "Akita", "Borzoi", "Bouvier De Flandes", "Bracoo Hungaro", "Alaskan Malute", "Husky Siberiano",
                "Deutch Drathaar", "Braco Weimar", "Galgo Español", "Golden Retriever", "Gordon Setter", "Gran Boyero Suizo", "Lebrel Ingles",
                "Doberman", "Eurasier", "Samoyedo", "Pastor Frances", "Retriever Labrador", "Pastor Aleman", "Pastor Brie", "Terranova", "Pastor Belga",
                "Dogo Argentino", "San Bernardo", "Rhodesian Ridgeback", "Fila Brasileño", "Mastin Pirineo", "Perro de Presa Canario", "Dogo de Burdeos", "Mastin Napolitano",
                "Dogo Aleman"});
    }


    @ResponseBody
    @RequestMapping(value = "/cat-breed",method = RequestMethod.GET)
    public List<String> getCatBreed() {

        return Arrays.asList(new String[]{"Mau Egipcio", "Abisinio", "Americano Pelo Corto", "Azul Ruso", "Balines", "Birmano", "Highland Fold", "Bombay", "Bengali",
                "Bosques de Noruega", "Britanico", "Curl Americano", "California Splanged", "Manx", "Devon Rex", "Burmes", "Habana Cafe", "Singapura",
                "Americano de Pelo Duro", "Himalaya", "Persa Exotico", "Esfinge", "Fold Escoces", "Chatreux", "Angora Turco", "Ocicat", "Pelo Corto Exotico", "Selkirk Rex",
                "Persa", "Oriental Pelo Corto", "Europeo Comun", "Ragdoll", "Siames", "Siberiano", "Snowsoe", "Korata", "Japones Bobtail", "Van Turco", "Somali", "Maine Coon"});
    }

    @ResponseBody
    @RequestMapping(value = "/pet-class",method = RequestMethod.GET)
    public List<String> getPetClass() {

        return Arrays.asList(new String[]{"Gato", "Perro", "Ave", "Pez"});
    }



    @ResponseBody
    @RequestMapping(value = "/animal-age",method = RequestMethod.GET)
    public List<AnimalAge> getAnimalAge() {

        return Arrays.asList(AnimalAge.values());
    }

}
