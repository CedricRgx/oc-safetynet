package com.openclassrooms.safetynet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
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
    public void getPersonsWithSuccessTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/person"))
                .andExpect(status().isFound())
                .andExpect(jsonPath("$[0].firstName", is("John")));
    }

    @Test
    public void getPersonsWithFailureTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/personError"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void addPersonWithSuccessTest() throws Exception {
        //define a person to add for the test
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
                        .post("/person")
                        .content((new ObjectMapper().writeValueAsString(personTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void addPersonWithFailureTest() throws Exception{
        Person personTestNull = null;
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/person")
                        .content((new ObjectMapper().writeValueAsString(personTestNull)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updatePersonWithSuccessTest() throws Exception {
        // Ajout de la personne à modifier
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
                .post("/person")
                .content((new ObjectMapper().writeValueAsString(personTest)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        //Modification de la personne
        String newPhone = "841-874-6512";
        String newZip = "97451";
        String newAddress = "1509 Culver St";
        String newCity = "Culver";
        String newEmail = "adresse@email.com";
        String newBirthdate = "12/06/1975";
        List<String> newMedications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> newAllergies = new ArrayList<>() {{add("nillacilan");}};
        Person newPersonTest = new Person(firstName, lastName, newPhone, newZip, newAddress, newCity, newEmail, new MedicalRecord(firstName, lastName, newBirthdate, newMedications, newAllergies));

        mockMvc.perform(MockMvcRequestBuilders
                    .put("/person")
                    .content((new ObjectMapper().writeValueAsString(personTest)))
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updatePersonWithFailureTest() throws Exception {
        // Ajout de la personne à modifier
        String firstName = "Test";
        String lastName = "Error";
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
                        .put("/person")
                        .content((new ObjectMapper().writeValueAsString(personTest)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void deletePersonWithSuccessTest() throws Exception {
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
                .post("/person")
                .content(new ObjectMapper().writeValueAsString(personTest))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/person?firstName=\"Toto\"&lastName=\"Tata\""))
                .andExpect(status().isNoContent());
    }

    @Test
    public void deletePersonWithFailureTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/person?firstNameError=\"Toto\"&lastName=\"Tata\""))
                .andExpect(status().isBadRequest());
    }
}