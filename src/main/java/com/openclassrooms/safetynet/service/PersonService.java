package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The PersonService class is used to return datas to the controller PersonController
 */
@Service
public class PersonService {

    private static final Logger logger = LogManager.getLogger("PersonService");

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of persons
     * @return the list of persons
     */
    public List<Person> getPersonsService(){
        logger.info("Service d'envoi de la liste des personnes");
        return jsonDatabase.getListOfPersons();
    }

    /**
     * This method adds a person to the list of persons
     */
    public void addPersonService(Person person){
        logger.info("Service d'ajout d'une personne");
        List<Person> listOfPersons = getPersonsService();
        listOfPersons.add(person);
    }

    /**
     * This method updates one of the attributes of a person
     */
    public void updatePersonService(String namePersonToUpdate, String keyToUpdate, String valueToUpdate){
        logger.info("Service de modification d'une personne");
        String name;
        List<Person> personList = getPersonsService();
        for(Person person:personList) {
            name = person.getFirstName().concat(person.getLastName());
            if(namePersonToUpdate.equals(name)){
                switch(keyToUpdate){
                    case "phone": person.setPhone(valueToUpdate); break;
                    case "zip": person.setZip(valueToUpdate); break;
                    case "address": person.setAddress(valueToUpdate); break;
                    case "city": person.setCity(valueToUpdate); break;
                    case "email": person.setEmail(valueToUpdate); break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * This method removes a person in the list of persons
     */
    public void removePersonService(String namePersonToDelete){
        logger.info("Service de suppression d'une personne");
        List<Person> personList = getPersonsService();
        personList.removeIf(p -> p.getFirstName().concat(p.getLastName()).equals(namePersonToDelete));
    }

}
