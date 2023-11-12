package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MedicalRecordController {


    @Autowired
    private MedicalRecordService medicalRecordService;

    /**
     * This method send the list of medical records via the API (/medicalRecord)
     * @return the list of medical records via API REST
     */
    @GetMapping("/medicalRecord")
    public List<MedicalRecord> getMedicalRecords(){
        return medicalRecordService.getMedicalRecords();
    }

    /**
     * This method adds a medical record to the list of medical records via the API (/medicalRecord)
     * @return the list of medical records with a new medical record via API REST
     */
    @PostMapping("/medicalRecord")
    public List<MedicalRecord> addMedicalRecord(){
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/07/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        medicalRecordService.addMedicalRecordService(new MedicalRecord(firstName, lastName, birthdate, medications, allergies));
        return medicalRecordService.getMedicalRecords();
    }

    /**
     * This method update a person to the list of persons via the API (/persons)
     * @return the list of persons with an updated person via API REST
     */
    @PutMapping("/medicalRecord")
    public List<MedicalRecord> updateMedicalRecord(){
        String keyToUpdate = "birthday";
        String valueToUpdate = "09/09/1999";
        String namePersonToUpdate = "TotoTata";
        medicalRecordService.updateMedicalRecord(namePersonToUpdate, keyToUpdate, valueToUpdate);
        return medicalRecordService.getMedicalRecords();
    }

    /**
     * This method delete a medical record from the list of medical records via the API (/persons)
     * @return the list of medical records without the removed medical record via API REST
     */
    @DeleteMapping("/medicalRecord")
    public List<MedicalRecord> deleteMedicalRecord(){
        String namePersonToDelete = "TotoTata";
        medicalRecordService.removeMedicalRecordService(namePersonToDelete);
        return medicalRecordService.getMedicalRecords();
    }
        /*
    http://localhost:8080/medicalRecord
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
        ajouter un dossier médical ;
        mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas) ;
        supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
     */
}
