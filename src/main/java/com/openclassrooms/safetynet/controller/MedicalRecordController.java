package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The MedicalRecordController class is used to manage /medicalRecord endpoints and interact with the service MedicalRecordService
 */
@RestController
public class MedicalRecordController {

    private static final Logger logger = LogManager.getLogger("MedicalRecordController");

    @Autowired
    private MedicalRecordService medicalRecordService;

    /**
     * This method send the list of medical records via the API (/medicalRecord)
     * @return the list of medical records via API REST and the status code
     */
    @GetMapping("/medicalRecord")
    public ResponseEntity<List<MedicalRecord>> getMedicalRecords(){
        logger.info("Requête GET sur l'endpoint /medicalRecord : affichage de la liste des dossiers médicaux");
        List<MedicalRecord> medicalRecordList = medicalRecordService.getMedicalRecordsService();
        if(medicalRecordList.isEmpty()){
            logger.error("Erreur lors de l'affichage de la liste des dossiers médicaux");
            return new ResponseEntity<List<MedicalRecord>>(medicalRecordList, HttpStatus.NOT_FOUND);
        }else{
            logger.info("Succès lors de l'affichage de la liste des dossiers médicaux");
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
        logger.info("Requête POST sur l'endpoint /medicalRecord : ajout d'un dossier médical");
        MedicalRecord medicalRecordAdded = medicalRecordService.addMedicalRecordService(medicalRecordToAdd);
        if(medicalRecordAdded == null){
            logger.error("Erreur lors de l'ajout du dossier médical");
            return new ResponseEntity<MedicalRecord>(medicalRecordAdded, HttpStatus.BAD_REQUEST);
        }else {
            logger.info("Dossier médical ajoutée avec succès");
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
        logger.info("Requête PUT sur l'endpoint /medicalRecord : modification d'un dossier médical");
        MedicalRecord MedicalRecordUpdated = medicalRecordService.updateMedicalRecordService(medicalRecordToUpdate);
        if(MedicalRecordUpdated == null){
            logger.error("Erreur lors de la modification du dossier médical");
            return new ResponseEntity<MedicalRecord>(MedicalRecordUpdated, HttpStatus.NOT_FOUND);
        }else {
            logger.info("Dossier médical modifié avec succès");
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
        logger.info("Requête DELETE sur l'endpoint /medicalRecord : suppression d'un dossier médical");
        boolean isDeleted = medicalRecordService.removeMedicalRecordService(firstName, lastName);
        if(isDeleted){
            logger.info("Dossier médical supprimé avec succès");
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }else {
            logger.error("Erreur lors de la suppression du dossier médical");
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
    }
}
