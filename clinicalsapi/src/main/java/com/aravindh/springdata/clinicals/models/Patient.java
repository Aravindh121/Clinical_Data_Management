package com.aravindh.springdata.clinicals.models;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String lastName;
	private String firstName;
	private int age;
	
	@OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "patient")
	private List<Clinicaldata> clinicaldatas;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<Clinicaldata> getClinicaldatas() {
		return clinicaldatas;
	}

	public void setClinicaldatas(List<Clinicaldata> clinicaldatas) {
		this.clinicaldatas = clinicaldatas;
	}
	
	

}
