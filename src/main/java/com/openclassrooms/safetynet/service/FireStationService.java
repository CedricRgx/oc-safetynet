package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
     */
    public void addFireStationService(FireStation fireStation){
        logger.info("Service d'ajout d'une station de pompiers");
        List<FireStation> listOfFireStations = getFireStationService();
        listOfFireStations.add(fireStation);
    }

    /**
     * This method updates the station number of a fire station
     */
    public void updateFireStationService(String addressFireStationToUpdate, String keyToUpdate, String valueToUpdate){
        logger.info("Service de modification d'une station de pompiers");
        String address;
        List<FireStation> FireStationList = getFireStationService();
        for(FireStation fireStation:FireStationList) {
            address = fireStation.getAddress();
            if(addressFireStationToUpdate.equals(address)){
                switch(keyToUpdate){
                    case "station": fireStation.setStationNumber(valueToUpdate); break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * This method removes a fire station in the list of fire stations
     */
    public void removeFireStationService(String fireStationToDelete){
        logger.info("Service de suppression d'une station de pompiers");
        List<FireStation> FireStationList = getFireStationService();
        FireStationList.removeIf(p -> p.getAddress().equals(fireStationToDelete));
    }
}
