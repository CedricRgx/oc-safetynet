package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The PersonController class is used to manage /persons endpoints and interact with the service PersonService
 */
@RestController
@Slf4j
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * This method send the list of persons via the API (/persons)
     * @return the list of persons via API REST
     */
    @GetMapping("/person")
    public ResponseEntity<List<Person>> getPersons() {
        log.info("GET request on the endpoint /persons: getting the list of persons");
        log.debug("GET request on the endpoint /persons: getting the list of persons");
        List<Person> personList = personService.getPersonsService();
        if(personList.isEmpty()){
            log.error("Error getting the list of persons");
            return new ResponseEntity<List<Person>>(personList, HttpStatus.NOT_FOUND);
        }else{
            log.info("Success getting the list of persons");
            return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
        }
    }

    /**
     * This method adds a person to the list of persons via the API (/persons)
     * @param personToAdd person to add
     * @return the person added via API REST and the status code
     */
    @PostMapping("/person")
    public ResponseEntity<Person> addPerson(@RequestBody Person personToAdd){
        log.info("POST request on the endpoint /persons: adding a person");
        Person personAdded = personService.addPersonService(personToAdd);
        if(personAdded == null){
            log.error("Error adding person");
            return new ResponseEntity<Person>(personAdded, HttpStatus.BAD_REQUEST);
        }else {
            log.info("Success adding person");
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
        log.info("PUT request on the endpoint /persons: updating a person");
        Person personUpdated = personService.updatePersonService(personToUpdate);
        if(personUpdated == null){
            log.error("Error updating person");
            return new ResponseEntity<Person>(personUpdated, HttpStatus.NOT_FOUND);
        }else {
            log.info("Success updating person");
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
        log.info("DELETE request on the endpoint /persons: deleting a person");
        boolean isDeleted = personService.removePersonService(firstName, lastName);
        if(isDeleted){
            log.info("Success deleting person");
            return new ResponseEntity(HttpStatus.OK);
        }else {
            log.error("Error deleting person");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
