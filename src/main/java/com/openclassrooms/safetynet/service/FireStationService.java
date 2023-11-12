package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FireStationService {

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of fire station
     * @return the list of fire station
     */
    public List<FireStation> getFireStationService(){
        List<FireStation> listOfFireStation = jsonDatabase.getListOfFireStations();
        return listOfFireStation;
    }

    /**
     * This method adds a fire station to the list of fire stations
     */
    public void addFireStationService(FireStation fireStation){
        List<FireStation> listOfFireStations = getFireStationService();
        listOfFireStations.add(fireStation);
    }

    /**
     * This method updates the station number of a fire station
     */
    public void updateFireStationService(String addressFireStationToUpdate, String keyToUpdate, String valueToUpdate){
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
        List<FireStation> FireStationList = getFireStationService();
        FireStationList.removeIf(p -> p.getAddress().equals(fireStationToDelete));
    }
}
