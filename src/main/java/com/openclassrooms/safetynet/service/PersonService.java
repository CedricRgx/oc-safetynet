package com.openclassrooms.safetynet.service;


import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of persons
     * @return the list of persons
     */
    public List<Person> getPersonsService(){
        return jsonDatabase.getListOfPersons();
    }

    /**
     * This method adds a person to the list of persons
     */
    public void addPersonService(Person person){
        List<Person> listOfPersons = getPersonsService();
        listOfPersons.add(person);
    }

    /**
     * This method updates one of the attributes of a person
     */
    public void updatePersonService(String namePersonToUpdate, String keyToUpdate, String valueToUpdate){
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
        List<Person> personList = getPersonsService();
        personList.removeIf(p -> p.getFirstName().concat(p.getLastName()).equals(namePersonToDelete));
    }

}
