package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.DTO.*;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The AlertService class is used to set up the datas and the business data for the AlertController
 */
@Service
public class AlertService {
    private static final Logger logger = LogManager.getLogger("AlertService");

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the DTO which contains the list of persons with the number of adults and children from a station number
     * @param stationNumber
     * @return a DTO which contains the list of persons with the number of adults and children
     */
    public PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(String stationNumber) {
        logger.info("Sending service for the list of persons by station number with number of adults and number of children");
        //Get the list of persons with the number of adults and children from a station number
        List<PersonByStationNumberDTO> listOfPersonByStationNumberDTO = getListOfPersonByStationNumberDTO(stationNumber);
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO personsDTO = new PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO();
        List<String> listOfBirthdays = new ArrayList<>();
        //Rretrieve the birthdate of a list of person
        for(PersonByStationNumberDTO personDTO:listOfPersonByStationNumberDTO){
            listOfBirthdays.add(personDTO.getBirthday());
        }
        Calculator calculator = new Calculator();
        //Calculate the number of adults from the list of birthdate
        int nbAdults = calculator.getNumberOfAdults(listOfBirthdays);
        //Calculate the number of children from the list of birthdate
        int nbChildren = calculator.getNumberOfChildren(listOfBirthdays);
        //Set the attributes of personsDTO
        personsDTO.setNumberOfAdults(nbAdults);
        personsDTO.setNumberOfChildren(nbChildren);
        personsDTO.setListOfPersonsByStationNumber(listOfPersonByStationNumberDTO);
        return personsDTO;
    }

    /**
     * This method returns a list of persons who share the same fire station
     * @param stationNumber
     * @return the list of persons who share the same fire station
     */
    public List<PersonByStationNumberDTO> getListOfPersonByStationNumberDTO(String stationNumber){
        logger.info("Creation of the list of people by station number");
        List<FireStation> listOfFireStations = jsonDatabase.getListOfFireStations();
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<PersonByStationNumberDTO> listOfPersonByStationNumberDTO = new ArrayList<>();
        //Retrieve the persons by station number from their address
        for(FireStation fs:listOfFireStations) {
            if(fs.getStationNumber().equals(stationNumber)) {
                for(Person p : listOfPersons) {
                    if(p.getAddress().equals(fs.getAddress())) {
                        PersonByStationNumberDTO person = new PersonByStationNumberDTO();
                        person.setFirstName(p.getFirstName());
                        person.setLastName(p.getLastName());
                        person.setAddress(p.getAddress());
                        person.setPhone(p.getPhone());
                        person.setBirthday(p.getMedicalRecord().getBirthdate());
                        //Put the PersonByStationNumberDTO in the list listOfPersonByStationNumberDTO
                        listOfPersonByStationNumberDTO.add(person);
                    }
                }
            }
        }
        return listOfPersonByStationNumberDTO;
    }

    /**
     * This method returns a list of children with the others members of the household
     * @param address
     * @return the list of children with the others members of the household
     */
    public List<ChildrenWithOthersMembersOfHouseholdDTO> getChildrenWithOthersMembersOfHouseholdService(String address){
        logger.info("");
        List<ChildrenWithOthersMembersOfHouseholdDTO> listOfChildrenWithOthersMembersOfHousehold = new ArrayList<>();
        List<OthersMembersOfTheHouseholdDTO> listOfAdults = new ArrayList<>();
        //Get the list of persons living at the same address
        List<Person> listOfPersonByAddress = getListPersonsByAddress(address);
        String birthday;
        Calculator calculate = new Calculator();
        //Sort the members of a household into children and adults by calculating their ages
        for(Person p:listOfPersonByAddress) {
            birthday = p.getMedicalRecord().getBirthdate();
            if(p.getAddress().equals(address)) {
                if(calculate.isChild(birthday)) {
                    listOfChildrenWithOthersMembersOfHousehold.add(new ChildrenWithOthersMembersOfHouseholdDTO(p.getFirstName(), p.getLastName(), birthday, p.getAddress(), new ArrayList<>()));
                } else {
                    listOfAdults.add(new OthersMembersOfTheHouseholdDTO(p.getFirstName(), p.getLastName(), p.getAddress()));
                }
            }
        }
        //Set the list of adult linked to a child
        for(ChildrenWithOthersMembersOfHouseholdDTO child:listOfChildrenWithOthersMembersOfHousehold){
            List<OthersMembersOfTheHouseholdDTO> listOfOthersMembersOfTheHouseholdDTO = new ArrayList<>();
            for(OthersMembersOfTheHouseholdDTO adult:listOfAdults){
                    listOfOthersMembersOfTheHouseholdDTO.add(adult);
            }
            child.setListOfOthersMembersOfTheHouseholdDTO(listOfOthersMembersOfTheHouseholdDTO);
        }
        return listOfChildrenWithOthersMembersOfHousehold;
    }

