package com.openclassrooms.safetynet.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class CalculatorTest {
    private static final Logger logger = LogManager.getLogger("CalculatorTest");

    @Test
    void testCalculateAge() {
        logger.info("testCalculateAge() for CalculatorTest");

        Calculator calculator = new Calculator();
        String birthday = "01/10/1990";
        int expectedAge = 33; // Change the expected age based on the current date

        int calculatedAge = calculator.calculateAge(birthday);

        assertEquals(expectedAge, calculatedAge);
    }

    @Test
    void testIsChild() {
        logger.info("testIsChild() for CalculatorTest");

        Calculator calculator = new Calculator();
        String childBirthday = "01/10/2010";
        String adultBirthday = "01/10/1990";

        boolean isChild = calculator.isChild(childBirthday);
        boolean isAdult = calculator.isChild(adultBirthday);

        assertEquals(true, isChild);
        assertEquals(false, isAdult);
    }

    @Test
    void testGetNumberOfAdults() {
        logger.info("testGetNumberOfAdults() for CalculatorTest");

        Calculator calculatorMock = mock(Calculator.class);
        List<String> listOfBirthdays = List.of("01/12/1990", "05/05/1985", "12/10/2015");
        when(calculatorMock.calculateAge("01/12/1990")).thenReturn(33);
        when(calculatorMock.calculateAge("05/05/1985")).thenReturn(38);
        when(calculatorMock.calculateAge("12/10/2015")).thenReturn(8);

        Calculator calculator = new Calculator();
        int numberOfAdults = calculator.getNumberOfAdults(listOfBirthdays);

        assertEquals(2, numberOfAdults);
    }

    @Test
    void testGetNumberOfChildren() {
        logger.info("testGetNumberOfChildren() for CalculatorTest");

        Calculator calculatorMock = mock(Calculator.class);
        List<String> listOfBirthdays = List.of("01/12/1990", "05/05/1985", "12/10/2015");
        when(calculatorMock.calculateAge("01/12/1990")).thenReturn(33);
        when(calculatorMock.calculateAge("05/05/1985")).thenReturn(38);
        when(calculatorMock.calculateAge("12/10/2015")).thenReturn(8);

        Calculator calculator = new Calculator();
        int numberOfChildren = calculator.getNumberOfChildren(listOfBirthdays);

        assertEquals(1, numberOfChildren);
    }
    
}