package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.DTO.*;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import com.openclassrooms.safetynet.util.Calculator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

/**
 * The AlertServiceTest class is used to test the AlertService class
 */
@SpringBootTest
@AutoConfigureMockMvc
public class AlertServiceTest {
    private static final Logger logger = LogManager.getLogger("AlertServiceTest");

    @Mock
    private JSONDatabase jsonDatabase;

    @Mock
    private Calculator calculatorMock;

    @InjectMocks
    private AlertService alertService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * testGetListOfPersonsByAddress is used to test the getListOfPersonsByAddress method of the Service layer
     */
    @Test
    public void testGetListOfPersonsByAddress() {
        logger.info("testGetListOfPersonsByAddress() for AlertServiceTest");

        // Arrange
        String address = "Address for the test";
        List<Person> expectedPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("Address for the test")
                    .city("City")
                    .email("mail@email.com")
                    .medicalRecord(null)
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("Address for the test")
                    .city("City2")
                    .email("mailTwo@email.com")
                    .medicalRecord(null)
                    .build());
        }};

        when(jsonDatabase.getListOfPersons()).thenReturn(expectedPersons);

        // Act
        List<Person> actualPersons = alertService.getListOfPersonsByAddress(address);

        // Assert
        assertEquals(expectedPersons, actualPersons);
    }

    /**
     * testGetListOfPersonsByCity is used to test the getListOfPersonsByCity method of the Service layer
     */
    @Test
    public void testGetListOfPersonsByCity() {
        logger.info("testGetListOfPersonsByCity() for AlertServiceTest");

        // Arrange
        String city = "City for the test";
        List<Person> expectedPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(null)
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress2")
                    .city("City for the test")
                    .email("mailTwo@email.com")
                    .medicalRecord(null)
                    .build());
        }};
        List<String> actualEmail = new ArrayList<>() {{add("mail@email.com");add("mailTwo@email.com");}};

        when(jsonDatabase.getListOfPersons()).thenReturn(expectedPersons);

        // Act
        List<String> expectedEmail = alertService.getListOfPersonsByCity(city);

        // Assert
        assertEquals(expectedEmail, actualEmail);
    }

    /**
     * testGetListOfFireStationsByStationNumber is used to test the getListOfFireStationsByStationNumber method of the Service layer
     */
    @Test
    public void testGetListOfFireStationsByStationNumber() {
        logger.info("testGetListOfFireStationsByStationNumber() for AlertServiceTest");

        // Arrange
        String stationNumber = "3";
        List<FireStation> expectedFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress1")
                    .personByStation(null)
                    .build());
        }};

        when(jsonDatabase.getListOfFireStations()).thenReturn(expectedFireStations);

        // Act
        List<FireStation> actualFireStations = alertService.getListOfFireStationsByStationNumber(stationNumber);

        // Assert
        assertEquals(expectedFireStations, actualFireStations);
    }

    /**
     * testGetListOfFireStationsByAddress is used to test the getListOfFireStationsByAddress method of the Service layer
     */
    @Test
    public void testGetListOfFireStationsByAddress() {
        logger.info("testGetListOfFireStationsByAddress() for AlertServiceTest");

        // Arrange
        String address = "TestAddress";
        List<FireStation> expectedFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .stationNumber("2")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
        }};

        when(jsonDatabase.getListOfFireStations()).thenReturn(expectedFireStations);

        // Act
        List<FireStation> actualFireStations = alertService.getListOfFireStationsByAddress(address);

        // Assert
        assertEquals(expectedFireStations, actualFireStations);
    }

    /**
     * testGetListOfPersonByStationNumberDTO is used to test the getListOfPersonByStationNumberDTO method of the Service layer
     */
    @Test
    public void testGetListOfPersonByStationNumberDTO() {
        logger.info("testGetListOfPersonByStationNumberDTO() for AlertServiceTest");

        // Arrange
        String stationNumber = "3";

        List<PersonByStationNumberDTO> expectedPersonByStationNumberDTO = new ArrayList<>() {{
            add(PersonByStationNumberDTO.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .address("TestAddress")
                    .phone("111-222-333")
                    .birthday("01/01/2000")
                    .build());
            add(PersonByStationNumberDTO.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .address("TestAddress")
                    .phone("444-555-666")
                    .birthday("01/02/2000")
                    .build());
        }};

        List<Person> expectedPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                        .firstName("TestFirstName")
                        .lastName("TestLastName")
                        .birthdate("01/01/2000")
                        .medications(null)
                        .allergies(null)
                        .build()
                    )
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mailTwo@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName2")
                            .lastName("TestLastName2")
                            .birthdate("01/02/2000")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
        }};

        List<FireStation> expectedFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .stationNumber("2")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
        }};

        when(jsonDatabase.getListOfPersons()).thenReturn(expectedPersons);
        when(jsonDatabase.getListOfFireStations()).thenReturn(expectedFireStations);

        // Act
        List<PersonByStationNumberDTO> actualPersonByStationNumberDTO = alertService.getListOfPersonByStationNumberDTO(stationNumber);

        // Assert
        assertNotNull(actualPersonByStationNumberDTO);
        assertEquals(expectedPersonByStationNumberDTO, actualPersonByStationNumberDTO);
    }