    /**
     * This method returns a list of persons who have the same address
     * @param address
     * @return the list of persons who have the same address
     */
    public List<Person> getListPersonsByAddress(String address){
        logger.info("Sending service for the list of persons by address");
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<Person> listOfPersonsByAddress = new ArrayList<>();
        //Retrieve a person from his address
        for(Person p:listOfPersons){
            if(p.getAddress().equals(address)){
                //Add the person to the list of persons living at the same address
                listOfPersonsByAddress.add(p);
            }
        }
        return listOfPersonsByAddress;
    }

    /**
     * This method returns a list of phones of persons living near a firestation
     * @param stationNumber
     * @return the list of phones of persons living near a firestation
     */
    public List<PhonesOfResidentsByFireStationDTO> getPhonesOfResidentsByFireStationService(String stationNumber){
        logger.info("Sending service for the list of phones of persons living near a firestation");
        List<FireStation> listOfFireStations = jsonDatabase.getListOfFireStations();
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<PhonesOfResidentsByFireStationDTO> listOfPhones = new ArrayList<>();
        //Retrieve the address of a person from the station number of a fire station
        for(FireStation fs:listOfFireStations) {
            if(fs.getStationNumber().equals(stationNumber)) {
                for(Person p : listOfPersons) {
                    if(p.getAddress().equals(fs.getAddress())) {
                        PhonesOfResidentsByFireStationDTO phone = new PhonesOfResidentsByFireStationDTO();
                        phone.setPhone(p.getPhone());
                        phone.setStationNumber(fs.getStationNumber());
                        //Add the phone number to the list of phone numbers
                        listOfPhones.add(phone);
                    }
                }
            }
        }
        //Removing duplicate phone numbers from the list to avoid several calls or sms to the same phone number
        listOfPhones = listOfPhones.stream().distinct().collect(Collectors.toList());
        return listOfPhones;
    }



    /**
     * This method returns a list of person living at an address and the fire station number for this address
     * @param address
     * @return a list of person living at an address and the fire station number for this address
     */
    public PersonsByAddressWithFireStationNumberDTO getPersonsByAddressAndFireStationService(String address){
        logger.info("Sending service for the list of person living at an address and the fire station number for this address");
        PersonsByAddressWithFireStationNumberDTO personsByAddressWithFireStationNumberDTO = new PersonsByAddressWithFireStationNumberDTO();
        List<PersonByAddressDTO> listOfPersonsByAddress = new ArrayList<>();

        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        //Retrieve the person from his address
        for(Person p:listOfPersons) {
            if(p.getAddress().equals(address)) {
                PersonByAddressDTO person = new PersonByAddressDTO();
                person.setFirstName(p.getFirstName());
                person.setLastName(p.getLastName());
                person.setAddress(address);
                person.setPhone(p.getPhone());
                person.setAge(new Calculator().calculateAge(p.getMedicalRecord().getBirthdate()));
                person.setMedications(p.getMedicalRecord().getMedications());
                person.setAllergies(p.getMedicalRecord().getAllergies());
                //Put the PersonByAddressDTO in the list listOfPersonsByAddress
                listOfPersonsByAddress.add(person);
            }
        }
        personsByAddressWithFireStationNumberDTO.setListOfPersonsByAddress(listOfPersonsByAddress);

        List<FireStation> listOfFireStations = jsonDatabase.getListOfFireStations();
        List<String> listOfStationNumber = new ArrayList<>();
        //Retrieve the station number of fire station from its address
        for(FireStation fs:listOfFireStations){
            if(fs.getAddress().equals(address)){
                //Put the station number in the list of address of station number
                listOfStationNumber.add(fs.getStationNumber());
            }
        }
        //Add the list of station number in the list of persons personsByAddressWithFireStationNumberDTO
        personsByAddressWithFireStationNumberDTO.setListOfStationNumber(listOfStationNumber);
        return personsByAddressWithFireStationNumberDTO;
    }

