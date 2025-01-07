package com.openclassrooms.safetynet.DTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonByAddressDTOTest {

    private static PersonByAddressDTO DTOForTest;

    String valueToTest;
    List<String> listToTest;

    @BeforeAll
    static void createDTOForTest(){
        String firstName = "Enfant";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        String phone = "666-777-8888";
        int age = 33;
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        DTOForTest = new PersonByAddressDTO(firstName, lastName, address, phone, age, medications, allergies);
    }

    @Test
    void initializeBuilderTest(){
        assertEquals(DTOForTest, PersonByAddressDTO.builder()
                .firstName("Enfant")
                .lastName("Petit")
                .address("15 rue de la rue")
                .phone("666-777-8888")
                .age(33)
                .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                .allergies(new ArrayList<>() {{add("nillacilan");}}).build());
    }

    @Test
    void setAndGetFirstNameTest(){
        valueToTest = "Enfant";
        DTOForTest.setFirstName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getFirstName());
    }

    @Test
    void setAndGetLastNameTest(){
        valueToTest = "Petit";
        DTOForTest.setLastName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getLastName());
    }

    @Test
    void setAndGetAddressTest(){
        valueToTest = "15 rue de la rue";
        DTOForTest.setAddress(valueToTest);
        assertEquals(valueToTest, DTOForTest.getAddress());
    }

    @Test
    void setAndGetPhoneTest(){
        valueToTest = "666-777-8888";
        DTOForTest.setPhone(valueToTest);
        assertEquals(valueToTest, DTOForTest.getPhone());
    }

    @Test
    public void setAndGetMedicationsTest() {
        listToTest = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        DTOForTest.setMedications(listToTest);
        assertEquals(listToTest, DTOForTest.getMedications());
    }

    @Test
    public void setAndGetAllergiesTest() {
        listToTest = new ArrayList<>() {{add("nillacilan");}};
        DTOForTest.setAllergies(listToTest);
        assertEquals(listToTest, DTOForTest.getAllergies());
    }

    @Test
    public void equalsTrueTest(){
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        String phoneOne = "666-777-8888";
        int ageOne = 33;
        List<String> medicationsOne = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesOne = new ArrayList<>() {{add("nillacilan");}};
        PersonByAddressDTO DTOForTestOne = new PersonByAddressDTO(firstNameOne, lastNameOne, addressOne, phoneOne, ageOne, medicationsOne, allergiesOne);

        String firstNameTwo = "Enfant";
        String lastNameTwo = "Petit";
        String addressTwo = "15 rue de la rue";
        String phoneTwo = "666-777-8888";
        int ageTwo = 33;
        List<String> medicationsTwo = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesTwo = new ArrayList<>() {{add("nillacilan");}};
        PersonByAddressDTO DTOForTestTwo = new PersonByAddressDTO(firstNameTwo, lastNameTwo, addressTwo, phoneTwo, ageTwo, medicationsTwo, allergiesTwo);

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        String phoneOne = "666-777-8888";
        int ageOne = 33;
        List<String> medicationsOne = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesOne = new ArrayList<>() {{add("nillacilan");}};
        PersonByAddressDTO DTOForTestOne = new PersonByAddressDTO(firstNameOne, lastNameOne, addressOne, phoneOne, ageOne, medicationsOne, allergiesOne);

        PersonByAddressDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
