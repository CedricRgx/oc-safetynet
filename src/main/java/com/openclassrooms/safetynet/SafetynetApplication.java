package com.openclassrooms.safetynet;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import com.openclassrooms.safetynet.model.FireStation;
import com.openclassrooms.safetynet.model.JSONDatabase;
import com.openclassrooms.safetynet.model.MedicalRecords;
import com.openclassrooms.safetynet.model.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class SafetynetApplication {

	private static final Logger logger = LogManager.getLogger("SafetynetApplication");

	@Autowired
	private JSONDatabase jsondatabase;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SafetynetApplication.class, args);
		System.out.println("Application UP !");
	}

	@Bean
	CommandLineRunner runner(){
		return args -> {

			//Loading and reading the file
			logger.info("Loading and reading the input json file");
			byte[] bytesFile = Files.readAllBytes(new File("src/main/resources/data.json").toPath());
			//Iteration over file data
			JsonIterator iter = JsonIterator.parse(bytesFile);
			Any any = iter.readAny();


			//Loading Person data from file into JSONDatabase object
			Any personAny = any.get("persons");
			logger.info("Loading persons data from input file");
			List<Person> personDatafile = initializePersons(personAny);
			personDatafile.forEach(p -> System.out.println(p.getFirstName().concat(p.getLastName()).concat(p.getAddress()).concat(p.getCity()).concat(p.getPhone()).concat(p.getZip())));
			//Populate the Jsondatabase object from data retrieved from the input file
			jsondatabase.setListOfPersons(personDatafile);


			//Loading FireStation data from file into JSONDatabase object
			Any fireStationAny = any.get("firestations");
			logger.info("Loading fire stations data from input file");
			List<FireStation> fireStationDatafile = initializeFireStations(fireStationAny);
			fireStationDatafile.forEach(p -> System.out.println(p.getAddress().concat(p.getStationNumber())));
			//Populate the Jsondatabase object from data retrieved from the input file
			jsondatabase.setListOfFireStations(fireStationDatafile);


			//Loading MedicalRecords data from file into JSONDatabase object
			Any medicalRecordsAny = any.get("medicalrecords");
			logger.info("Loading medical records data from input file");
			List<MedicalRecords> medicalrecordsDatafile = initializeMedicalRecords(medicalRecordsAny);
			medicalrecordsDatafile.forEach(p -> System.out.println(p.getFirstName().concat(p.getLastName()).concat(p.getBirthdate()).concat(p.getMedications().toString()).concat(p.getAllergies().toString())));
			//Populate the Jsondatabase object from data retrieved from the input file
			jsondatabase.setListOfMedicalRecords(medicalrecordsDatafile);
		};

	}

	private List<Person> initializePersons(Any personAny){
		List<Person> persons = new ArrayList<>();
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
	};

	private List<FireStation> initializeFireStations(Any fireStationAny){
		List<FireStation> fireStations = new ArrayList<>();
		fireStationAny.forEach(a -> fireStations.add(FireStation.builder()
				.address(a.get("address").toString())
				.stationNumber(a.get("stationNumber").toString())
				.build()));
		return fireStations;
	};

	private List<MedicalRecords> initializeMedicalRecords(Any medicalRecordsAny){
		List<MedicalRecords> medicalRecords = new ArrayList<>();
		medicalRecordsAny.forEach(a -> medicalRecords.add(MedicalRecords.builder()
				.firstName(a.get("firstName").toString())
				.lastName(a.get("lastName").toString())
				.birthdate(a.get("birthdate").toString())
				.medications(Collections.singletonList(a.get("medications").toString()))
				.allergies(Collections.singletonList(a.get("allergies").toString()))
				.build()));
		return medicalRecords;
	}

}