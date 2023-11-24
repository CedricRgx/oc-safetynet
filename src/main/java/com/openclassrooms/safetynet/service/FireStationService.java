package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The FireStationService class is used to return datas to the controller FireStationController
 */
@Service
public class FireStationService {

    private static final Logger logger = LogManager.getLogger("FireStationService");

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of fire station
     * @return the list of fire station
     */
    public List<FireStation> getFireStationService(){
        logger.info("Service d'envoi de la liste des stations de pompiers");
        List<FireStation> listOfFireStation = jsonDatabase.getListOfFireStations();
        return listOfFireStation;
    }

    /**
     * This method adds a fire station to the list of fire stations
     * @param fireStation fire station to add
     * @return fire station added
     */
    public FireStation addFireStationService(FireStation fireStation){
        logger.info("Service d'ajout d'une station de pompiers");
        List<FireStation> fireStationList = getFireStationService();
        fireStationList.add(fireStation);
        jsonDatabase.setListOfFireStations(fireStationList);
        return fireStation;
    }

    /**
     * This method updates the station number of a fire station
     * @param fireStation fire station to update
     * @return fire station updated or null
     */
    public FireStation updateFireStationService(FireStation fireStation){
        logger.info("Service de modification d'une station de pompiers");
        List<FireStation> fireStationList = getFireStationService();
        Optional<FireStation> fireStationOptional = fireStationList.stream().filter(p -> p.getAddress().equals(fireStation.getAddress())).findAny();
        if(fireStationOptional.isPresent()){
            FireStation fireStationUpdate = fireStationOptional.get();
            //Mise à jour des champs
            fireStationUpdate.setStationNumber(fireStation.getStationNumber());
        }else{
            return null; //return null because the address of the fire station hasn't founded
        }
        jsonDatabase.setListOfFireStations(fireStationList);
        return fireStation;
    }

    /**
     * This method removes a fire station in the list of fire stations
     * @param address and stationNumber of the fire station to delete
     * @return true if the fire station is not in the list (success of deletion)
     */
    public boolean removeFireStationService(String address, String stationNumber){
        logger.info("Service de suppression d'une station de pompiers");
        List<FireStation> fireStationList = getFireStationService();
        return !fireStationList.removeIf(p -> p.getAddress().equals(address) && p.getStationNumber().equals(stationNumber));
    }
}
