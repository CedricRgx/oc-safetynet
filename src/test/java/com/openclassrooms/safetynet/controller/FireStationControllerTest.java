package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
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
class FireStationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getFireStationWithSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestations"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")));
    }

    @Test
    public void getFireStationWithFailureTest() throws Exception {
        List<FireStation> listOfFireStation = new ArrayList<>();
        listOfFireStation = null;
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestationError"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addFireStationWithSuccessTest() throws Exception {
        //define a fire station to add for the test
        String address = "77 Pommier Street";
        String stationNumber = "4";
        List<Person> personsForThisFireStation = new ArrayList<>();
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .content((new ObjectMapper().writeValueAsString(fireStationTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addFireStationWithFailureTest() throws Exception{
        MedicalRecord medicalRecordTestNull = null;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .content((new ObjectMapper().writeValueAsString(medicalRecordTestNull)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateFireStationWithSuccessTest() throws Exception {
        String address = "77 Pommier Street";
        String stationNumber = "4";
        List<Person> personsForThisFireStation = new ArrayList<>();
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/firestation")
                .content((new ObjectMapper().writeValueAsString(fireStationTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        String NewStationNumber = "4";
        FireStation newFireStationTest = new FireStation(address, NewStationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/firestation")
                        .content((new ObjectMapper().writeValueAsString(newFireStationTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFireStationWithFailureTest() throws Exception {
        // Ajout de la station de pompier à modifier
        String address = "000 Test Street";
        String stationNumber = "4";
        List<Person> personsForThisFireStation = new ArrayList<>();
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/firestation")
                        .content((new ObjectMapper().writeValueAsString(fireStationTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deleteFireStationWithSuccessTest() throws Exception {
        String address = "77 Pommier Street";
        String stationNumber = "4";
        List<Person> personsForThisFireStation = new ArrayList<>();
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/firestation")
                .content((new ObjectMapper().writeValueAsString(fireStationTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isCreated());

        mockMvc.perform(MockMvcRequestBuilders
                .delete("/firestation?address=\"77 Pommier Street\"&stationNumber=\"4\""))
            .andExpect(status().isNotFound());
    }

    @Test
    public void deleteFireStationWithFailureTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/firestation?addressError=\"77 Pommier Street\"&stationNumber=\"4\""))
                .andExpect(status().isBadRequest());
    }
}