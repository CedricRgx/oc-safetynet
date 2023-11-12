package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class FireStationController {

    @Autowired
    private FireStationService fireStationService;

    /**
     * This method send the list of firestations via the API (/firestation)
     * @return the list of firestations via API REST
     */
    @GetMapping("/firestation")
    public List<FireStation> getFireStation() {
        return fireStationService.getFireStationService();
    }

    /**
     * This method adds a fire station to the list of fire stations via the API (/persons)
     * @return the list of fire stations with a new fire station via API REST
     */
    @PostMapping("/firestation")
    public List<FireStation> addFireStation(){
        String address = "77 Pommier Street";
        String stationNumber = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation = new ArrayList<>(){{add(personOne);add(personTwo);}};
        fireStationService.addFireStationService(new FireStation(address, stationNumber, personsForThisFireStation));
        return fireStationService.getFireStationService();
    }


    /**
     * This method update a fire station to the list of fire stations via the API (/persons)
     * @return the list of fire stations with an updated fire stations via API REST
     */
    @PutMapping("/firestation")
    public List<FireStation> updateFireStation(){
        String keyToUpdate = "station";
        String valueToUpdate = "9";
        String addressFireStationToUpdate = "77 Pommier Street";
        fireStationService.updateFireStationService(addressFireStationToUpdate, keyToUpdate, valueToUpdate);
        return fireStationService.getFireStationService();
    }

    /**
     * This method delete a fire station from the list of fire stations via the API (/persons)
     * @return the list of fire stations without the removed fire station via API REST
     */
    @DeleteMapping("/firestation")
    public List<FireStation> deleteFireStation(){
        String fireStationToDelete = "77 Pommier Street";
        fireStationService.removeFireStationService(fireStationToDelete);
        return fireStationService.getFireStationService();
    }



/*
    http://localhost:8080/firestation
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
        ajout d'un mapping caserne/adresse ;
        mettre à jour le numéro de la caserne de pompiers d'une adresse ;
        supprimer le mapping d'une caserne ou d'une adresse.
    */

}
