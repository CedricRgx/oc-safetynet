package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * This method send the list of persons via the API (/persons)
     * @return the list of persons via API REST
     */
    @GetMapping("/persons")
    public List<Person> getPersons() {
        return personService.getPersonsService();
    }

    /**
     * This method adds a person to the list of persons via the API (/persons)
     * @return the list of persons with a new person via API REST
     */
    @PostMapping("/persons")
    public List<Person> addPerson(){
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
        return personService.getPersonsService();
    }

    /**
     * This method update a person to the list of persons via the API (/persons)
     * @return the list of persons with an updated person via API REST
     */
    @PutMapping("/persons")
    public List<Person> updatePerson(){
        String keyToUpdate = "phone";
        String valueToUpdate = "000-000-0000";
        String namePersonToUpdate = "TotoTata";
        personService.updatePersonService(namePersonToUpdate, keyToUpdate, valueToUpdate);
        return personService.getPersonsService();
    }

    /**
     * This method delete a person from the list of persons via the API (/persons)
     * @return the list of persons without the removed person via API REST
     */
    @DeleteMapping("/persons")
    public List<Person> deletePerson(){
        String namePersonToDelete = "TotoTata";
        personService.removePersonService(namePersonToDelete);
        return personService.getPersonsService();
    }

/*


    http://localhost:8080/person
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
        ajouter une nouvelle personne ;
        mettre à jour une personne existante (pour le moment, supposons que le prénom et le nom de famille ne changent pas, mais que les autres champs peuvent être modifiés) ;
        supprimer une personne (utilisez une combinaison de prénom et de nom comme identificateur unique).
    */

}
