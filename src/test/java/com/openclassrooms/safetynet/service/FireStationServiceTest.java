package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

/**
 * The FireStationServiceTest class is used to test the FireStationService class
 */
@SpringBootTest
@AutoConfigureMockMvc
public class FireStationServiceTest {

    @Mock
    private JSONDatabase jsonDatabase;

    @InjectMocks
    private FireStationService fireStationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * testGetFireStationService is used to test the getFireStationService method of the Service layer
     */
    @Test
    public void testGetFireStationService() {

        // Arrange
        List<FireStation> expectedFireStations = Arrays.asList(FireStation.builder().build(), FireStation.builder().build());
        when(jsonDatabase.getListOfFireStations()).thenReturn(expectedFireStations);

        // Act
        List<FireStation> actualFireStations = fireStationService.getFireStationService();

        // Assert
        assertEquals(expectedFireStations, actualFireStations);
    }

    /**
     * testAddFireStationService is used to test the addFireStationService method of the Service layer
     */
    @Test
    public void testAddFireStationService() {

        // Arrange
        FireStation fireStationToAdd = FireStation.builder()
                .address("TestAddress")
                .stationNumber("2")
                .personByStation(null)
                .build();
        List<FireStation> initialFireStations = new ArrayList<>() {{add(FireStation.builder().build());add(FireStation.builder().build());}};
        when(fireStationService.getFireStationService()).thenReturn(initialFireStations);

        // Act
        FireStation addedFireStation;
        addedFireStation = fireStationService.addFireStationService(fireStationToAdd);

        // Assert
        assertTrue(initialFireStations.contains(addedFireStation));
        verify(jsonDatabase, times(1)).setListOfFireStations(anyList());
    }

    /**
     * testUpdateFireStationServicePersonNotFound is used to test the testUpdateFireStationService method of the Service layer
     */
    @Test
    public void testUpdateFireStationService() {

        // Arrange
        FireStation fireStationToUpdate = FireStation.builder()
                .address("TestAddress")
                .stationNumber("3")
                .personByStation(null)
                .build();

        List<FireStation> initialFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .address("TestAddress")
                    .stationNumber("2")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .address("TestAddress1")
                    .stationNumber("3")
                    .personByStation(null)
                    .build());
        }};

        when(fireStationService.getFireStationService()).thenReturn(initialFireStations);

        // Act
        FireStation updatedFireStation = fireStationService.updateFireStationService(fireStationToUpdate);
        String stationNumberUpdatedFireStation = updatedFireStation.getStationNumber();

        // Assert
        assertNotNull(updatedFireStation);
        assertEquals("3", stationNumberUpdatedFireStation);
        verify(jsonDatabase, times(1)).setListOfFireStations(anyList());
    }

    /**
     * testUpdateFireStationServicePersonNotFound is used to test the testUpdateFireStationService method of the Service layer with firestation not found output
     */
    @Test
    public void testUpdateFireStationServiceFireStationNotFound() {

        // Arrange
        FireStation fireStationToUpdate = FireStation.builder()
                .address("TestAddress")
                .stationNumber("3")
                .personByStation(null)
                .build();

        List<FireStation> initialFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .address("TestAddress1")
                    .stationNumber("3")
                    .personByStation(null)
                    .build());
        }};

        when(fireStationService.getFireStationService()).thenReturn(initialFireStations);

        // Act
        FireStation updatedFireStation = fireStationService.updateFireStationService(fireStationToUpdate);

        // Assert
        assertNull(updatedFireStation);
        verify(jsonDatabase, never()).setListOfFireStations(anyList());
    }

    /**
     * testRemoveFireStationService is used to test the removeFireStationService method of the Service layer
     */
    @Test
    public void testRemoveFireStationService() {

        // Arrange
        String addressToRemove = "TestAddress";
        String stationNumberToRemove = "4";

        List<FireStation> initialFireStations = new ArrayList<>() {{
            add(FireStation.builder()
                    .address("TestAddress")
                    .stationNumber("4")
                    .personByStation(null)
                    .build());
            add(FireStation.builder()
                    .address("TestAddress1")
                    .stationNumber("3")
                    .personByStation(null)
                    .build());
        }};

        when(fireStationService.getFireStationService()).thenReturn(initialFireStations);

        // Act
        boolean result = fireStationService.removeFireStationService(addressToRemove, stationNumberToRemove);

        // Assert
        assertTrue(result);
    }
}
