package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<MedicalRecord> getMedicalRecord(){
        return medicalRecordService.getMedicalRecord();
    }
        /*
    http://localhost:8080/medicalRecord
    Cet endpoint permettra d’effectuer les actions suivantes via Post/Put/Delete HTTP :
        ajouter un dossier médical ;
        mettre à jour un dossier médical existant (comme évoqué précédemment, supposer que le prénom et le nom de famille ne changent pas) ;
        supprimer un dossier médical (utilisez une combinaison de prénom et de nom comme identificateur unique)
     */
}
