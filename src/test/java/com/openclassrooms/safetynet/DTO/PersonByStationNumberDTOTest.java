package com.openclassrooms.safetynet.DTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonByStationNumberDTOTest {

    private static final Logger logger = LogManager.getLogger("PersonByStationNumberDTOTest");

    private static PersonByStationNumberDTO DTOForTest;

    String valueToTest;

    @BeforeAll
    static void createDTOForTest(){
        logger.info("Create PersonByStationNumberDTO for test");
        String firstName = "Adulte";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        String phone = "666-777-8888";
        String birthday = "12/06/1975";
        DTOForTest = new PersonByStationNumberDTO(firstName, lastName, address, phone, birthday);
    }

    @Test
    void initializeBuilderTest(){
        logger.info("Test initializeBuilder for PersonByStationNumberDTO");
        assertEquals(DTOForTest, PersonByStationNumberDTO.builder()
                .firstName("Adulte")
                .lastName("Petit")
                .address("15 rue de la rue")
                .phone("666-777-8888")
                .birthday("12/06/1975").build());
    }

    @Test
    void setAndGetFirstNameTest(){
        logger.info("Test setAndGetFirstName for PersonByStationNumberDTO");
        valueToTest = "Adulte";
        DTOForTest.setFirstName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getFirstName());
    }

    @Test
    void setAndGetLastNameTest(){
        logger.info("Test setAndGetLastName for PersonByStationNumberDTO");
        valueToTest = "Petit";
        DTOForTest.setLastName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getLastName());
    }

    @Test
    void setAndGetAddressTest(){
        logger.info("Test setAndGetAddress for PersonByStationNumberDTO");
        valueToTest = "15 rue de la rue";
        DTOForTest.setAddress(valueToTest);
        assertEquals(valueToTest, DTOForTest.getAddress());
    }

    @Test
    void setAndGetPhoneTest(){
        logger.info("Test setAndGetPhone for PersonByStationNumberDTO");
        valueToTest = "666-777-8888";
        DTOForTest.setPhone(valueToTest);
        assertEquals(valueToTest, DTOForTest.getPhone());
    }

    @Test
    void setAndGetBirthdayTest(){
        logger.info("Test setAndGetBirthday for PersonByStationNumberDTO");
        valueToTest = "12/06/1975";
        DTOForTest.setBirthday(valueToTest);
        assertEquals(valueToTest, DTOForTest.getBirthday());
    }


    @Test
    public void equalsTrueTest(){
        logger.info("Test equalsTrue for PersonByStationNumberDTO");
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        String phoneOne = "666-777-8888";
        String birthdayOne = "12/06/1975";
        PersonByStationNumberDTO DTOForTestOne = new PersonByStationNumberDTO(firstNameOne, lastNameOne, addressOne, phoneOne, birthdayOne);

        String firstNameTwo = "Enfant";
        String lastNameTwo = "Petit";
        String addressTwo = "15 rue de la rue";
        String phoneTwo = "666-777-8888";
        String birthdayTwo = "12/06/1975";
        PersonByStationNumberDTO DTOForTestTwo = new PersonByStationNumberDTO(firstNameTwo, lastNameTwo, addressTwo, phoneTwo, birthdayTwo);

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        logger.info("Test equalsNull for PersonByStationNumberDTO");
        String firstNameOne = "Enfant";
        String lastNameOne = "Petit";
        String addressOne = "15 rue de la rue";
        String phoneOne = "666-777-8888";
        String birthdayOne = "12/06/1975";
        PersonByStationNumberDTO DTOForTestOne = new PersonByStationNumberDTO(firstNameOne, lastNameOne, addressOne, phoneOne, birthdayOne);

        PersonByStationNumberDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
