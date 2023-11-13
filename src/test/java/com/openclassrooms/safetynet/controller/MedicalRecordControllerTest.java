package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.MedicalRecord;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MedicalRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getMedicalRecordsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/medicalRecord"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", CoreMatchers.is("John")));
    }

    @Test
    public void addMedicalRecordTest() throws Exception {
        //define a medical record to add for the test
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/medicalRecord")
                        .content(asJsonString(medicalRecordTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateMedicalRecordTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/medicalRecord")
                        .content(asJsonString(medicalRecordTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteMedicalRecordTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/medicalRecord")
                .content(asJsonString(medicalRecordTest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/medicalRecord/firstname/Toto"))
                .andExpect(status().isNotFound());
    }
}