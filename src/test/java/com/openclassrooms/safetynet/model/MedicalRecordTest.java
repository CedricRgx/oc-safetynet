package com.openclassrooms.safetynet.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordTest {

    private static final Logger logger = LogManager.getLogger("MedicalRecordTest");

    private static MedicalRecord medicalRecordTest;

    String valueToTest;
    List<String> listToTest;

    @BeforeAll
    static void createMedicalRecordForTest(){
        String firstName = "Toto";
        String lastName = "Tata";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        medicalRecordTest = new MedicalRecord(firstName, lastName, birthdate, medications, allergies);
    }

    @Test
    public void setAndGetFirstNameTest() {
        valueToTest = "Citron";
        medicalRecordTest.setFirstName(valueToTest);
        assertEquals(valueToTest, medicalRecordTest.getFirstName());
    }

    @Test
    public void setAndGetLastNameTest() {
        valueToTest = "Pomme";
        medicalRecordTest.setLastName(valueToTest);
        assertEquals(valueToTest, medicalRecordTest.getLastName());
    }

    @Test
    public void setAndGetBirthdateTest() {
        valueToTest = "06-09-1745";
        medicalRecordTest.setBirthdate(valueToTest);
        assertEquals(valueToTest, medicalRecordTest.getBirthdate());
    }

    @Test
    public void setAndGetMedicationsTest() {
        listToTest = new ArrayList<>() {{add("aspirine:100mg");add("anthrax:700mg");}};
        medicalRecordTest.setMedications(listToTest);
        assertEquals(listToTest, medicalRecordTest.getMedications());
    }

    @Test
    public void setAndGetAllergiesTest() {
        listToTest = new ArrayList<>() {{add("kiwi");}};
        medicalRecordTest.setAllergies(listToTest);
        assertEquals(listToTest, medicalRecordTest.getAllergies());
    }

    @Test public void equalsTrueTest(){
        String firstName1 = "Jean";
        String lastName1 = "Pierre";
        String birthdate1 = "12/06/1975";
        List<String> medications1 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies1 = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecord1 = new MedicalRecord(firstName1, lastName1, birthdate1, medications1, allergies1);

        String firstName2 = "Jean";
        String lastName2 = "Pierre";
        String birthdate2 = "12/06/1975";
        List<String> medications2 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies2 = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecord2 = new MedicalRecord(firstName2, lastName2, birthdate2, medications2, allergies2);

        assertEquals(medicalRecord1, medicalRecord2);
        assertEquals(medicalRecord1.hashCode(), medicalRecord2.hashCode());
    }

    @Test public void equalsNullTest(){
        String firstName1 = "Jean";
        String lastName1 = "Pierre";
        String birthdate1 = "12/06/1975";
        List<String> medications1 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies1 = new ArrayList<>() {{add("nillacilan");}};
        MedicalRecord medicalRecord1 = new MedicalRecord(firstName1, lastName1, birthdate1, medications1, allergies1);
        MedicalRecord medicalRecord2 = null;

        assertNull(medicalRecord2);
        assertNotEquals(medicalRecord1, medicalRecord2);
    }
}