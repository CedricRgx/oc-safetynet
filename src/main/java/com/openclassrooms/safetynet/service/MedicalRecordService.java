package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<MedicalRecord> getMedicalRecordsService(){
        logger.info("Service d'envoi de la liste des dossiers médicaux");
        return jsonDatabase.getListOfMedicalRecord();
    }

    /**
     * This method adds a medical record to the list of medical records
     * @param medicalRecord medical record to add
     * @return medical record added
     */
    public MedicalRecord addMedicalRecordService(MedicalRecord medicalRecord){
        logger.info("Service d'ajout d'un dossier médical");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        medicalRecordList.add(medicalRecord);
        jsonDatabase.setListOfMedicalRecord(medicalRecordList);
        return medicalRecord;
    }

    /**
     * This method updates one of the attributes of a medical record
     * @param medicalRecord medical record to update
     * @return medical record updated or null
     */
    public MedicalRecord updateMedicalRecordService(MedicalRecord medicalRecord){
        logger.info("Service de modification d'un dossier médical");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordList.stream().filter(p -> p.getFirstName().equals(medicalRecord.getFirstName()) && p.getLastName().equals(medicalRecord.getLastName())).findAny();
        if(medicalRecordOptional.isPresent()){
            MedicalRecord medicalRecordUpdate = medicalRecordOptional.get();
            //Mise à jour des champs
            medicalRecordUpdate.setBirthdate(medicalRecord.getBirthdate());
            medicalRecordUpdate.setMedications(medicalRecord.getMedications());
            medicalRecordUpdate.setAllergies(medicalRecord.getAllergies());
        }else{
            return null; //return null because the firstName and the lastName of the medical record hasn't founded
        }
        jsonDatabase.setListOfMedicalRecord(medicalRecordList);
        return medicalRecord;
    }

    /**
     * This method removes a medical record in the list of medical records
     * @param firstName and lastName of the medical record to delete
     * @return true if the medical record is not in the list (success of deletion)
     */
    public boolean removeMedicalRecordService(String firstName, String lastName){
        logger.info("Service de suppression d'un dossier médical");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        return !medicalRecordList.removeIf(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName));
    }
}