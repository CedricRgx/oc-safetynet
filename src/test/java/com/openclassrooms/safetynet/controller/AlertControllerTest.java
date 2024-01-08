package com.openclassrooms.safetynet.controller;

import com.openclassrooms.safetynet.DTO.*;
import com.openclassrooms.safetynet.service.AlertService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
public class AlertControllerTest {

    @Mock
    private AlertService alertService;

    @InjectMocks
    private AlertController alertController;

    @Test
    public void testSuccessFindPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildren() {

        // Mocking the service response
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO mockDTO = PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO.builder().build();
        when(alertService.getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(anyString())).thenReturn(mockDTO);

        // Calling the endpoint
        ResponseEntity<PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO> response = alertController.findPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildren("1");

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockDTO, response.getBody());
    }

    @Test
    public void testErrorFindPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildren() {

        // Mocking the service response
        PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO mockDTO = null;
        when(alertService.getPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenService(anyString())).thenReturn(mockDTO);

        // Calling the endpoint
        ResponseEntity<PersonsByStationNumberWithNumberOfAdultsAndNumberOfChildrenDTO> response = alertController.findPersonsByStationNumberWithNumberOfAdultsAndNumberOfChildren("1");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        //assertEquals(mockDTO, response.getBody());
        assertNull(response.getBody());
    }

    @Test
    public void testSuccessFindChildrenWithOthersMembersOfHousehold() {

        // Mocking the service response
        List<ChildrenWithOthersMembersOfHouseholdDTO> mockList = new ArrayList<>();
        mockList.add(ChildrenWithOthersMembersOfHouseholdDTO.builder()
            .firstName("Tenley")
            .lastName("Boyd")
            .age(10)
            .address("1509 Culver St")
            .listOfOthersMembersOfTheHouseholdDTO(new ArrayList<>()).build());
        when(alertService.getChildrenWithOthersMembersOfHouseholdService(anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<ChildrenWithOthersMembersOfHouseholdDTO>> response = alertController.findChildrenWithOthersMembersOfHousehold("1509 Culver St");

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testErrorFindChildrenWithOthersMembersOfHousehold() {

        // Mocking the service response
        List<ChildrenWithOthersMembersOfHouseholdDTO> mockList = null;
        when(alertService.getChildrenWithOthersMembersOfHouseholdService(anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<ChildrenWithOthersMembersOfHouseholdDTO>> response = alertController.findChildrenWithOthersMembersOfHousehold("1509 Culver St");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testSuccessFindPhonesOfResidentsByFireStation(){

        // Mocking the service response
        Set<String> mockSet = new HashSet<>(Arrays.asList("841-874-7784", "841-874-7462", "841-874-6512", "841-874-8547"));
        when(alertService.getPhonesOfResidentsByFireStationService(anyString())).thenReturn(mockSet);

        // Calling the endpoint
        ResponseEntity<Set<String>> response = alertController.findPhonesOfResidentsByFireStation("1");

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockSet, response.getBody());
    }

    @Test
    public void testErrorFindPhonesOfResidentsByFireStation(){

        // Mocking the service response
        Set<String> mockSet = null;
        when(alertService.getPhonesOfResidentsByFireStationService(anyString())).thenReturn(mockSet);

        // Calling the endpoint
        ResponseEntity<Set<String>> response = alertController.findPhonesOfResidentsByFireStation("1");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockSet, response.getBody());
    }

    @Test
    public void testSuccessFindPersonsByAddressAndFireStation(){

        // Mocking the service response
        PersonsByAddressWithFireStationNumberDTO mockDTO = PersonsByAddressWithFireStationNumberDTO.builder().build();
        when(alertService.getPersonsByAddressAndFireStationService(anyString())).thenReturn(mockDTO);

        // Calling the endpoint
        ResponseEntity<PersonsByAddressWithFireStationNumberDTO> response = alertController.findPersonsByAddressAndFireStation("1509 Culver St");

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockDTO, response.getBody());
    }

    @Test
    public void testErrorFindPersonsByAddressAndFireStation(){

        // Mocking the service response
        PersonsByAddressWithFireStationNumberDTO mockDTO = null;
        when(alertService.getPersonsByAddressAndFireStationService(anyString())).thenReturn(mockDTO);

        // Calling the endpoint
        ResponseEntity<PersonsByAddressWithFireStationNumberDTO> response = alertController.findPersonsByAddressAndFireStation("1509 Culver St");

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockDTO, response.getBody());
    }

    @Test
    public void testSuccessFindPersonsByStationNumber(){

        // Mocking the service response
        List<PersonByAddressDTO> mockList = new ArrayList<>();
        when(alertService.getPersonsByStationNumberService(anyList())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<PersonByAddressDTO>> response = alertController.findPersonsByStationNumber(Arrays.asList("1", "2"));

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testErrorFindPersonsByStationNumber(){

        // Mocking the service response
        List<PersonByAddressDTO> mockList = null;
        when(alertService.getPersonsByStationNumberService(anyList())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<PersonByAddressDTO>> response = alertController.findPersonsByStationNumber(Arrays.asList("1", "2"));

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testSuccessFindInfoAboutPerson() {

        // Mocking the service response
        List<PersonInfoDTO> mockList = new ArrayList<>();
        when(alertService.getInfoAboutPersonService(anyString(), anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<PersonInfoDTO>> response = alertController.findInfoAboutPerson(anyString(), anyString());

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testErrorFindInfoAboutPerson() {

        // Mocking the service response
        List<PersonInfoDTO> mockList = null;
        when(alertService.getInfoAboutPersonService(anyString(), anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<PersonInfoDTO>> response = alertController.findInfoAboutPerson(anyString(), anyString());

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testSuccessFindEmailFromPersonsInCity() {

        // Mocking the service response
        List<String> mockList = new ArrayList<>(Arrays.asList("Ville"));
        when(alertService.getEmailFromPersonsInCityService(anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<String>> response = alertController.findEmailFromPersonsInCity(anyString());

        // Assertions
        assertEquals(HttpStatus.FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }

    @Test
    public void testErrorFindEmailFromPersonsInCity() {

        // Mocking the service response
        List<String> mockList = null;
        when(alertService.getEmailFromPersonsInCityService(anyString())).thenReturn(mockList);

        // Calling the endpoint
        ResponseEntity<List<String>> response = alertController.findEmailFromPersonsInCity(anyString());

        // Assertions
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(mockList, response.getBody());
    }
}