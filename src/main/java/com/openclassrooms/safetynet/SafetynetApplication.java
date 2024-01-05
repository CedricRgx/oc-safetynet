package com.openclassrooms.safetynet;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecord;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The SafetynetApplication is used to parse the data.json file and launch the safetyNet application
 */
@SpringBootApplication
public class SafetynetApplication {

	private static final Logger logger = LogManager.getLogger("SafetynetApplication");

	@Autowired
	private JSONDatabase jsonDatabase;

	public static void main(String[] args) {
		SpringApplication.run(SafetynetApplication.class, args);
		System.out.println("SafetyNet application is UP !");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {

			//Loading and reading the file
			logger.info("Loading and reading the input json file");
			logger.debug("Loading and reading the input json file");
			byte[] bytesFile = Files.readAllBytes(new File("src/main/resources/data.json").toPath());
			//Iteration over file data
			JsonIterator iter = JsonIterator.parse(bytesFile);
			Any any = iter.readAny();

			//Loading Person data from file into JSONDatabase object
			Any personAny = any.get("persons");
			logger.info("Loading persons data from input file");
			List<Person> personDatafile = initializePersons(personAny);
			//Populate the JsonDatabase object from data retrieved from the input file
			jsonDatabase.setListOfPersons(personDatafile);

			//Loading FireStation data from file into JSONDatabase object
			Any fireStationAny = any.get("firestations");
			logger.info("Loading fire stations data from input file");
			List<FireStation> fireStationDatafile = initializeFireStation(fireStationAny);
			//Populate the JsonDatabase object from data retrieved from the input file
			jsonDatabase.setListOfFireStations(fireStationDatafile);

			//Loading MedicalRecords data from file into JSONDatabase object
			Any medicalRecordAny = any.get("medicalrecords");
			logger.info("Loading medical records data from input file");
			List<MedicalRecord> medicalrecordDatafile = initializeMedicalRecord(medicalRecordAny);
			//Populate the JsonDatabase object from data retrieved from the input file
			jsonDatabase.setListOfMedicalRecord(medicalrecordDatafile);

			loadMedicalRecordsIntoPerson(medicalrecordDatafile, personDatafile);
			loadPersonIntoFirestation(personDatafile, fireStationDatafile);
		};
	}

	/**
	 * This method reads the line from the input data file and sets the data in the people list
	 * @param personAny
	 * @return the list of person from the input data file
	 */
	private List<Person> initializePersons(Any personAny){
		List<Person> persons = new ArrayList<>();
		//Set the values of persons from the json data
		personAny.forEach(a -> persons.add(Person.builder()
				.firstName(a.get("firstName").toString())
				.address(a.get("address").toString())
				.city(a.get("city").toString())
				.lastName(a.get("lastName").toString())
				.phone(a.get("phone").toString())
				.zip(a.get("zip").toString())
				.email(a.get("email").toString())
				.build()));
		return persons;
	}

	/**
	 * This method reads the line from the input data file and sets the data in the fire station list
	 * @param fireStationAny
	 * @return the list of fire stations from the input data file
	 */
	private List<FireStation> initializeFireStation(Any fireStationAny){
		List<FireStation> fireStations = new ArrayList<>();
		//Set the values of fire stations from the json data
		fireStationAny.forEach(a -> fireStations.add(FireStation.builder()
				.address(a.get("address").toString())
				.stationNumber(a.get("station").toString())
				.build()));
		return fireStations;
	}

	/**
	 * This method reads the line from the input data file and sets the data in the medical records list
	 * @param medicalRecordAny
	 * @return the list of medical records from the input data file
	 */
	private List<MedicalRecord> initializeMedicalRecord(Any medicalRecordAny){
		List<MedicalRecord> medicalRecord = new ArrayList<>();
		//Set the values of medical records from the json data
		medicalRecordAny.forEach(a -> medicalRecord.add(MedicalRecord.builder()
				.firstName(a.get("firstName").toString())
				.lastName(a.get("lastName").toString())
				.birthdate(a.get("birthdate").toString())
				.medications(Collections.singletonList(a.get("medications").toString()))
				.allergies(Collections.singletonList(a.get("allergies").toString()))
				.build()));
		return medicalRecord;
	}

	/**
	 * This method sets the medical records to the person
	 * @param medicalrecordDatafile
	 * @param personDatafile
	 */
	private void loadMedicalRecordsIntoPerson(List<MedicalRecord> medicalrecordDatafile, List<Person> personDatafile){
		String nameMedicalRecord;
		String namePerson;
		//Load medical record into the person object (owner of the medical record)
		for(MedicalRecord medicalRecord:medicalrecordDatafile) {
			nameMedicalRecord = medicalRecord.getFirstName().concat(medicalRecord.getLastName());
			for(Person person:personDatafile) {
				namePerson = person.getFirstName().concat(person.getLastName());
				if(nameMedicalRecord.equals(namePerson)){ //if firstname and lastname are identical, then the medical records is linked to the person
					person.setMedicalRecord(medicalRecord);
				}
			}
		}
	}


	/**
	 * This method sets the list of person by fire station
	 * @param personDatafile
	 * @param firestationDatafile
	 */
	private void loadPersonIntoFirestation(List<Person> personDatafile, List<FireStation> firestationDatafile){
		String adressPerson;
		String adressFireStation;
		//Load person into the person object (owner of the medical record)
		Map<String, List<Person>> personGroupByAddress = personDatafile.stream().collect(Collectors.groupingBy(Person::getAddress)); //Regroup the persons by address
		for(Map.Entry<String, List<Person>> entry : personGroupByAddress.entrySet()) {
			adressPerson = entry.getKey();
			for(FireStation firestation:firestationDatafile){
				adressFireStation = firestation.getAddress();
				if(adressPerson.equals(adressFireStation)){
					firestation.setPersonByStation(entry.getValue());
				}
			}
		}
	}

}