package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class MedicalRecordService {

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of medical records
     * @return the list of medical records
     */
    public List<MedicalRecord> getMedicalRecords(){
        List<MedicalRecord> listOfMedicalRecord = jsonDatabase.getListOfMedicalRecord();
        return listOfMedicalRecord;
    }

    /**
     * This method adds a medical record to the list of medical records
     */
    public void addMedicalRecordService(MedicalRecord medicalRecord){
        List<MedicalRecord> listOfMedicalRecords = getMedicalRecords();
        listOfMedicalRecords.add(medicalRecord);
    }

    /**
     * This method updates one of the attributes of a medical record
     */
    public void updateMedicalRecord(String namePersonToUpdate, String keyToUpdate, String valueToUpdate){
        String name;
        List<MedicalRecord> MedicalRecordsList = getMedicalRecords();
        for(MedicalRecord medicalRecord:MedicalRecordsList) {
            name = medicalRecord.getFirstName().concat(medicalRecord.getLastName());
            if(namePersonToUpdate.equals(name)){
                switch(keyToUpdate){
                    case "birthday": medicalRecord.setBirthdate(valueToUpdate); break;
                    case "medications": medicalRecord.setMedications(Collections.singletonList(valueToUpdate)); break;
                    case "allergies": medicalRecord.setAllergies(Collections.singletonList(valueToUpdate)); break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * This method removes a person in the list of persons
     */
    public void removeMedicalRecordService(String namePersonToDelete){
        List<MedicalRecord> MedicalRecordsList = getMedicalRecords();
        MedicalRecordsList.removeIf(p -> p.getFirstName().concat(p.getLastName()).equals(namePersonToDelete));
    }



}
