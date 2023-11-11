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
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        return listOfPersons;
    }

    /**
     * This method adds a person to the list of persons
     * @return the list of persons with the new person
     */
    public void addPersonService(Person person){
        List<Person> listOfPersons = getPersonsService();
        listOfPersons.add(person);
    }

    /**
     * This method updates the phone of a person in the list of persons
     * @return the updated list of persons
     */
    public void updatePersonService(String phone, String name){
        String namePersonToUpdate = name;
        List<Person> personList = getPersonsService();
        for(Person person:personList) {
            name = person.getFirstName().concat(person.getLastName());
            if(namePersonToUpdate.equals(name)){
                person.setPhone(phone);
            }
        }
    }

    /**
     * This method removes a in the list of persons
     * @return the list of persons without the deleted person
     */
    public void removePersonService(String namePersonToDelete){
        List<Person> personList = getPersonsService();
        personList.removeIf(p -> p.getFirstName().concat(p.getLastName()).equals(namePersonToDelete));
    }

}
