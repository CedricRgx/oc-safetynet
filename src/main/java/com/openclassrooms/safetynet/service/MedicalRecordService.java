package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The MedicalRecordService class is used to return datas to the controller MedicalRecordController
 */
@Service
@Slf4j
public class MedicalRecordService {

    @Autowired
    private JSONDatabase jsonDatabase;

    /**
     * This method returns the list of medical records
     * @return the list of medical records
     */
    public List<MedicalRecord> getMedicalRecordsService(){
        log.info("Sending service of the list of medical records");
        log.debug("Sending service of the list of medical records");
        return jsonDatabase.getListOfMedicalRecord();
    }

    /**
     * This method adds a medical record to the list of medical records
     * @param medicalRecord medical record to add
     * @return medical record added
     */
    public MedicalRecord addMedicalRecordService(MedicalRecord medicalRecord){
        log.info("Adding service of a medical record");
        log.debug("Adding service of a medical record");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        //Add a new medical record to the list of medical records
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
        log.info("Updating service of a medical record");
        log.debug("Updating service of a medical record");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        //Retrieve the medical record to update from the firstname and the lastname of his owner
        Optional<MedicalRecord> medicalRecordOptional = medicalRecordList.stream().filter(p -> p.getFirstName().equals(medicalRecord.getFirstName()) && p.getLastName().equals(medicalRecord.getLastName())).findAny();
        if(medicalRecordOptional.isPresent()){
            log.info("Update medical record");
            log.debug("Update medical record");
            MedicalRecord medicalRecordUpdate = medicalRecordOptional.get();
            //Update the birthdate, list of medications, list of allergies of the medical record to update
            medicalRecordUpdate.setBirthdate(medicalRecord.getBirthdate());
            medicalRecordUpdate.setMedications(medicalRecord.getMedications());
            medicalRecordUpdate.setAllergies(medicalRecord.getAllergies());
        }else{
            return null; //return null because the first and last name of the medical record to update were not found
        }
        //Update of the list of medical record with the updated medical record
        jsonDatabase.setListOfMedicalRecord(medicalRecordList);
        return medicalRecord;
    }

    /**
     * This method removes a medical record in the list of medical records
     * @param firstName of the owner of the medical record to have to remove
     * @param lastName of the owner of the medical record to have to remove
     * @return true if the medical record is not in the list (success of deletion)
     */
    public boolean removeMedicalRecordService(String firstName, String lastName){
        log.info("Deleting service of a medical record");
        log.debug("Deleting service of a medical record");
        List<MedicalRecord> medicalRecordList = getMedicalRecordsService();
        //Delete the medical record from the firstname and the lastname of his owner
        return medicalRecordList.removeIf(m -> m.getFirstName().equals(firstName) && m.getLastName().equals(lastName));
    }
}