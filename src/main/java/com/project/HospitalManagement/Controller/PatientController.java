package com.project.HospitalManagement.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HospitalManagement.Service.PatientService;
import com.project.HospitalManagement.model.Patient;

@RestController
@RequestMapping("/patient")
public class PatientController {
	
	private final PatientService PatientService;

	public PatientController(PatientService PatientService) {
		this.PatientService = PatientService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Patient>> getAllPatients(){
		List<Patient> Patients = PatientService.findAllPatients();
		return new ResponseEntity<List<Patient>>(Patients, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientByID(@PathVariable("id") Long id){
		Patient Patient = PatientService.findPatientById(id);
		return new ResponseEntity<Patient>(Patient, HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<Long> getDoctorCount(@PathVariable("name") String name){
		Long count = PatientService.getDoctorCount(name);
		return new ResponseEntity<>(count, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Patient> addPatient(@RequestBody Patient Patient){
		Patient newPatient = PatientService.addPatient(Patient) ;
		return new ResponseEntity<Patient>(newPatient, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient Patient){
		Patient updatePatient = PatientService.updatePatient(Patient);
		return new ResponseEntity<Patient>(updatePatient, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> updatePatient(@PathVariable("id")Long id){
		PatientService.deletePatient(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
