package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return fireStationService.getFireStation();
    }
    /*
    http://localhost:8080/firestation
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete avec HTTP :
        ajout d'un mapping caserne/adresse ;
        mettre à jour le numéro de la caserne de pompiers d'une adresse ;
        supprimer le mapping d'une caserne ou d'une adresse.
    */

}
