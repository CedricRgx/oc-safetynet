package com.openclassrooms.safetynet.DTO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OthersMembersOfTheHouseholdDTOTest {

    private static final Logger logger = LogManager.getLogger("OthersMembersOfTheHouseholdDTOTest");

    private static OthersMembersOfTheHouseholdDTO DTOForTest;

    String valueToTest;

    @BeforeAll
    static void createDTOForTest(){
        logger.info("Create OthersMembersOfTheHouseholdDTO for test");
        String firstName = "Enfant";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        DTOForTest = new OthersMembersOfTheHouseholdDTO(firstName, lastName, address);
    }

    @Test
    void initializeBuilderTest(){
        logger.info("Test initializeBuilder for OthersMembersOfTheHouseholdDTOTest");
        assertEquals(DTOForTest, OthersMembersOfTheHouseholdDTO.builder()
                .firstName("Enfant")
                .lastName("Petit")
                .address("15 rue de la rue").build());
    }

    @Test
    void setAndGetFirstNameTest(){
        logger.info("Test setAndGetFirstName for OthersMembersOfTheHouseholdDTOTest");
        valueToTest = "Enfant";
        DTOForTest.setFirstName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getFirstName());
    }

    @Test
    void setAndGetLastNameTest(){
        logger.info("Test setAndGetLastName for OthersMembersOfTheHouseholdDTOTest");
        valueToTest = "Petit";
        DTOForTest.setLastName(valueToTest);
        assertEquals(valueToTest, DTOForTest.getLastName());
    }

    @Test
    void setAndGetAddressTest(){
        logger.info("Test setAndGetAddress for OthersMembersOfTheHouseholdDTOTest");
        valueToTest = "15 rue de la rue";
        DTOForTest.setAddress(valueToTest);
        assertEquals(valueToTest, DTOForTest.getAddress());
    }

    @Test
    public void equalsTrueTest(){
        logger.info("Test equalsTrue for OthersMembersOfTheHouseholdDTOTest");
        OthersMembersOfTheHouseholdDTO DTOForTestOne = new OthersMembersOfTheHouseholdDTO(
                "Arthur",
                "Kaamelot",
                "15 rue Paimpont");

        OthersMembersOfTheHouseholdDTO DTOForTestTwo = new OthersMembersOfTheHouseholdDTO(
                "Arthur",
                "Kaamelot",
                "15 rue Paimpont");

        assertEquals(DTOForTestOne, DTOForTestTwo);
        assertEquals(DTOForTestOne.hashCode(), DTOForTestTwo.hashCode());
    }

    @Test
    public void equalsNullTest(){
        logger.info("Test equalsNull for OthersMembersOfTheHouseholdDTOTest");
        OthersMembersOfTheHouseholdDTO DTOForTestOne = new OthersMembersOfTheHouseholdDTO(
                "Arthur",
                "Kaamelot",
                "15 rue Paimpont");

        OthersMembersOfTheHouseholdDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
