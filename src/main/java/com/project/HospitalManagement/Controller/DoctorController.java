package com.project.HospitalManagement.Controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.HospitalManagement.Service.DoctorService;
import com.project.HospitalManagement.model.Doctor;


@RestController
@RequestMapping("/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {
	
	private final DoctorService doctorService;

	public DoctorController(DoctorService doctorService) {
		this.doctorService = doctorService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Doctor>> getAllDoctors(){
		List<Doctor> doctors = doctorService.findAllDoctors();
		return new ResponseEntity<List<Doctor>>(doctors, HttpStatus.OK);
	}
	
	@GetMapping("/find/{name}")
	public ResponseEntity<Doctor> getDoctorByName(@PathVariable("name") String name){
		Doctor doc = doctorService.findDoctorByName(name);
		return new ResponseEntity<Doctor>(doc, HttpStatus.OK);
	}	
	
	
	@GetMapping("/{id}")
	public ResponseEntity<Doctor> getDoctorByID(@PathVariable("id") Long id){
		Doctor doctor = doctorService.findDoctorById(id);
		return new ResponseEntity<Doctor>(doctor, HttpStatus.OK);
	}
	
	@PostMapping("/add")
	public ResponseEntity<Doctor> addDoctor(@RequestBody Doctor doctor){
		Doctor newdoctor = doctorService.addDoctor(doctor) ;
		return new ResponseEntity<Doctor>(newdoctor, HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public ResponseEntity<Doctor> updateDoctor(@RequestBody Doctor doctor){
		Doctor updatedoctor = doctorService.updateDoctor(doctor);
		return new ResponseEntity<Doctor>(updatedoctor, HttpStatus.OK);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> updateDoctor(@PathVariable("id")Long id){
		doctorService.deleteDoctor(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
