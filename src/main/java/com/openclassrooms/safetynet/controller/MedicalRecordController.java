package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The MedicalRecordController class is used to manage /medicalRecord endpoints and interact with the service MedicalRecordService
 */
@RestController
@Slf4j
public class MedicalRecordController {

    @Autowired
    private MedicalRecordService medicalRecordService;

    /**
     * This method send the list of medical records via the API (/medicalRecord)
     * @return the list of medical records via API REST and the status code
     */
    @GetMapping("/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(){
        log.info("GET request on the endpoint /medicalRecord: getting the list of medical records");
        List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecordsService();
        if(medicalRecordList.isEmpty()){
            log.error("Error getting the list of medical records");
            return new ResponseEntity<List<MedicalRecord>>(medicalRecordList, HttpStatus.NOT_FOUND);
        }else{
            log.info("Success getting the list of medical records");
            return new ResponseEntity<List<MedicalRecord>>(medicalRecordList, HttpStatus.FOUND);
        }
    }

    /**
     * This method adds a medical record to the list of medical records via the API (/medicalRecord)
     * @param medicalRecordToAdd medical record to add
     * @return the medical record added via API REST and the status code
     */
    @PostMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> addMedicalRecord(@RequestBody MedicalRecord medicalRecordToAdd){
        log.info("POST request on the endpoint /medicalRecord: adding a medical record");
        MedicalRecord medicalRecordAdded = medicalRecordService.addMedicalRecordService(medicalRecordToAdd);
        if(medicalRecordAdded == null){
            log.error("Error adding medical record");
            return new ResponseEntity<MedicalRecord>(medicalRecordAdded, HttpStatus.BAD_REQUEST);
        }else {
            log.info("Success adding medical record");
            return new ResponseEntity<MedicalRecord>(medicalRecordAdded, HttpStatus.CREATED);
        }
    }

    /**
     * This method update a medical record to the list of medical records via the API (/medicalRecord)
     * @param medicalRecordToUpdate medical record to update
     * @return the medical record updated via API REST and the status code
     */
    @PutMapping("/medicalRecord")
    public ResponseEntity<MedicalRecord> updateMedicalRecord(@RequestBody MedicalRecord medicalRecordToUpdate){
        log.info("PUT request on the endpoint /medicalRecord: updating a medical record");
        MedicalRecord MedicalRecordUpdated = medicalRecordService.updateMedicalRecordService(medicalRecordToUpdate);
        if(MedicalRecordUpdated == null){
            log.error("Error updating medical record");
            return new ResponseEntity<MedicalRecord>(MedicalRecordUpdated, HttpStatus.NOT_FOUND);
        }else {
            log.info("Success updating medical record");
            return new ResponseEntity<MedicalRecord>(MedicalRecordUpdated, HttpStatus.OK);
        }
    }

    /**
     * This method delete a medical record from the list of medical records via the API (/medicalRecord)
     * @param firstName and lastName of the medical record to delete
     * @return the status code
     */
    @DeleteMapping("/medicalRecord")
    public ResponseEntity deleteMedicalRecord(@RequestParam String firstName, @RequestParam String lastName){
        log.info("DELETE request on the endpoint /medicalRecord: deleting a medical record");
        boolean isDeleted = medicalRecordService.removeMedicalRecordService(firstName, lastName);
        if(isDeleted){
            log.info("Success deleting medical record");
            return new ResponseEntity(HttpStatus.OK);
        }else {
            log.error("Error deleting medical record");
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
