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

import java.util.*;
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
     * This method returns a list of persons for an address
     * @param address to retrieve the list of persons
     * @return the list of persons who have the same address
     */
    public List<Person> getListOfPersonsByAddress(String address){
        logger.info("Retrieve a list of person who have the same address");
        logger.debug("Retrieve a list of person who have the same address");
        return jsonDatabase.getListOfPersons()
                .stream()
                .filter(p -> p.getAddress()
                        .equals(address))
                .toList();
    }

    /**
     * This method returns a list of persons for a city
     * @param city to retrieve the list of persons
     * @return the list of persons who live in the same city
     */
    public List<String> getListOfPersonsByCity(String city){
        logger.info("Retrieve a list of person who have the same address");
        logger.debug("Retrieve a list of person who have the same address");
        return jsonDatabase.getListOfPersons()
                .stream()
                .filter(p -> city.equals(p.getCity()) && p.getEmail() != null) // Null check to avoid NullPointerException
                .map(Person::getEmail)
                .collect(Collectors.toList());
    }

    /**
     * This method returns a list of firestations from a station number
     * @param stationNumber to retrieve the list of fire stations
     * @return the list of fire stations which have the same station number
     */
    public List<FireStation> getListOfFireStationsByStationNumber(String stationNumber){
        logger.info("Retrieve a list of fire stations which have the same station number");
        logger.debug("Retrieve a list of fire stations which have the same station number");
        return jsonDatabase.getListOfFireStations()
                .stream()
                .filter(f -> f.getStationNumber()
                        .equals(stationNumber))
                .toList();
    }

    /**
     * This method returns a list of firestations from an address
     * @param address to retrieve the list of fire stations
     * @return the list of fire stations which have the same address
     */
    public List<FireStation> getListOfFireStationsByAddress(String address){
        logger.info("Retrieve a list of fire stations which have the same address");
        logger.debug("Retrieve a list of fire stations which have the same address");
        return jsonDatabase.getListOfFireStations()
                .stream()
                .filter(f -> f.getAddress()
                        .equals(address))
                .toList();
    }

    /**
     * This method returns a list of persons who share the same fire station
     * @param stationNumber for the list of persons who share the same fire station
     * @return the list of persons who share the same fire station
     */
    public List<PersonByStationNumberDTO> getListOfPersonByStationNumberDTO(String stationNumber){
        logger.info("Creation of the list of people by station number");
        logger.debug("Creation of the list of people by station number");
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<PersonByStationNumberDTO> listOfPersonByStationNumberDTO = new ArrayList<>();
        List<FireStation> listOfFireStationsByStationNumber = getListOfFireStationsByStationNumber(stationNumber);

        //Retrieve the persons by station number from their address
        for(FireStation fs:listOfFireStationsByStationNumber) {
            for(Person p:listOfPersons) {
                if(p.getAddress().equals(fs.getAddress())) {
                    //Put the PersonByStationNumberDTO in the list listOfPersonByStationNumberDTO
                    listOfPersonByStationNumberDTO.add(PersonByStationNumberDTO.builder()
                            .firstName(p.getFirstName())
                            .lastName(p.getLastName())
                            .address(p.getAddress())
                            .phone(p.getPhone())
                            .birthday(p.getMedicalRecord().getBirthdate()).build());
                }
            }
        }
        return listOfPersonByStationNumberDTO;
    }

    /**
     * This method returns the DTO which contains the list of persons with the number of adults and children from a station number
     * @param stationNumber for the list of persons
     * @return a DTO which contains the list of persons with the number of adults and children
     */
    public PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(String stationNumber) {
        logger.info("Sending service for the list of persons by station number with number of adults and number of children");
        logger.debug("Sending service for the list of persons by station number with number of adults and number of children");
        //Get the list of persons with the number of adults and children from a station number
        List<PersonByStationNumberDTO> listOfPersonByStationNumberDTO = getListOfPersonByStationNumberDTO(stationNumber);
        List<String> listOfBirthdays = new ArrayList<>();
        //Retrieve the birthdate of a list of person
        for(PersonByStationNumberDTO personDTO:listOfPersonByStationNumberDTO){
            listOfBirthdays.add(personDTO.getBirthday());
        }
        Calculator calculator = new Calculator();
        //Calculate the number of adults from the list of birthdate
        int nbAdults = calculator.getNumberOfAdults(listOfBirthdays);
        //Calculate the number of children from the list of birthdate
        int nbChildren = calculator.getNumberOfChildren(listOfBirthdays);
        //Return an DTO object with the attributes
        return PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO.builder()
                .numberOfAdults(nbAdults)
                .numberOfChildren(nbChildren)
                .listOfPersonsByStationNumber(listOfPersonByStationNumberDTO).build();
    }

    /**
     * This method returns a list of children with the others members of the household
     * @param address of the household
     * @return the list of children with the others members of the household
     */
    public List<ChildrenWithOthersMembersOfHouseholdDTO> getChildrenWithOthersMembersOfHouseholdService(String address){
        logger.info("Creation of the list of children with the others members of the household");
        logger.debug("Creation of the list of children with the others members of the household");
        List<ChildrenWithOthersMembersOfHouseholdDTO> listOfChildrenWithOthersMembersOfHousehold = new ArrayList<>();
        Calculator calculate = new Calculator();
        List<OthersMembersOfTheHouseholdDTO> listOfOtherMemberHouseHold;
        //Get the list of persons living at the same address
        List<Person> listOfPersonByAddress = getListOfPersonsByAddress(address);

        //Sort the members of a household into children and adults by calculating their ages
        for(Person p:listOfPersonByAddress) {
            //Identify a child of the list of persons
            if(calculate.isChild(p.getMedicalRecord().getBirthdate())) {
                listOfOtherMemberHouseHold = new ArrayList<>();
                String firstNameChild = p.getFirstName();
                String lastNameChild = p.getLastName();

                //Put the others members of household in another list
                for(Person m:listOfPersonByAddress){
                    if(!(m.getFirstName().equals(firstNameChild) && m.getLastName().equals(lastNameChild))){
                        listOfOtherMemberHouseHold.add(OthersMembersOfTheHouseholdDTO.builder()
                                .firstName(m.getFirstName())
                                .lastName(m.getLastName())
                                .address(m.getAddress()).build());
                    }
                }
                //Initialize the child DTO and put the list of others members of the household as an attribute of the child DTO
                listOfChildrenWithOthersMembersOfHousehold.add(ChildrenWithOthersMembersOfHouseholdDTO.builder()
                        .firstName(firstNameChild)
                        .lastName(lastNameChild)
                        .age(calculate.calculateAge(p.getMedicalRecord().getBirthdate()))
                        .address(p.getAddress())
                        .listOfOthersMembersOfTheHouseholdDTO(listOfOtherMemberHouseHold).build());
            }
        }
        return listOfChildrenWithOthersMembersOfHousehold;
    }

    /**
     * This method returns a list of phones of persons living near a firestation
     * @param stationNumber of the firestation
     * @return the list of phones of persons living near a firestation
     */
    public Set<String> getPhonesOfResidentsByFireStationService(String stationNumber){
        logger.info("Sending service for the list of phones of persons living near a firestation");
        logger.debug("Sending service for the list of phones of persons living near a firestation");
        List<FireStation> listOfFireStationsByStationNumber = getListOfFireStationsByStationNumber(stationNumber);
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        Set<String> listOfPhones = new HashSet<>();
        //Retrieve the address of a person from the station number of a fire station
        for(FireStation fs:listOfFireStationsByStationNumber) {
            for(Person p : listOfPersons) {
                if(p.getAddress().equals(fs.getAddress())) {
                    //Add the phone number to the list of phone numbers
                    listOfPhones.add(p.getPhone());
                }
            }
        }
        return listOfPhones;
    }

    /**
     * This method returns a list of person living at an address and the fire station number for this address
     * @param address of a list of persons and a firestation
     * @return a list of person living at an address and the fire station number for this address
     */
    public PersonsByAddressWithFireStationNumberDTO getPersonsByAddressAndFireStationService(String address){
        logger.info("Sending service for the list of person living at an address and the fire station number for this address");
        logger.debug("Sending service for the list of person living at an address and the fire station number for this address");
        List<PersonByAddressDTO> listOfPersonsByTheSameAddress = new ArrayList<>();
        Calculator calculate = new Calculator();
        List<Person> listOfPersonsByAddress = getListOfPersonsByAddress(address);
        String stationNumber = null;
        //Retrieve the person from his address
        for(Person p:listOfPersonsByAddress) {
            //Put the PersonByAddressDTO in the list listOfPersonsByAddress
            listOfPersonsByTheSameAddress.add(PersonByAddressDTO.builder()
                .firstName(p.getFirstName())
                .lastName(p.getLastName())
                .address(p.getAddress())
                .phone(p.getPhone())
                .age(calculate.calculateAge(p.getMedicalRecord().getBirthdate()))
                .medications(p.getMedicalRecord().getMedications())
                .allergies(p.getMedicalRecord().getAllergies())
                .build()
            );
        }
        Optional<FireStation> fireStationOptional = jsonDatabase.getListOfFireStations()
                .stream()
                .filter(p -> p.getAddress().equals(address)).findAny();
        if(fireStationOptional.isPresent()){
            stationNumber = fireStationOptional.get().getStationNumber();
        }
        //Add the list of station number in the list of persons personsByAddressWithFireStationNumberDTO
        return PersonsByAddressWithFireStationNumberDTO.builder()
                .listOfPersonsByAddress(listOfPersonsByTheSameAddress)
                .stationNumber(stationNumber).build();
    }

    /**
     * This method returns a list of person living at an address and the fire station number for the list of fire stations
     * @param listOfStationNumbers for a list of person
     * @return a list of person living at an address and the fire station number for the list of fire stations
     */
    public List<PersonByAddressDTO> getPersonsByStationNumberService(List<String> listOfStationNumbers){
        logger.info("Sending service for the list of person living at an address and the fire station number for the list of fire stations");
        logger.debug("Sending service for the list of person living at an address and the fire station number for the list of fire stations");
        List<PersonByAddressDTO> listOfPersonByAddressForListOfStationNumbers = new ArrayList<>();

        //Retrieve an address list that matches the station numbers on the fire station list
        List<FireStation> listOfFireStations = jsonDatabase.getListOfFireStations();
        List<String> listOfAddressOfFireStations = new ArrayList<>();
        Calculator calculate = new Calculator();
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
                    //Put the PersonByAddressForListOfStationNumbersDTO in the list listOfPersonByAddressForListOfStationNumbers
                    listOfPersonByAddressForListOfStationNumbers.add(PersonByAddressDTO.builder()
                            .address(p.getAddress())
                            .firstName(p.getFirstName())
                            .lastName(p.getLastName())
                            .phone(p.getPhone())
                            .age(calculate.calculateAge(p.getMedicalRecord().getBirthdate()))
                            .medications(p.getMedicalRecord().getMedications())
                            .allergies(p.getMedicalRecord().getAllergies())
                            .build());
                }
            }
        }
        //Sort the items of the list by address value and age value
        listOfPersonByAddressForListOfStationNumbers.sort(Comparator.comparing(PersonByAddressDTO::getAddress)
                .thenComparing(PersonByAddressDTO::getAge));
        return listOfPersonByAddressForListOfStationNumbers;
    }

    /**
     * This method returns a list of information about a person or several persons (if they share the same firstname and lastname)
     * @param firstName of a person
     * @param lastName of a person
     * @return a list of information about a person or several persons
     */
    public List<PersonInfoDTO> getInfoAboutPersonService(String firstName, String lastName){
        logger.info("Sending service for the list of informations about a person");
        logger.debug("Sending service for the list of informations about a person");
        List<Person> listOfPersons = jsonDatabase.getListOfPersons();
        List<PersonInfoDTO> listOfInfoPerson = new ArrayList<>();
        //Retrieve the values of the PersonInfoDTO attributes for a person from his firstname and lastname
        for(Person p : listOfPersons) {
            if(p.getFirstName().equals(firstName) && p.getLastName().equals(lastName)) {
                //Put the PersonInfoDTO in the list listOfInfoPerson
                listOfInfoPerson.add(PersonInfoDTO.builder()
                    .firstName(firstName)
                    .lastName(lastName)
                    .address(p.getAddress())
                    .age(new Calculator().calculateAge(p.getMedicalRecord().getBirthdate()))
                    .email(p.getEmail())
                    .medications(p.getMedicalRecord().getMedications())
                    .allergies(p.getMedicalRecord().getAllergies())
                    .build());
            }
        }
        return listOfInfoPerson;
    }

    /**
     * This method returns a list of emails of persons who live in the same city
     * @param city for a list of person live here
     * @return the list of emails of persons who live in the same city
     */
    public List<String> getEmailFromPersonsInCityService(String city) {
        logger.info("Sending service for the list of emails of persons who live in the same city");
        logger.debug("Sending service for the list of emails of persons who live in the same city");
        return getListOfPersonsByCity(city);
    }
}