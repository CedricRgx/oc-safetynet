package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/persons")
    public List<Person> getPersons() {
        logger.info("Requête GET sur l'endpoint /persons : affichage de la liste des personnes");
        return personService.getPersonsService();
    }

    /**
     * This method adds a person to the list of persons via the API (/persons)
     */
    @PostMapping("/persons")
    public void addPerson(){
        logger.info("Requête POST sur l'endpoint /persons : ajout d'une personne");
        String firstName = "Toto";
        String lastName = "Tata";
        String phone = "333-666-9999";
        String zip = "97451";
        String address = "1509 Culver St";
        String city = "Culver";
        String email = "adresse@email.com";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        personService.addPersonService(new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies)));
    }

    /**
     * This method update a person to the list of persons via the API (/persons)
     */
    @PutMapping("/persons")
    public void updatePerson(){
        logger.info("Requête PUT sur l'endpoint /persons : modification d'une personne");
        String keyToUpdate = "phone";
        String valueToUpdate = "000-000-0000";
        String namePersonToUpdate = "TotoTata";
        personService.updatePersonService(namePersonToUpdate, keyToUpdate, valueToUpdate);
    }

    /**
     * This method delete a person from the list of persons via the API (/persons)
     */
    @DeleteMapping("/persons")
    public void deletePerson(){
        logger.info("Requête DELETE sur l'endpoint /persons : suppression d'une personne");
        String namePersonToDelete = "TotoTata";
        personService.removePersonService(namePersonToDelete);
    }

/*


    http://localhost:8080/person
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
        ajouter une nouvelle personne ;
        mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
        supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique).
    */

}
