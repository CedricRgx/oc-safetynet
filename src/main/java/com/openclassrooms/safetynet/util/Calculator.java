package com.openclassrooms.safetynet.util;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * The Calculator class is used to do some calculations for the service layer
 */
@Slf4j
public class Calculator {

    /**
     * This method returns the age of a person from his birthday
     * @param birthday
     * @return the age of the person from his birthday in the parameter
     */
    public int calculateAge(String birthday) {
        log.info("Calculate the age from the birthday");
        int ageOfPerson;
        //Define the format of birthday
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDate = LocalDate.parse(birthday, formatter);
        //Get the local date
        LocalDate dateNow = LocalDate.now();
        //Calculate the age from the difference of time between the birthday and now
        Period difference = Period.between(localDate, dateNow);
        ageOfPerson = difference.getYears();
        return ageOfPerson;
    }

    /**
     * This method returns true if the birthday is belong to a child and false when is belong to an adult
     * @param birthday
     * @return true if the birthday is belong to a child and false when is belong to an adult
     */
    public boolean isChild(String birthday){
        //Return true if the person is a child and false if it is an adult
        return calculateAge(birthday) < 18;
    }


    /**
     * This method returns the number of adults from a list of birthday
     * @param listOfBirthdays
     * @return the number of adults
     */
    public int getNumberOfAdults(List<String> listOfBirthdays){
        int numberOfAdults = 0;
        Calculator calculator = new Calculator();
        //Verify for a birthdate: if it is more 18, then the person is an adult
        for(String birthday:listOfBirthdays){
            if(calculator.calculateAge(birthday) >= 18){
                numberOfAdults++;
            }
        }
        return numberOfAdults;
    }

    /**
     * This method returns the number of children from a list of birthday
     * @param listOfBirthdays
     * @return the number of children
     */
    public int getNumberOfChildren(List<String> listOfBirthdays){
        int numberOfChildren = 0;
        Calculator calculator = new Calculator();
        //Verify for a birthdate: if it is under 18, then the person is a child
        for(String birthday:listOfBirthdays){
            if(calculator.calculateAge(birthday) < 18){
                numberOfChildren++;
            }
        }
        return numberOfChildren;
    }
}
