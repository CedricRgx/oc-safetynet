package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        logger.info("Sending service of the list of persons");
        return jsonDatabase.getListOfPersons();
    }

    /**
     * This method adds a person to the list of persons
     * @param person person to add
     * @return person added
     */
    public Person addPersonService(Person person){
        logger.info("Adding service of a person");
        List<Person> personList = getPersonsService();
        //Add a new person to the list of persons
        personList.add(person);
        jsonDatabase.setListOfPersons(personList);
        return person;
    }

    /**
     * This method updates one of the attributes of a person
     * @param person person to update
     * @return person updated or null
     */
    public Person updatePersonService(Person person){
        logger.info("Updating service of a person");
        List<Person> personList = getPersonsService();
        //Retrieve the person to update from his firstname and his lastname
        Optional<Person> personOptional = personList.stream().filter(p -> p.getFirstName().equals(person.getFirstName()) && p.getLastName().equals(person.getLastName())).findAny();
        if(personOptional.isPresent()){
            logger.info("Update person");
            Person personUpdate = personOptional.get();
            //Update the phone, zip code, address, city, email and medical records of the person to update
            personUpdate.setPhone(person.getPhone());
            personUpdate.setZip(person.getZip());
            personUpdate.setAddress(person.getAddress());
            personUpdate.setCity(person.getCity());
            personUpdate.setEmail(person.getEmail());
            personUpdate.setMedicalRecord(person.getMedicalRecord());
        }else{
            return null; //return null because the first and last name of the person to update were not found
        }
        //Update of the list of person with the updated person
        jsonDatabase.setListOfPersons(personList);
        return person;
    }

    /**
     * This method removes a person in the list of persons
     * @param firstName
     * @param lastName
     * @return true if the person is not in the list (success of deletion)
     */
    public boolean removePersonService(String firstName, String lastName){
        logger.info("Deleting service of a person");
        List<Person> personList = getPersonsService();
        //Delete the person from his firstname and his lastname
        return personList.removeIf(p -> p.getFirstName().equals(firstName) && p.getLastName().equals(lastName));
    }
}