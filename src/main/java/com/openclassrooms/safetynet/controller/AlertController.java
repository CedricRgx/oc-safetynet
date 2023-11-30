package com.openclassrooms.safetynet.controller;


import com.openclassrooms.safetynet.DTO.*;
import com.openclassrooms.safetynet.service.AlertService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The AlertController class is used to set up the alerts of safetyNet via API REST and interact with the service AlertService
 */
@RestController
public class AlertController {

    private static final Logger logger = LogManager.getLogger("AlertController");

    @Autowired
    private AlertService alertService;

    /*http://localhost:8080/firestation?stationNumber=<station_number>
    Cette url doit retourner une liste des personnes couvertes par la caserne de pompiers correspondante.
            Donc, si le numéro de station = 1, elle doit renvoyer les habitants couverts par la station numéro 1. La liste
    doit inclure les informations spécifiques suivantes : prénom, nom, adresse, numéro de téléphone. De plus,
    elle doit fournir un décompte du nombre d'adultes et du nombre d'enfants (tout individu âgé de 18 ans ou moins) dans la zone desservie.
*/
    /**
     * This method returns a list of persons by station number with the number of adults and children (under 18) to the endpoint /firestation?stationNumber=<station_number>
     * @param stationNumber
     * @return list of persons group by station number with the number of adults and children (under 18)
     */
    @GetMapping("/firestation")
    public ResponseEntity<PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO> findPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildren(@RequestParam(value="stationNumber") String stationNumber){
        logger.info("GET request on the endpoint /firestation?stationNumber=<station_number>: sending the list of people covered by a fire station");
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO personsDTO = alertService.getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(stationNumber);
        if(personsDTO == null){
            logger.error("Error when displaying the list of people covered by a fire station");
            return new ResponseEntity<PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO>(personsDTO, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the list of people covered by a fire station");
            return new ResponseEntity<PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO>(personsDTO, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/childAlert?address=<address>
    Cette url doit retourner une liste d'enfants (tout individu âgé de 18 ans ou moins) habitant à cette adresse.
    La liste doit comprendre le prénom et le nom de famille de chaque enfant, son âge et une liste des autres
    membres du foyer. S'il n'y a pas d'enfant, cette url peut renvoyer une chaîne vide.
     */
    /**
     * This method returns a list of children (under 18) who live to an address to the endpoint /childAlert?address=<address>
     * @param address
     * @return a list of children (under 18) who live to the address
     */
    @GetMapping("/childAlert")
    public ResponseEntity<List<ChildrenWithOthersMembersOfHouseholdDTO>> findChildrenWithOthersMembersOfHousehold(@RequestParam(value="address") String address){
        logger.info("GET request on the endpoint /childAlert?address=<address>: sending a list of children living at the same address");
        List<ChildrenWithOthersMembersOfHouseholdDTO> listOfChildrenWithOthersMembersOfHousehold = alertService.getChildrenWithOthersMembersOfHouseholdService(address);
        if(listOfChildrenWithOthersMembersOfHousehold == null){
            logger.error("Error when displaying the list of children living at the same address");
            return new ResponseEntity<List<ChildrenWithOthersMembersOfHouseholdDTO>>(listOfChildrenWithOthersMembersOfHousehold, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the list of children living at the same address");
            return new ResponseEntity<List<ChildrenWithOthersMembersOfHouseholdDTO>>(listOfChildrenWithOthersMembersOfHousehold, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/phoneAlert?firestation=<firestation_number>
    Cette url doit retourner une liste des numéros de téléphone des résidents desservis par la caserne de
    pompiers. Nous l'utiliserons pour envoyer des messages texte d'urgence à des foyers spécifiques.
    */
    @GetMapping("/phoneAlert")
    public ResponseEntity<List<PhonesOfResidentsByFireStationDTO>> findPhonesOfResidentsByFireStation(@RequestParam(value="firestation") String stationNumber){
        logger.info("GET request on the endpoint /phoneAlert?firestation=<firestation_number>: sending a list of telephone numbers for residents served by the fire station");
        List<PhonesOfResidentsByFireStationDTO> listOfPhonesOfResidentsByFireStation = alertService.getPhonesOfResidentsByFireStationService(stationNumber);
        if(listOfPhonesOfResidentsByFireStation == null){
            logger.error("Error when displaying the list of telephone numbers for residents served by the fire station");
            return new ResponseEntity<List<PhonesOfResidentsByFireStationDTO>>(listOfPhonesOfResidentsByFireStation, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the list of telephone numbers for residents served by the fire station");
            return new ResponseEntity<List<PhonesOfResidentsByFireStationDTO>>(listOfPhonesOfResidentsByFireStation, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/fire?address=<address>
    Cette url doit retourner la liste des habitants vivant à l’adresse donnée ainsi que le numéro de la caserne
    de pompiers la desservant. La liste doit inclure le nom, le numéro de téléphone, l'âge et les antécédents
    médicaux (médicaments, posologie et allergies) de chaque personne.
    */
    /**
     * This method returns a list of person living at an address and the fire station number for this address to the endpoint /fire?address=<address>
     * @param address
     * @return a list of person living at an address and the fire station number for this address
     */
    @GetMapping("/fire")
    public ResponseEntity<PersonsByAddressWithFireStationNumberDTO> findPersonsByAddressAndFireStation(@RequestParam(value="address") String address){
        logger.info("GET request on the endpoint /fire?address=<address>: sending a list of people to an address served by a fire station");
        PersonsByAddressWithFireStationNumberDTO personsByAddressWithFireStationNumberDTO = alertService.getPersonsByAddressAndFireStationService(address);
        if(personsByAddressWithFireStationNumberDTO == null){
            logger.error("Error when displaying the list of people to an address served by a fire station");
            return new ResponseEntity<PersonsByAddressWithFireStationNumberDTO>(personsByAddressWithFireStationNumberDTO, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the list of people to an address served by a fire station");
            return new ResponseEntity<PersonsByAddressWithFireStationNumberDTO>(personsByAddressWithFireStationNumberDTO, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/flood/stations?stations=<a list of station_numbers>
    Cette url doit retourner une liste de tous les foyers desservis par la caserne. Cette liste doit regrouper les
    personnes par adresse. Elle doit aussi inclure le nom, le numéro de téléphone et l'âge des habitants, et
    faire figurer leurs antécédents médicaux (médicaments, posologie et allergies) à côté de chaque nom.
    */
    /**
     * This method returns a list of person living at an address for a list of station numbers to the endpoint /flood/stations?stations=<a list of station_numbers>
     * @param listOfStationNumbers
     * @return a list of person living at an address for a list of station numbers
     */
    @GetMapping("/flood/stations")
    public ResponseEntity<List<PersonByAddressForListOfStationNumbersDTO>> findPersonsByStationNumber(@RequestParam(value="stations") List<String> listOfStationNumbers){
        logger.info("GET request on the endpoint /flood/stations?stations=<a list of station_numbers>: sending a list of homes served by a fire station");
        List<PersonByAddressForListOfStationNumbersDTO> listOfPersonByAddressForListOfStationNumbersDTO = alertService.getPersonsByStationNumberService(listOfStationNumbers);
        if(listOfPersonByAddressForListOfStationNumbersDTO == null){
            logger.error("Error when displaying the list of homes served by a fire station");
            return new ResponseEntity<List<PersonByAddressForListOfStationNumbersDTO>>(listOfPersonByAddressForListOfStationNumbersDTO, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the list of homes served by a fire station");
            return new ResponseEntity<List<PersonByAddressForListOfStationNumbersDTO>>(listOfPersonByAddressForListOfStationNumbersDTO, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/personInfo?firstName=<firstName>&lastName=<lastName>
    Cette url doit retourner le nom, l'adresse, l'âge, l'adresse mail et les antécédents médicaux (médicaments,
    posologie, allergies) de chaque habitant. Si plusieurs personnes portent le même nom, elles doivent
    toutes apparaître.
    */
    /**
     * This method returns a person to the endpoint /personInfo?firstName=<firstName>&lastName=<lastName>
     * @param firstName
     * @Param lastName
     * @return a person
     */
    @GetMapping("/personInfo")
    public ResponseEntity<List<PersonInfoDTO>> findInfoAboutPerson(@RequestParam(value="firstName") String firstName, @RequestParam(value="lastName") String lastName){
        logger.info("GET request on the endpoint /personInfo?firstName=<firstName>&lastName=<lastName>: sending informations about a person");
        List<PersonInfoDTO> listOfPersonsInfo = alertService.getInfoAboutPersonService(firstName, lastName);
        if(listOfPersonsInfo == null){
            logger.error("Error when displaying the informations about a person");
            return new ResponseEntity<List<PersonInfoDTO>>(listOfPersonsInfo, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the informations about a person");
            return new ResponseEntity<List<PersonInfoDTO>>(listOfPersonsInfo, HttpStatus.FOUND);
        }
    }

    /*http://localhost:8080/communityEmail?city=<city>
    Cette url doit retourner les adresses mail de tous les habitants de la ville.
*/
    /**
     * This method returns a list of emails of persons who live in the same city to the endpoint /communityEmail?city=<city>
     * @param city
     * @return a list of emails of persons who live in the same city
     */
    @GetMapping("/communityEmail")
    public ResponseEntity<List<EmailFromPersonsInCityDTO>> findEmailFromPersonsInCity(@RequestParam(value="city") String city){
        logger.info("GET request on the endpoint /communityEmail?city=<city>: sending the email list");
        List<EmailFromPersonsInCityDTO> listOfEmailsFromPersonsInCityDTO = alertService.getEmailFromPersonsInCityService(city);
        if(listOfEmailsFromPersonsInCityDTO == null){
            logger.error("Error when displaying the email addresses of all the inhabitants of the city");
            return new ResponseEntity<List<EmailFromPersonsInCityDTO>>(listOfEmailsFromPersonsInCityDTO, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success in displaying the email addresses of everyone in the city");
            return new ResponseEntity<List<EmailFromPersonsInCityDTO>>(listOfEmailsFromPersonsInCityDTO, HttpStatus.FOUND);
        }
    }
}
