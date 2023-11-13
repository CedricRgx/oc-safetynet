package com.openclassrooms.safetynet.controller;

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
class PersonControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getPersonsTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/persons"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void addPersonTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String phone = "333-666-9999";
        String zip = "97451";
        String address = "1509 Culver St";
        String city = "Culver";
        String email = "adresse@email.com";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        Person personTest = new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/persons")
                        .content(personTest.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
    }

    @Test
    public void updatePersonTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String phone = "841-874-6512";
        String zip = "97451";
        String address = "1509 Culver St";
        String city = "Culver";
        String email = "adresse@email.com";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        Person personTest = new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies));

        mockMvc.perform(MockMvcRequestBuilders
                    .put("/persons")
                    .content(personTest.toString())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deletePersonTest() throws Exception {
        String firstName = "Toto";
        String lastName = "Tata";
        String phone = "841-874-6512";
        String zip = "97451";
        String address = "1509 Culver St";
        String city = "Culver";
        String email = "adresse@email.com";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        Person personTest = new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies));

        mockMvc.perform(MockMvcRequestBuilders
                        .post("/persons")
                        .content(personTest.toString())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/persons/firstname/Toto"))
                .andExpect(status().isNotFound());
    }
}