package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.service.FireStationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The FireStationController class is used to manage /firestation endpoints and interact with the service FireStationService
 */
@RestController
public class FireStationController {

    private static final Logger logger = LogManager.getLogger("FireStationController");

    @Autowired
    private FireStationService fireStationService;

    /**
     * This method send the list of firestations via the API (/firestation)
     * @return the list of firestations via API REST and the status code
     */
    @GetMapping("/firestation")
    public ResponseEntity<List<FireStation>> getFireStation() {
        logger.info("Requête GET sur l'endpoint /firestation : envoi de la liste des stations de pompiers");
        List<FireStation> fireStationList = fireStationService.getFireStationService();
        if(fireStationList.isEmpty()){
            logger.error("Erreur lors de l'affichage de la liste de stations de pompiers");
            return new ResponseEntity<List<FireStation>>(fireStationList, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Succès lors de l'affichage de la liste de stations de pompiers");
            return new ResponseEntity<List<FireStation>>(fireStationList, HttpStatus.FOUND);
        }
    }

    /**
     * This method adds a fire station to the list of fire stations via the API (/firestation)
     * @param fireStationtoAdd fire station to add
     * @return the fire station added via API REST and the status code
     */
    @PostMapping("/firestation")
    public ResponseEntity<FireStation> addFireStation(@RequestBody FireStation fireStationtoAdd){
        logger.info("Requête POST sur l'endpoint /firestation : ajout d'une station de pompiers");
        FireStation fireStationAdded = fireStationService.addFireStationService(fireStationtoAdd);
        if(fireStationAdded == null){
            logger.error("Erreur lors de l'ajout de la station de pompiers");
            return new ResponseEntity<FireStation>(fireStationAdded, HttpStatus.BAD_REQUEST);
        }else {
            logger.info("Station de pompiers ajoutée avec succès");
            return new ResponseEntity<FireStation>(fireStationAdded, HttpStatus.CREATED);
        }
    }


    /**
     * This method update a fire station to the list of fire stations via the API (/firestation)
     * @param fireStationToUpdate fire station to update
     * @return the fire station updated via API REST and the status code
     */
    @PutMapping("/firestation")
    public ResponseEntity<FireStation> updateFireStation(@RequestBody FireStation fireStationToUpdate){
        logger.info("Requête PUT sur l'endpoint /firestation : modification d'une station de pompiers");
         FireStation fireStationUpdated = fireStationService.updateFireStationService(fireStationToUpdate);
        if(fireStationUpdated == null){
            logger.error("Erreur lors de la modification de la station de pompiers");
            return new ResponseEntity<FireStation>(fireStationUpdated, HttpStatus.NOT_FOUND);
        }else {
            logger.info("Station de pompiers modifiée avec succès");
            return new ResponseEntity<FireStation>(fireStationUpdated, HttpStatus.OK);
        }
    }

    /**
     * This method delete a fire station from the list of fire stations via the API (/firestation)
     * @param address and stationNumber of the fire station to delete
     * @return the status code
     */
    @DeleteMapping("/firestation")
    public ResponseEntity deleteFireStation(@RequestParam(value="address") String address, @RequestParam(value="stationNumber") String stationNumber){
        logger.info("Requête DELETE sur l'endpoint /firestation : suppression d'une station de pompiers");
        boolean isDeleted = fireStationService.removeFireStationService(address, stationNumber);
        if(isDeleted){
            logger.info("Station de pompiers supprimée avec succès");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {

            logger.error("Erreur lors de la suppression de la station de pompiers");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
