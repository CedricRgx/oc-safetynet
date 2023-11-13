package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * @return the list of medical records via API REST
     */
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getMedicalRecords(){
        logger.info("Requête GET sur l'endpoint /medicalRecord : affichage de la liste des dossiers médicaux");
        return medicalRecordService.getMedicalRecords();
    }

    /**
     * This method adds a medical record to the list of medical records via the API (/medicalRecord)
     */
    @PostMapping("/medicalRecord")
    public void addMedicalRecord(){
        logger.info("Requête POST sur l'endpoint /medicalRecord : ajout d'un dossier médical");
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/07/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        medicalRecordService.addMedicalRecordService(new MedicalRecord(firstName, lastName, birthdate, medications, allergies));
    }

    /**
     * This method update a medical record to the list of medical records via the API (/persons)
     */
    @PutMapping("/medicalRecord")
    public void updateMedicalRecord(){
        logger.info("Requête PUT sur l'endpoint /medicalRecord : modification d'un dossier médical");
        String keyToUpdate = "birthday";
        String valueToUpdate = "09/09/1999";
        String namePersonToUpdate = "TotoTata";
        medicalRecordService.updateMedicalRecord(namePersonToUpdate, keyToUpdate, valueToUpdate);
    }

    /**
     * This method delete a medical record from the list of medical records via the API (/persons)
     */
    @DeleteMapping("/medicalRecord")
    public void deleteMedicalRecord(){
        logger.info("Requête DELETE sur l'endpoint /medicalRecord : suppression d'un dossier médical");
        String namePersonToDelete = "TotoTata";
        medicalRecordService.removeMedicalRecordService(namePersonToDelete);
    }
        /*
    http://localhost:8080/medicalRecord
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
        ajouter un dossier médical ;
        mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas) ;
        supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
     */
}