    /**
     * This method returns a list of person living at an address and the fire station number for the list of fire stations
     * @param listOfStationNumbers
     * @return a list of person living at an address and the fire station number for the list of fire stations
     */
    public List<PersonByAddressForListOfStationNumbersDTO> getPersonsByStationNumberService(List<String> listOfStationNumbers){
        logger.info("Sending service for the list of person living at an address and the fire station number for the list of fire stations");
        List<PersonByAddressForListOfStationNumbersDTO> listOfPersonByAddressForListOfStationNumbers = new ArrayList<>();

        //Retrieve an address list that matches the station numbers on the fire station list
        List<FireStation> listOfFireStations = jsonDatabase.getListOfFireStations();
        List<String> listOfAddressOfFireStations = new ArrayList<>();
        //Retrieve the address of fire station from its station number
        for(String lsn:listOfStationNumbers){
            for(FireStation fs:listOfFireStations){
                if(fs.getStationNumber().equals(lsn)){
                    //Put the address in the list of address of fire station
                    listOfAddressOfFireStations.add(fs.getAddress());
                }
            }
        }
        //Retrieve the values of the PersonByAddressForListOfStationNumbersDTO attributes from an address of fire station
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        for(String afs:listOfAddressOfFireStations) {
            for(Person p : listOfPersons) {
                if(p.getAddress().equals(afs)) {
                    PersonByAddressForListOfStationNumbersDTO person = new PersonByAddressForListOfStationNumbersDTO();
                    person.setAddress(p.getAddress());
                    person.setFirstName(p.getFirstName());
                    person.setLastName(p.getLastName());
                    person.setPhone(p.getPhone());
                    person.setAge(new Calculator().calculateAge(p.getMedicalRecord().getBirthdate()));
                    person.setMedications(p.getMedicalRecord().getMedications());
                    person.setAllergies(p.getMedicalRecord().getAllergies());
                    //Put the PersonByAddressForListOfStationNumbersDTO in the list listOfPersonByAddressForListOfStationNumbers
                    listOfPersonByAddressForListOfStationNumbers.add(person);
                }
            }
        }
        //Sort the items of the list by address value and age value
        listOfPersonByAddressForListOfStationNumbers.sort(Comparator.comparing(PersonByAddressForListOfStationNumbersDTO::getAddress)
                .thenComparing(PersonByAddressForListOfStationNumbersDTO::getAge));
        return listOfPersonByAddressForListOfStationNumbers;
    }

    /**
     * This method returns a list of informations about a person or several persons (if they shares the same firstname and lastname)
     * @param firstName
     * @param lastName
     * @return a list of informations about a person or several persons
     */
    public List<PersonInfoDTO> getInfoAboutPersonService(String firstName, String lastName){
        logger.info("Sending service for the list of informations about a person");
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<PersonInfoDTO> listOfInfoPerson = new ArrayList<>();
        //Retrieve the values of the PersonInfoDTO attributes for a person from his firstname and lastname
        for(Person p : listOfPersons) {
            if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                PersonInfoDTO infoPerson = new PersonInfoDTO();
                infoPerson.setFirstName(firstName);
                infoPerson.setLastName(lastName);
                infoPerson.setAddress(p.getAddress());
                infoPerson.setAge(new Calculator().calculateAge(p.getMedicalRecord().getBirthdate()));
                infoPerson.setEmail(p.getEmail());
                infoPerson.setMedications(p.getMedicalRecord().getMedications());
                infoPerson.setAllergies(p.getMedicalRecord().getAllergies());
                //Put the PersonInfoDTO in the list listOfInfoPerson
                listOfInfoPerson.add(infoPerson);
            }
        }
        return listOfInfoPerson;
    }

    /**
     * This method returns a list of emails of persons who live in the same city
     * @param city
     * @return the list of emails of persons who live in the same city
     */
    public List<EmailFromPersonsInCityDTO> getEmailFromPersonsInCityService(String city) {
        logger.info("Sending service for the list of emails of persons who live in the same city");
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<EmailFromPersonsInCityDTO> listOfEmailFromPersonsInCity = new ArrayList<>();
        //Retrieve the values of the EmailFromPersonsInCityDTO attributes for a person living in the same city
        for(Person p:listOfPersons) {
            if(p.getCity().equals(city)){
                EmailFromPersonsInCityDTO emailFromPersonsInCityDTO = new EmailFromPersonsInCityDTO();
                emailFromPersonsInCityDTO.setEmail(p.getEmail());
                emailFromPersonsInCityDTO.setCity(city);
                //Put the emailFromPersonsInCityDTO in the list listOfEmailFromPersonsInCity
                listOfEmailFromPersonsInCity.add(emailFromPersonsInCityDTO);
            }
        }
        return listOfEmailFromPersonsInCity;
    }
}