//    /**
//     * testGetPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService is used to test the getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService method of the Service layer
//     */
//    @Test
//    public void testGetPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService() {
//        logger.info("testGetListOfPersonByStationNumberDTO() for AlertServiceTest");
//
//        // Arrange
//        String stationNumber = "1";
//
//        List<PersonByStationNumberDTO> expectedPersonByStationNumberDTO = new ArrayList<>() {{
//            add(PersonByStationNumberDTO.builder()
//                    .firstName("TestFirstName")
//                    .lastName("TestLastName")
//                    .address("TestAddress")
//                    .phone("111-222-333")
//                    .birthday("01/01/2000")
//                    .build());
//            add(PersonByStationNumberDTO.builder()
//                    .firstName("TestFirstName2")
//                    .lastName("TestLastName2")
//                    .address("TestAddress")
//                    .phone("444-555-666")
//                    .birthday("01/02/2020")
//                    .build());
//        }};
//
//        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO expectedDTO = PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO.builder()
//                .numberOfAdults(1)
//                .numberOfChildren(1)
//                .listOfPersonsByStationNumber(expectedPersonByStationNumberDTO)
//                .build();
//
//        when(alertService.getListOfPersonByStationNumberDTO(stationNumber)).thenReturn(expectedPersonByStationNumberDTO);
//        when(calculatorMock.getNumberOfAdults(anyList())).thenReturn(1);
//        when(calculatorMock.getNumberOfChildren(anyList())).thenReturn(1);
//
//        // Act
//        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO actualDTO = alertService.getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(stationNumber);
//
//        // Assert
//        assertEquals(expectedDTO, actualDTO);
//    }

    /**
     * testGetChildrenWithOthersMembersOfHouseholdService is used to test the getChildrenWithOthersMembersOfHouseholdService method of the Service layer
     */
    @Test
    public void testGetChildrenWithOthersMembersOfHouseholdService() {
        logger.info("testGetChildrenWithOthersMembersOfHouseholdService() for AlertServiceTest");

        // Arrange
        String address = "TestAddress";

        List<ChildrenWithOthersMembersOfHouseholdDTO> expectedChildren = new ArrayList<>() {{
            add(ChildrenWithOthersMembersOfHouseholdDTO.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .age(5)
                    .address("TestAddress")
                    .listOfOthersMembersOfTheHouseholdDTO(new ArrayList<>() {{add(OthersMembersOfTheHouseholdDTO.builder().firstName("TestFirstName2").lastName("TestLastName").address("TestAddress").build());}})
                    .build());
            add(ChildrenWithOthersMembersOfHouseholdDTO.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName")
                    .age(5)
                    .address("TestAddress")
                    .listOfOthersMembersOfTheHouseholdDTO(new ArrayList<>() {{add(OthersMembersOfTheHouseholdDTO.builder().firstName("TestFirstName").lastName("TestLastName").address("TestAddress").build());}})
                    .build());
        }};

        List<Person> listOfPersonByAddress = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2018")
                            .medications(null)
                            .allergies(null)
                            .build()
                    )
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mailTwo@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName2")
                            .lastName("TestLastName")
                            .birthdate("01/02/2018")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
        }};

        when(alertService.getListOfPersonsByAddress(address)).thenReturn(listOfPersonByAddress);
        when(calculatorMock.calculateAge(anyString())).thenReturn(5);

        // Act
        List<ChildrenWithOthersMembersOfHouseholdDTO> result = alertService.getChildrenWithOthersMembersOfHouseholdService(address);

        // Assert
        assertEquals(expectedChildren, result);
    }

    /**
     * testGetPhonesOfResidentsByFireStationService is used to test the getPhonesOfResidentsByFireStationService method of the Service layer
     */
    @Test
    public void testGetPhonesOfResidentsByFireStationService() {
        logger.info("testGetPhonesOfResidentsByFireStationService() for AlertServiceTest");

        // Arrange
        String stationNumber = "3";

        Set<String> expectedPhones = new HashSet<>();
        expectedPhones.add("111-222-333");
        expectedPhones.add("444-555-666");

        List<FireStation> expectedFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
        }};

        List<Person> listOfPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2000")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mailTwo@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2000")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
        }};

        when(alertService.getListOfFireStationsByStationNumber(stationNumber)).thenReturn(expectedFireStations);
        when(jsonDatabase.getListOfPersons()).thenReturn(listOfPersons);

        // Act
        Set<String> actualPhones = alertService.getPhonesOfResidentsByFireStationService(stationNumber);

        // Assert
        assertEquals(expectedPhones, actualPhones);
    }

    /**
     * testGetPersonsByAddressAndFireStationService is used to test the getPersonsByAddressAndFireStationService method of the Service layer
     */
    @Test
    public void testGetPersonsByAddressAndFireStationService() {
        logger.info("testGetPersonsByAddressAndFireStationService() for AlertServiceTest");

        // Arrange
        String address = "TestAddress";

        List<PersonByAddressDTO> listOfPersonByAddressDTO = new ArrayList<>() {{
            add(PersonByAddressDTO.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .address("TestAddress")
                    .phone("111-222-333")
                    .age(10)
                    .medications(null)
                    .allergies(null)
                    .build());
            add(PersonByAddressDTO.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName")
                    .address("TestAddress")
                    .phone("444-555-666")
                    .age(30)
                    .medications(null)
                    .allergies(null)
                    .build());
        }};

        PersonsByAddressWithFireStationNumberDTO expectedDTO = PersonsByAddressWithFireStationNumberDTO.builder()
                .stationNumber("3")
                .listOfPersonsByAddress(listOfPersonByAddressDTO)
                .build();

        List<FireStation> listOfFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .stationNumber("3")
                    .address("TestAddress")
                    .personByStation(null)
                    .build());
        }};

        List<Person> listOfPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2013")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mailTwo@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/1993")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
        }};

        when(jsonDatabase.getListOfFireStations()).thenReturn(listOfFireStations);
        when(alertService.getListOfPersonsByAddress(address)).thenReturn(listOfPersons);

        // Act
        PersonsByAddressWithFireStationNumberDTO resultDTO = alertService.getPersonsByAddressAndFireStationService(address);

        // Assert
        assertEquals(expectedDTO, resultDTO);
    }

    /**
     * testGetPersonsByStationNumberService is used to test the getPersonsByStationNumberService method of the Service layer
     */
    @Test
    public void testGetPersonsByStationNumberService() {
        logger.info("testGetPersonsByStationNumberService() for AlertServiceTest");

        // Arrange
        List<String> stationNumbers = Arrays.asList("1", "2");
        List<PersonByAddressDTO> expectedPersons = new ArrayList<>() {{
            add(PersonByAddressDTO.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .address("AddressTest")
                    .phone("111-222-333")
                    .age(5)
                    .medications(null)
                    .allergies(null)
                    .build());}};

        when(jsonDatabase.getListOfFireStations()).thenReturn(new ArrayList<>() {{add(FireStation.builder().stationNumber("1").address("AddressTest").personByStation(null).build());}});

        when(jsonDatabase.getListOfPersons()).thenReturn(new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("AddressTest")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2018")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());}});

        when(calculatorMock.calculateAge(anyString())).thenReturn(30);

        // Act
        List<PersonByAddressDTO> result = alertService.getPersonsByStationNumberService(stationNumbers);

        // Assert
        assertEquals(expectedPersons, result);
    }

    /**
     * testGetInfoAboutPersonService is used to test the getInfoAboutPersonService method of the Service layer
     */
    @Test
    public void testGetInfoAboutPersonService() {
        logger.info("testGetInfoAboutPersonService() for AlertServiceTest");

        // Arrange
        String firstName = "TestFirstName";
        String lastName = "TestLastName";

        List<PersonInfoDTO> expectedListDTO = new ArrayList<>() {{
            add(PersonInfoDTO.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .address("TestAddress")
                    .age(10)
                    .email("mail@email.com")
                    .medications(null)
                    .allergies(null)
                    .build());
        }};

        List<Person> listOfPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2013")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
            add(Person.builder()
                    .firstName("TestFirstNameTwo")
                    .lastName("TestLastNameTwo")
                    .phone("111-222-333")
                    .zip("TestZipTwo")
                    .address("TestAddressTwo")
                    .city("City for the test")
                    .email("mail@email.com")
                    .medicalRecord(MedicalRecord.builder()
                            .firstName("TestFirstName")
                            .lastName("TestLastName")
                            .birthdate("01/01/2013")
                            .medications(null)
                            .allergies(null)
                            .build())
                    .build());
        }};


        when(jsonDatabase.getListOfPersons()).thenReturn(listOfPersons);

        when(calculatorMock.calculateAge(anyString())).thenReturn(30);

        // Act
        List<PersonInfoDTO> result = alertService.getInfoAboutPersonService(firstName, lastName);

        // Assert
        assertEquals(expectedListDTO, result);
    }

//    /**
//     * testGetEmailFromPersonsInCityService is used to test the getEmailFromPersonsInCityService method of the Service layer
//     */
//    @Test
//    public void testGetEmailFromPersonsInCityService() {
//        logger.info("testGetEmailFromPersonsInCityService() for AlertServiceTest");
//
//        // Arrange
//        String city = "CityTest";
//
//        List<String> expectedEmails = new ArrayList<>();
//        expectedEmails.add("mailOne@email.com");
//        expectedEmails.add("TestLastNameTwo");
//
//        List<String> listOfEmails = new ArrayList<>();
//        listOfEmails.add("mailOne@email.com");
//        listOfEmails.add("TestLastNameTwo");
//
//        when(alertService.getListOfPersonsByCity("CityTest")).thenReturn(listOfEmails);
//
//        // Act
//        List<String> result = alertService.getEmailFromPersonsInCityService(city);
//
//        // Assert
//        assertThat(result).containsExactlyInAnyOrderElementsOf(expectedEmails);
//    }

}