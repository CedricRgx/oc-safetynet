package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getMedicalRecordsWithSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void getMedicalRecordsWithFailureTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/medicalRecordError"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addMedicalRecordWithSuccessTest() throws Exception {
        //define a medical record to add for the test
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .content((new ObjectMapper().writeValueAsString(medicalRecordTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addMedicalRecordWithFailureTest() throws Exception {
        MedicalRecord medicalRecordTestNull = null;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .content((new ObjectMapper().writeValueAsString(medicalRecordTestNull)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateMedicalRecordWithSuccessTest() throws Exception {
        //define a medical record to update for the test
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecord")
                .content((new ObjectMapper().writeValueAsString(medicalRecordTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        String newFirstName = "Toto";
        String newLastName = "Tata";
        String newBirthdate = "12/06/1999";
        List<String> newMedications = new ArrayList<>() {{add("test:100mg");add("testtest:700mg");}};
        List<String> newAllergies = new ArrayList<>() {{add("testtesttest");}};
        MedicalRecord newMedicalRecordTest = new MedicalRecord(newFirstName, newLastName, newBirthdate, newMedications, newAllergies);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/medicalRecord")
                        .content((new ObjectMapper().writeValueAsString(medicalRecordTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecordWithFailureTest() throws Exception {
        //define a medical record to update for the test
        String firstName = "Test";
        String lastName = "Error";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/medicalRecord")
                        .content((new ObjectMapper().writeValueAsString(medicalRecordTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecordWithSuccessTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecord")
                .content((new ObjectMapper().writeValueAsString(medicalRecordTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/medicalRecord?firstName=\"Toto\"&lastName=\"Tata\""))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteMedicalRecordWithFailureTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/medicalRecord?firstNameError=\"Toto\"&lastName=\"Tata\""))
                .andExpect(status().isBadRequest());
    }
}