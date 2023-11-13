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
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FireStationControllerTest {

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
    public void getFireStationTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].address", is("1509 Culver St")));
    }

    @Test
    public void addFireStationTest() throws Exception {
        String address = "77 Pommier Street";
        String stationNumber = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation = new ArrayList<>(){{add(personOne);add(personTwo);}};
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/firestation")
                        .content(asJsonString(fireStationTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateFireStationTest() throws Exception {
        String address = "77 Pommier Street";
        String stationNumber = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation = new ArrayList<>(){{add(personOne);add(personTwo);}};
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                        .put("/firestation")
                        .content(asJsonString(fireStationTest))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteFireStationTest() throws Exception {
        String address = "77 Pommier Street";
        String stationNumber = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation = new ArrayList<>(){{add(personOne);add(personTwo);}};
        FireStation fireStationTest = new FireStation(address, stationNumber, personsForThisFireStation);

        mockMvc.perform(MockMvcRequestBuilders
                .post("/firestation")
                .content(asJsonString(fireStationTest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/firestation/address/77 Pommier Street"))
                .andExpect(status().isNotFound());
    }
}