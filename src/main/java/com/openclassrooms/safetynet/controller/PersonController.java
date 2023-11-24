package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The PersonController class is used to manage /persons endpoints and interact with the service PersonService
 */
@RestController
public class PersonController {

    private static final Logger logger = LogManager.getLogger("PersonController");

    @Autowired
    private PersonService personService;

    /**
     * This method send the list of persons via the API (/persons)
     * @return the list of persons via API REST
     */
    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPersons() {
        logger.info("Requête GET sur l'endpoint /persons : affichage de la liste des personnes");
        List<Person> personList = personService.getPersonsService();
        if(personList.isEmpty()){
            logger.error("Erreur lors de l'affichage de la liste de personnes");
            return new ResponseEntity<List<Person>>(personList, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Succès lors de l'affichage de la liste de personnes");
            return new ResponseEntity<List<Person>>(personList, HttpStatus.FOUND);
        }
    }

    /**
     * This method adds a person to the list of persons via the API (/persons)
     * @param personToAdd person to add
     * @return the person added via API REST and the status code
     */
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person personToAdd){
        logger.info("Requête POST sur l'endpoint /persons : ajout d'une personne");
        Person personAdded = personService.addPersonService(personToAdd);
        if(personAdded == null){
            logger.error("Erreur lors de l'ajout de la personne");
            return new ResponseEntity<Person>(personAdded, HttpStatus.BAD_REQUEST);
        }else {
            logger.info("Personne ajoutée avec succès");
            return new ResponseEntity<Person>(personAdded, HttpStatus.CREATED);
        }
    }

    /**
     * This method update a person to the list of persons via the API (/persons)
     * @param personToUpdate person to update
     * @return the person updated via API REST and the status code
     *
     */
    @PutMapping("/person")
    public ResponseEntity<Person> updatePerson(@RequestBody Person personToUpdate){
        logger.info("Requête PUT sur l'endpoint /persons : modification d'une personne");
        Person personUpdated = personService.updatePersonService(personToUpdate);
        if(personUpdated == null){
            logger.error("Erreur lors de la modification de la personne");
            return new ResponseEntity<Person>(personUpdated, HttpStatus.NOT_FOUND);
        }else {
            logger.info("Personne modifiée avec succès");
            return new ResponseEntity<Person>(personUpdated, HttpStatus.OK);
        }
    }

    /**
     * This method delete a person from the list of persons via the API (/persons)
     * @param firstName and lastName of the person to delete
     * @return the status code
     */
    @DeleteMapping("/person")
    public ResponseEntity deletePerson(@RequestParam String firstName, @RequestParam String lastName){
        logger.info("Requête DELETE sur l'endpoint /persons : suppression d'une personne");
        boolean isDeleted = personService.removePersonService(firstName, lastName);
        if(isDeleted){
            logger.info("Personne supprimée avec succès");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            logger.error("Erreur lors de la suppression de la personne");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
