package com.openclassrooms.safetynet.DTO;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PersonByStationNumberDTOTest {

    private static PersonByStationNumberDTO DTOForTest;

    String valueToTest;

    @BeforeAll
    static void createDTOForTest(){
        String firstName = "Adulte";
        String lastName = "Petit";
        String address = "15 rue de la rue";
        String phone = "666-777-8888";
        String birthday = "12/06/1975";
        DTOForTest = new PersonByStationNumberDTO(firstName, lastName, address, phone, birthday);
    }

    @Test
    void initializeBuilderTest(){
        assertEquals(DTOForTest, PersonByStationNumberDTO.builder()
                .firstName("Adulte")
                .lastName("Petit")
                .address("15 rue de la rue")
                .phone("666-777-8888")
                .birthday("12/06/1975").build());
    }

    @Test
    void setAndGetFirstNameTest(){
        valueToTest = "Adulte";
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
    void setAndGetBirthdayTest(){
        valueToTest = "12/06/1975";
        DTOForTest.setBirthday(valueToTest);
        assertEquals(valueToTest, DTOForTest.getBirthday());
    }


    @Test
    public void equalsTrueTest(){
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
