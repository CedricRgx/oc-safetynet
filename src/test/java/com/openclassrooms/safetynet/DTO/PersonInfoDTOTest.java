package com.openclassrooms.safetynet.DTO;

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
public class PersonInfoDTOTest {

    private static final Logger logger = LogManager.getLogger("PersonInfoDTOTest");

    private static PersonInfoDTO DTOForTest;

    String valueToTest;
    int ageToTest;
    List<String> listToTest;

    @BeforeAll
    static void createDTOForTest(){
        logger.info("Create PersonInfoDTO for test");
        String firstName = "Adulte";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        int age = 33;
        String email = "email@mail.com";
        List<String> medications = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergies = new ArrayList<>() {{add("nillacilan");}};
        DTOForTest = new PersonInfoDTO(firstName, lastName, address, age, email, medications, allergies);
    }

    @Test
    void initializeBuilderTest(){
        logger.info("Test initializeBuilder for PersonInfoDTO");
        assertEquals(DTOForTest, PersonInfoDTO.builder()
                .firstName("Adulte")
                .lastName("Petit")
                .address("15 rue de la rue")
                .age(33)
                .email("email@mail.com")
                .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                .allergies(new ArrayList<>() {{add("nillacilan");}}).build());
    }

    @Test
    void setAndGetFirstNameTest(){
        logger.info("Test setAndGetFirstName for PersonInfoDTO");
        valueToTest = "Adulte";
        DTOForTest.setFirstName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getFirstName());
    }

    @Test
    void setAndGetLastNameTest(){
        logger.info("Test setAndGetLastName for PersonInfoDTO");
        valueToTest = "Petit";
        DTOForTest.setLastName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getLastName());
    }

    @Test
    void setAndGetAddressTest(){
        logger.info("Test setAndGetAddress for PersonInfoDTO");
        valueToTest = "15 rue de la rue";
        DTOForTest.setAddress(valueToTest);
        assertEquals(valueToTest, DTOForTest.getAddress());
    }

    @Test
    void setAndGetAgeTest(){
        logger.info("Test setAndGetAge for PersonInfoDTO");
        ageToTest = 33;
        DTOForTest.setAge(ageToTest);
        assertEquals(ageToTest, DTOForTest.getAge());
    }

    @Test
    void setAndGetEmailTest(){
        logger.info("Test setAndGetEmail for PersonInfoDTO");
        valueToTest = "email@mail.com";
        DTOForTest.setEmail(valueToTest);
        assertEquals(valueToTest, DTOForTest.getEmail());
    }

    @Test
    public void setAndGetMedicationsTest() {
        logger.info("Test setAndGetMedications for PersonInfoDTO");
        listToTest = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        DTOForTest.setMedications(listToTest);
        assertEquals(listToTest, DTOForTest.getMedications());
    }

    @Test
    public void setAndGetAllergiesTest() {
        logger.info("Test setAndGetAllergies for PersonInfoDTO");
        listToTest = new ArrayList<>() {{add("nillacilan");}};
        DTOForTest.setAllergies(listToTest);
        assertEquals(listToTest, DTOForTest.getAllergies());
    }


    @Test
    public void equalsTrueTest(){
        logger.info("Test equalsTrue for PersonInfoDTO");
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        int ageOne = 23;
        String emailOne = "email@mail.com";
        List<String> medicationsOne = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesOne = new ArrayList<>() {{add("nillacilan");}};
        PersonInfoDTO DTOForTestOne = new PersonInfoDTO(firstNameOne, lastNameOne, addressOne, ageOne, emailOne, medicationsOne, allergiesOne);

        String firstNameTwo = "Enfant";
        String lastNameTwo = "Petit";
        String addressTwo = "15 rue de la rue";
        int ageTwo = 23;
        String emailTwo = "email@mail.com";
        List<String> medicationsTwo = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesTwo = new ArrayList<>() {{add("nillacilan");}};
        PersonInfoDTO DTOForTestTwo = new PersonInfoDTO(firstNameTwo, lastNameTwo, addressTwo, ageTwo, emailTwo, medicationsTwo, allergiesTwo);

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        logger.info("Test equalsNull for PersonInfoDTO");
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        int ageOne = 23;
        String emailOne = "email@mail.com";
        List<String> medicationsOne = new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}};
        List<String> allergiesOne = new ArrayList<>() {{add("nillacilan");}};
        PersonInfoDTO DTOForTestOne = new PersonInfoDTO(firstNameOne, lastNameOne, addressOne, ageOne, emailOne, medicationsOne, allergiesOne);

        PersonInfoDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
