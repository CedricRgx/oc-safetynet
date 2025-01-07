package com.openclassrooms.safetynet;

import com.openclassrooms.safetynet.service.AlertService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class SafetynetApplicationTests {

	@Autowired
	private AlertService alertService;

	@Test
	void contextLoads() {
		assertNotNull(alertService);
	}

}
