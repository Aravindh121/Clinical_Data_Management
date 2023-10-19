package com.aravindh.springdata.clinicals.repos;

import org.springframework.data.repository.CrudRepository;

import com.aravindh.springdata.clinicals.models.Patient;

public interface PatientRepository extends CrudRepository<Patient, Integer> {
	
}
