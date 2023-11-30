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
    @GetMapping("/firestations")
    public ResponseEntity<List<FireStation>> getFireStation() {
        logger.info("GET request on the endpoint /firestations: getting the list of fire stations");
        List<FireStation> fireStationList = fireStationService.getFireStationService();
        if(fireStationList.isEmpty()){
            logger.error("Error getting the list of fire stations");
            return new ResponseEntity<List<FireStation>>(fireStationList, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Success getting the list of fire stations");
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
        logger.info("POST request on the endpoint /firestation: adding a fire station");
        FireStation fireStationAdded = fireStationService.addFireStationService(fireStationtoAdd);
        if(fireStationAdded == null){
            logger.error("Error adding fire station");
            return new ResponseEntity<FireStation>(fireStationAdded, HttpStatus.BAD_REQUEST);
        }else {
            logger.info("Success adding fire station");
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
        logger.info("PUT request on the endpoint /firestation: updating a fire station");
         FireStation fireStationUpdated = fireStationService.updateFireStationService(fireStationToUpdate);
        if(fireStationUpdated == null){
            logger.error("Error updating fire station");
            return new ResponseEntity<FireStation>(fireStationUpdated, HttpStatus.NOT_FOUND);
        }else {
            logger.info("Success updating fire station");
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
        logger.info("DELETE request on the endpoint /firestation: deleting a fire station");
        boolean isDeleted = fireStationService.removeFireStationService(address, stationNumber);
        if(isDeleted){
            logger.info("Success deleting fire station");
            return new ResponseEntity(HttpStatus.OK);
        }else {
            logger.error("Error deleting fire station");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
