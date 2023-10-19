package com.aravindh.springdata.clinicals.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aravindh.springdata.clinicals.models.Patient;
import com.aravindh.springdata.clinicals.repos.PatientRepository;

@RestController
@RequestMapping("/clinicalsapi")
@CrossOrigin
public class PatientController {
	
	private PatientRepository repository;
	
	@Autowired
	public PatientController(PatientRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("/getPatients")
	public List<Patient> getPatients(){
		return (List<Patient>) repository.findAll();
	}
	
	@GetMapping("/getPatient/{id}")
	public Patient getPatient(@PathVariable("id") int id) {
		return repository.findById(id).get();
	}
	
	@PostMapping("/create")
	public Patient create(@RequestBody Patient patient) {
		return repository.save(patient);
	}
	
}
