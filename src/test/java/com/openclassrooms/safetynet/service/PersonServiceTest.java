package com.openclassrooms.safetynet.service;

import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
 * The PersonServiceTest class is used to test the PersonService class
 */
@SpringBootTest
@AutoConfigureMockMvc
public class PersonServiceTest {
    private static final Logger logger = LogManager.getLogger("PersonServiceTest");

    @Mock
    private JSONDatabase jsonDatabase;

    @InjectMocks
    private PersonService personService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * testGetPersonsService is used to test the getPersonService method of the Service layer
     */
    @Test
    public void testGetPersonsService() {
        logger.info("testGetPersonsService() for PersonServiceTest");

        // Arrange
        List<Person> expectedPersons = Arrays.asList(Person.builder().build(), Person.builder().build());
        when(jsonDatabase.getListOfPersons()).thenReturn(expectedPersons);

        // Act
        List<Person> actualPersons = personService.getPersonsService();

        // Assert
        assertEquals(expectedPersons, actualPersons);
    }

    /**
     * testAddPersonService is used to test the addPersonService method of the Service layer
     */
    @Test
    public void testAddPersonService() {
        logger.info("testAddPersonService() for PersonServiceTest");

        // Arrange
        Person personToAdd = Person.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .phone("TestPhone")
                .zip("TestZip")
                .address("TestAddress")
                .city("City")
                .email("mail@email.com")
                .build();
        List<Person> initialPersons = new ArrayList<>() {{add(Person.builder().build());add(Person.builder().build());}};
        when(personService.getPersonsService()).thenReturn(initialPersons);

        // Act
        Person addedPerson;
        addedPerson = personService.addPersonService(personToAdd);

        // Assert
        assertTrue(initialPersons.contains(addedPerson));
        verify(jsonDatabase, times(1)).setListOfPersons(anyList());
    }

    /**
     * testUpdatePersonService is used to test the updatePersonService method of the Service layer
     */
    @Test
    public void testUpdatePersonService() {
        logger.info("testUpdatePersonService() for PersonServiceTest");

        // Arrange
        Person personToUpdate = Person.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .phone("TestPhone")
                .zip("TestZip")
                .address("TestAddress")
                .city("City")
                .email("mail@email.com")
                .medicalRecord(null)
                .build();

        List<Person> initialPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City")
                    .email("mail@email.com")
                    .medicalRecord(null)
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress2")
                    .city("City2")
                    .email("mailTwo@email.com")
                    .medicalRecord(null)
                    .build());
        }};

        when(personService.getPersonsService()).thenReturn(initialPersons);

        // Act
        Person updatedPerson = personService.updatePersonService(personToUpdate);
        String phoneUpdatedPerson = updatedPerson.getPhone();

        // Assert
        assertNotNull(updatedPerson);
        assertEquals("TestPhone", phoneUpdatedPerson);
        verify(jsonDatabase, times(1)).setListOfPersons(anyList());
    }

    /**
     * testUpdatePersonServicePersonNotFound is used to test the updatePersonService method of the Service layer with person not found output
     */
    @Test
    public void testUpdatePersonServicePersonNotFound() {
        logger.info("testUpdatePersonServicePersonNotFound() for PersonServiceTest");

        // Arrange
        Person personToUpdate = Person.builder()
                .firstName("TestFirstName")
                .lastName("TestLastName")
                .phone("TestPhone")
                .zip("TestZip")
                .address("TestAddress")
                .city("City")
                .email("mail@email.com")
                .medicalRecord(null)
                .build();

        List<Person> initialPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress2")
                    .city("City2")
                    .email("mailTwo@email.com")
                    .medicalRecord(null)
                    .build());
        }};

        when(personService.getPersonsService()).thenReturn(initialPersons);

        // Act
        Person updatedPerson = personService.updatePersonService(personToUpdate);

        // Assert
        assertNull(updatedPerson);
        verify(jsonDatabase, never()).setListOfPersons(anyList());
    }

    /**
     * testRemovePersonService is used to test the removePersonService method of the Service layer
     */
    @Test
    public void testRemovePersonService() {
        logger.info("testRemovePersonService() for PersonServiceTest");

        // Arrange
        String firstNameToRemove = "TestFirstName";
        String lastNameToRemove = "TestLastName";

        List<Person> initialPersons = new ArrayList<>() {{
            add(Person.builder()
                    .firstName("TestFirstName")
                    .lastName("TestLastName")
                    .phone("111-222-333")
                    .zip("TestZip")
                    .address("TestAddress")
                    .city("City")
                    .email("mail@email.com")
                    .medicalRecord(null)
                    .build());
            add(Person.builder()
                    .firstName("TestFirstName2")
                    .lastName("TestLastName2")
                    .phone("444-555-666")
                    .zip("TestZip2")
                    .address("TestAddress2")
                    .city("City2")
                    .email("mailTwo@email.com")
                    .medicalRecord(null)
                    .build());
        }};

        when(personService.getPersonsService()).thenReturn(initialPersons);

        // Act
        boolean result = personService.removePersonService(firstNameToRemove, lastNameToRemove);

        // Assert
        assertTrue(result);
    }
}