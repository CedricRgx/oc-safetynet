package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
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
 * The MedicalRecordServiceTest class is used to test the MedicalRecordService class
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MedicalRecordServiceTest {

    @Mock
    private JSONDatabase jsonDatabase;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * testGetMedicalRecordService is used to test the getMedicalRecordService method of the Service layer
     */
    @Test
    public void testGetMedicalRecordService() {

        // Arrange
        List<MedicalRecord> expectedMedicalRecords = Arrays.asList(MedicalRecord.builder().build(), MedicalRecord.builder().build());
        when(jsonDatabase.getListOfMedicalRecord()).thenReturn(expectedMedicalRecords);

        // Act
        List<MedicalRecord> actualMedicalRecords = medicalRecordService.getMedicalRecordsService();

        // Assert
        assertEquals(expectedMedicalRecords, actualMedicalRecords);
    }

    /**
     * testAddMedicalRecordService is used to test the addMedicalRecordService method of the Service layer
     */
    @Test
    public void testAddMedicalRecordService() {

        // Arrange
        MedicalRecord medicalRecordToAdd = MedicalRecord.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthdate("01/03/2000")
                .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                .allergies(new ArrayList<>() {{add("nillacilan");}})
                .build();
        List<MedicalRecord> initialMedicalRecords = new ArrayList<>() {{add(MedicalRecord.builder().build());add(MedicalRecord.builder().build());}};
        when(medicalRecordService.getMedicalRecordsService()).thenReturn(initialMedicalRecords);

        // Act
        MedicalRecord addedMedicalRecord;
        addedMedicalRecord = medicalRecordService.addMedicalRecordService(medicalRecordToAdd);

        // Assert
        assertTrue(initialMedicalRecords.contains(addedMedicalRecord));
        verify(jsonDatabase, times(1)).setListOfMedicalRecord(anyList());
    }

    /**
     * testUpdateMedicalRecordService is used to test the updateMedicalRecordService method of the Service layer
     */
    @Test
    public void testUpdateMedicalRecordService() {

        // Arrange
        MedicalRecord medicalRecordToUpdate = MedicalRecord.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthdate("01/03/2000")
                .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                .allergies(new ArrayList<>() {{add("nillacilan");}})
                .build();

        List<MedicalRecord> initialMedicalRecords = new ArrayList<>() {{
            add(MedicalRecord.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .birthdate("01/03/1999")
                    .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                    .allergies(new ArrayList<>() {{add("nillacilan");}})
                    .build());
            add(MedicalRecord.builder()
                    .firstName("TestFirstName1")
                    .lastName("TestLastName1")
                    .birthdate("01/03/2000")
                    .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                    .allergies(new ArrayList<>() {{add("nillacilan");}})
                    .build());
        }};

        when(medicalRecordService.getMedicalRecordsService()).thenReturn(initialMedicalRecords);

        // Act
        MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecordService(medicalRecordToUpdate);
        String birthdateUpdatedMedicalRecord = updatedMedicalRecord.getBirthdate();

        // Assert
        assertNotNull(updatedMedicalRecord);
        assertEquals("01/03/2000", birthdateUpdatedMedicalRecord);
        verify(jsonDatabase, times(1)).setListOfMedicalRecord(anyList());
    }

    /**
     * testUpdateMedicalRecordServiceMedicalRecordNotFound is used to test the updateMedicalRecordService method of the Service layer with medical record not found output
     */
    @Test
    public void testUpdateMedicalRecordServiceMedicalRecordNotFound() {

        // Arrange
        MedicalRecord medicalRecordToUpdate = MedicalRecord.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .birthdate("01/03/2000")
                .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                .allergies(new ArrayList<>() {{add("nillacilan");}})
                .build();

        List<MedicalRecord> initialMedicalRecords = new ArrayList<>() {{
            add(MedicalRecord.builder()
                    .firstName("TestFirstName1")
                    .lastName("TestLastName1")
                    .birthdate("01/03/2000")
                    .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                    .allergies(new ArrayList<>() {{add("nillacilan");}})
                    .build());
        }};

        when(medicalRecordService.getMedicalRecordsService()).thenReturn(initialMedicalRecords);

        // Act
        MedicalRecord updatedMedicalRecord = medicalRecordService.updateMedicalRecordService(medicalRecordToUpdate);

        // Assert
        assertNull(updatedMedicalRecord);
        verify(jsonDatabase, never()).setListOfMedicalRecord(anyList());
    }

    /**
     * testRemoveMedicalRecordService is used to test the removeMedicalRecordService method of the Service layer
     */
    @Test
    public void testRemoveMedicalRecordService() {

        // Arrange
        String firstNameToRemove = "TestFirstName";
        String lastNameToRemove = "TestLastName";

        List<MedicalRecord> initialMedicalRecords = new ArrayList<>() {{
            add(MedicalRecord.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .birthdate("01/03/1999")
                    .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                    .allergies(new ArrayList<>() {{add("nillacilan");}})
                    .build());
            add(MedicalRecord.builder()
                    .firstName("TestFirstName1")
                    .lastName("TestLastName1")
                    .birthdate("01/03/2000")
                    .medications(new ArrayList<>() {{add("noxidian:100mg");add("thradox:700mg");}})
                    .allergies(new ArrayList<>() {{add("nillacilan");}})
                    .build());
        }};

        when(medicalRecordService.getMedicalRecordsService()).thenReturn(initialMedicalRecords);

        // Act
        boolean result = medicalRecordService.removeMedicalRecordService(firstNameToRemove, lastNameToRemove);

        // Assert
        assertTrue(result);
    }
}
