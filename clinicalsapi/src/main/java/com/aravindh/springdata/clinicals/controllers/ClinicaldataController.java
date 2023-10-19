package com.aravindh.springdata.clinicals.controllers;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravindh.springdata.clinicals.dto.ClinicaldataRequest;
import com.aravindh.springdata.clinicals.models.Clinicaldata;
import com.aravindh.springdata.clinicals.models.Patient;
import com.aravindh.springdata.clinicals.repos.ClinicaldataRepository;
import com.aravindh.springdata.clinicals.repos.PatientRepository;

@RestController
@RequestMapping("/clinicalsapi")
@CrossOrigin
public class ClinicaldataController {

	private PatientRepository patientRepository;
	private ClinicaldataRepository clinicaldataRepository;

	ClinicaldataController(PatientRepository patientRepository, ClinicaldataRepository clinicaldataRepository) {
		this.patientRepository = patientRepository;
		this.clinicaldataRepository = clinicaldataRepository;
	}
	
	Map<String, String> filters = new HashMap<>();

	@PostMapping("/saveClinicalData")
	public Clinicaldata saveClinicaldata(@RequestBody ClinicaldataRequest request) {
		Patient patient = patientRepository.findById(request.getPatientId()).get();

		Clinicaldata clinicaldata = new Clinicaldata();
		
		clinicaldata.setComponentName(request.getComponentName());
		
		clinicaldata.setComponentValue(request.getComponentValue());
		
		clinicaldata.setPatient(patient);
		
		clinicaldata.setMeasuredDateTime(Timestamp.from(Instant.now()));
		
		return clinicaldataRepository.save(clinicaldata);

	}

	@GetMapping("/analyse/{id}")
	public Patient analyse(@PathVariable("id") int id) {
		Patient patient = patientRepository.findById(id).get();
		List<Clinicaldata> clinicaldatas = patient.getClinicaldatas();
		ArrayList<Clinicaldata> dupeClinicaldatas = new ArrayList<>(clinicaldatas);

		// BMI
		for (Clinicaldata data : dupeClinicaldatas) {
			
			if(filters.containsKey(data.getComponentName())) {
				clinicaldatas.remove(data);
				continue;
			}
			else {
				filters.put(data.getComponentName(), null);
			}
			if (data.getComponentName().equals("hw")) {
				String[] heightAndWeight = data.getComponentValue().split("/");

				if (heightAndWeight != null && heightAndWeight.length > 1) {
					float height = Float.parseFloat(heightAndWeight[0]) * 0.3048f;
					float weight = Float.parseFloat(heightAndWeight[1]);
					float bmi = (float) (weight / (Math.pow(height, 2)));

					Clinicaldata bmiData = new Clinicaldata();
					bmiData.setComponentName("bmi");
					bmiData.setComponentValue(Float.toString(bmi));
					clinicaldatas.add(bmiData);
				}
			}
		}
		filters.clear();
		return patient;
	}
	
}
