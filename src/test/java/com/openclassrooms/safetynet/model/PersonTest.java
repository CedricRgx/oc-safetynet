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
class PersonTest {

    private static final Logger logger = LogManager.getLogger("PersonTest");

    private static Person personTest;
    String valueToTest;

    @BeforeAll
    static void createPersonForTest(){
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
        personTest = new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies));
    }

    @Test
    public void setAndGetFirstNameTest() {
        valueToTest = "Citron";
        personTest.setFirstName(valueToTest);
        assertEquals(valueToTest, personTest.getFirstName());
    }

    @Test
    public void setAndGetLastNameTest() {
        valueToTest = "Pomme";
        personTest.setLastName(valueToTest);
        assertEquals(valueToTest, personTest.getLastName());
    }

    @Test
    public void setAndGetPhoneTest() {
        valueToTest = "111-222-3333";
        personTest.setPhone(valueToTest);
        assertEquals(valueToTest, personTest.getPhone());
    }

    @Test
    public void setAndGetZipTest() {
        valueToTest = "99999";
        personTest.setZip(valueToTest);
        assertEquals(valueToTest, personTest.getZip());
    }

    @Test
    public void setAndGetAddressTest() {
        valueToTest = "56 Lombard Street";
        personTest.setAddress(valueToTest);
        assertEquals(valueToTest, personTest.getAddress());
    }

    @Test
    public void setAndGetCityTest() {
        valueToTest = "London";
        personTest.setCity(valueToTest);
        assertEquals(valueToTest, personTest.getCity());
    }

    @Test
    public void setAndGetEmailTest() {
        valueToTest = "emailDeTest@mail.com";
        personTest.setEmail(valueToTest);
        assertEquals(valueToTest, personTest.getEmail());
    }

    @Test public void equalsTrueTest(){
        String firstName1 = "Jean";
        String lastName1 = "Pierre";
        String phone1 = "111-222-3333";
        String zip1 = "99999";
        String address1 = "56 Lombard Street";
        String city1 = "London";
        String email1 = "emailDeTest@mail.com";
        String birthdate1 = "12/06/1975";
        List<String> medications1 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies1 = new ArrayList<>() {{add("nillacilan");}};
        Person personTest1 = new Person(firstName1, lastName1, phone1, zip1, address1, city1, email1, new MedicalRecord(firstName1, lastName1, birthdate1, medications1, allergies1));

        String firstName2 = "Jean";
        String lastName2 = "Pierre";
        String phone2 = "111-222-3333";
        String zip2 = "99999";
        String address2 = "56 Lombard Street";
        String city2 = "London";
        String email2 = "emailDeTest@mail.com";
        String birthdate2 = "12/06/1975";
        List<String> medications2 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies2 = new ArrayList<>() {{add("nillacilan");}};
        Person personTest2 = new Person(firstName2, lastName2, phone2, zip2, address2, city2, email2, new MedicalRecord(firstName2, lastName2, birthdate2, medications2, allergies2));

        assertEquals(personTest1, personTest2);
        assertEquals(personTest1.hashCode(), personTest2.hashCode());
    }

    @Test public void equalsNullTest(){
        String firstName1 = "Jean";
        String lastName1 = "Pierre";
        String phone1 = "111-222-3333";
        String zip1 = "99999";
        String address1 = "56 Lombard Street";
        String city1 = "London";
        String email1 = "emailDeTest@mail.com";
        String birthdate1 = "12/06/1975";
        List<String> medications1 = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies1 = new ArrayList<>() {{add("nillacilan");}};
        Person personTest1 = new Person(firstName1, lastName1, phone1, zip1, address1, city1, email1, new MedicalRecord(firstName1, lastName1, birthdate1, medications1, allergies1));

        Person personTest2 = null;

        assertNull(personTest2);
        assertNotEquals(personTest1, personTest2);
    }
}