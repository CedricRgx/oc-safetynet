package com.openclassrooms.safetynet.DTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class OthersMembersOfTheHouseholdDTOTest {

    private static OthersMembersOfTheHouseholdDTO DTOForTest;

    String valueToTest;

    @BeforeAll
    static void createDTOForTest(){
        String firstName = "Enfant";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        DTOForTest = new OthersMembersOfTheHouseholdDTO(firstName, lastName, address);
    }

    @Test
    void initializeBuilderTest(){
        assertEquals(DTOForTest, OthersMembersOfTheHouseholdDTO.builder()
                .firstName("Enfant")
                .lastName("Petit")
                .address("15 rue de la rue").build());
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
    public void equalsTrueTest(){
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
        OthersMembersOfTheHouseholdDTO DTOForTestOne = new OthersMembersOfTheHouseholdDTO(
                "Arthur",
                "Kaamelot",
                "15 rue Paimpont");

        OthersMembersOfTheHouseholdDTO DTOForTestTwo = null;

        assertNull(DTOForTestTwo);
        assertNotEquals(DTOForTestOne, DTOForTestTwo);
    }
}
