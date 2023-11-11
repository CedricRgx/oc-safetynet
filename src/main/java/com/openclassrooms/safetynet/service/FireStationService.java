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
    public List<FireStation> getFireStation(){
        List<FireStation> listOfFireStation = jsonDatabase.getListOfFireStations();
        return listOfFireStation;
    }
}
