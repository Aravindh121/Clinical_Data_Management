package com.aravindh.springdata.clinicals;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.Instant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import com.aravindh.springdata.clinicals.models.Clinicaldata;
import com.aravindh.springdata.clinicals.models.Patient;
import com.aravindh.springdata.clinicals.repos.ClinicaldataRepository;
import com.aravindh.springdata.clinicals.repos.PatientRepository;

@SpringBootTest
class ClinicalsapiApplicationTests {
	
	@Autowired
	PatientRepository repository;
	
	@Autowired
	ClinicaldataRepository clinicaldataRepository;
	
	
	

	@Test
	void contextLoads() {
		Patient patient = repository.findById(1).get();
		
		Clinicaldata data = new Clinicaldata();
		data.setComponentName("Allajjs");
		data.setComponentValue("oiuyt");
		data.setPatient(patient);
		data.setMeasuredDateTime(Timestamp.from(Instant.now()));
		clinicaldataRepository.save(data);
		
	}
	
	@Test
	void test2(){
		
		try {
			RestTemplate restTemplate = new RestTemplate();
			Patient patient = repository.findById(1).get();
			Clinicaldata data = new Clinicaldata();
			data.setComponentName("AAA");
			data.setComponentValue("BBB");
			data.setPatient(patient);
			data.setMeasuredDateTime(Timestamp.from(Instant.now()));
			
			restTemplate.postForObject("http://localhost:8080/clinicalsapi/saveClinicalData", data  , Clinicaldata.class);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
