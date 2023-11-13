package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * The MedicalRecordService class is used to return datas to the controller MedicalRecordController
 */
@Service
public class MedicalRecordService {

    private static final Logger logger = LogManager.getLogger("MedicalRecordService");

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of medical records
     * @return the list of medical records
     */
    public List<MedicalRecord> getMedicalRecords(){
        logger.info("Service d'envoi de la liste des dossiers médicaux");
        List<MedicalRecord> listOfMedicalRecord = jsonDatabase.getListOfMedicalRecord();
        return listOfMedicalRecord;
    }

    /**
     * This method adds a medical record to the list of medical records
     */
    public void addMedicalRecordService(MedicalRecord medicalRecord){
        logger.info("Service d'ajout d'un dossier médical");
        List<MedicalRecord> listOfMedicalRecords = getMedicalRecords();
        listOfMedicalRecords.add(medicalRecord);
    }

    /**
     * This method updates one of the attributes of a medical record
     */
    public void updateMedicalRecord(String namePersonToUpdate, String keyToUpdate, String valueToUpdate){
        logger.info("Service de modification d'un dossier médical");
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
        logger.info("Service de suppression d'un dossier médical");
        List<MedicalRecord> MedicalRecordsList = getMedicalRecords();
        MedicalRecordsList.removeIf(p -> p.getFirstName().concat(p.getLastName()).equals(namePersonToDelete));
    }



}
