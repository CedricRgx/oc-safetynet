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
        logger.info("Sending service of the list of firestations");
        List<FireStation> listOfFireStation = jsonDatabase.getListOfFireStations();
        return listOfFireStation;
    }

    /**
     * This method adds a fire station to the list of fire stations
     * @param fireStation fire station to add
     * @return fire station added
     */
    public FireStation addFireStationService(FireStation fireStation){
        logger.info("Adding service of a fire station");
        List<FireStation> fireStationList = getFireStationService();
        //Add a new firestation to the list of fire stations
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
        logger.info("Updating service of a fire station");
        List<FireStation> fireStationList = getFireStationService();
        //Retrieve the firestation to update from its address
        Optional<FireStation> fireStationOptional = fireStationList.stream().filter(p -> p.getAddress().equals(fireStation.getAddress())).findAny();
        if(fireStationOptional.isPresent()){
            logger.info("Update firestation");
            FireStation fireStationUpdate = fireStationOptional.get();
            //Update the station number of the firestation to update
            fireStationUpdate.setStationNumber(fireStation.getStationNumber());
        }else{
            return null; //return null because the address of the firestation to update were not found
        }
        //Update of the list of firestation with the updated firestation
        jsonDatabase.setListOfFireStations(fireStationList);
        return fireStation;
    }

    /**
     * This method removes a fire station in the list of fire stations
     * @param address of the firestation to have to remove
     * @param stationNumber of the firestation to have to remove
     * @return true if the fire station is not in the list (success of deletion)
     */
    public boolean removeFireStationService(String address, String stationNumber){
        logger.info("Deleting service of a fire station");
        List<FireStation> fireStationList = getFireStationService();
        //Delete the firestation from its address and its station number
        return fireStationList.removeIf(p -> p.getAddress().equals(address) && p.getStationNumber().equals(stationNumber));
    }
}
