package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of medical records
     * @return the list of medical records
     */
    public List<MedicalRecord> getMedicalRecord(){
        List<MedicalRecord> listOfMedicalRecord = jsonDatabase.getListOfMedicalRecord();
        return listOfMedicalRecord;
    }
}
