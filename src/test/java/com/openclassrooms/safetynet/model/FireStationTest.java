package com.openclassrooms.safetynet.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FireStationTest {

    private static FireStation fireStationTest;

    private static Person personTest;

    String valueToTest;

    List<Person> listToTest;

    @BeforeAll
    static void createFireStationForTest(){
        String address = "1509 Culver St";
        String stationNumber = "3";
        String firstName = "Toto";
        String lastName = "Tata";
        String phone = "333-666-9999";
        String zip = "97451";
        String city = "Culver";
        String email = "adresse@email.com";
        String birthdate = "12/06/1975";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        personTest = new Person(firstName, lastName, phone, zip, address, city, email, new MedicalRecord(firstName, lastName, birthdate, medications, allergies));
        List<Person> listOfPersons = new ArrayList<>(){{
            Person personTest1 = personTest;
        }};
        fireStationTest = new FireStation(address, stationNumber, new ArrayList<>(){{
            Person personTest1 = personTest;
        }});
    }

    @Test
    void setAndGetAddressTest() {
        valueToTest = "56 Lombard Street";
        fireStationTest.setAddress(valueToTest);
        assertEquals(valueToTest, fireStationTest.getAddress());
    }

    @Test
    void setAndGetStationNumberTest() {
        valueToTest = "3";
        fireStationTest.setStationNumber(valueToTest);
        assertEquals(valueToTest, fireStationTest.getStationNumber());
    }

    @Test
    void setAndGetPersonByStationTest() {

        Person personTest1 = new Person("Toto", "Tata", "333-666-9999", "97451", "1509 Culver St", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTest2 = new Person("Jean", "Miel", "333-666-9999", "97451", "1509 Culver St", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        listToTest = new ArrayList<>(){{add(personTest1);add(personTest2);}};

        fireStationTest.setPersonByStation(listToTest);
        assertEquals(listToTest, fireStationTest.getPersonByStation());
    }

    @Test public void equalsTrueTest(){
        String address1 = "77 Pommier Street";
        String stationNumber1 = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", address1, "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", address1, "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation1 = new ArrayList<>(){{add(personOne);add(personTwo);}};
        FireStation fireStationTest1 = new FireStation(address1, stationNumber1, personsForThisFireStation1);

        String address2 = "77 Pommier Street";
        String stationNumber2 = "4";
        Person personThree = new Person("Toto", "Tata", "333-666-9999", "97451", address2, "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personFour = new Person("Jean", "Miel", "333-666-9999", "97451", address2, "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation2 = new ArrayList<>(){{add(personThree);add(personFour);}};
        FireStation fireStationTest2 = new FireStation(address1, stationNumber1, personsForThisFireStation2);

        assertEquals(fireStationTest1, fireStationTest2);
        assertEquals(fireStationTest1.hashCode(), fireStationTest2.hashCode());
    }

    @Test public void equalsNullTest(){
        String address1 = "77 Pommier Street";
        String stationNumber1 = "4";
        Person personOne = new Person("Toto", "Tata", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        Person personTwo = new Person("Jean", "Miel", "333-666-9999", "97451", "77 Pommier Street", "Culver", "adresse@email.com",
                new MedicalRecord("Toto", "Tata", "12/06/1975", new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}}, new ArrayList<>() {{add("nillacilan");}}));
        List<Person> personsForThisFireStation1 = new ArrayList<>(){{add(personOne);add(personTwo);}};
        FireStation fireStationTest1 = new FireStation(address1, stationNumber1, personsForThisFireStation1);
        FireStation fireStationTest2 = null;

        assertNull(fireStationTest2);
        assertNotEquals(fireStationTest1, fireStationTest2);
    }
}