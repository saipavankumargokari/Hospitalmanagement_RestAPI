package com.project.HospitalManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.HospitalManagement.Exception.PatientNotFoundException;
import com.project.HospitalManagement.Repository.PatientRepository;
import com.project.HospitalManagement.model.Patient;

@Component
public class PatientService {

	private final PatientRepository patientRepository;

	@Autowired
	public PatientService(PatientRepository PatientRepository) {
		this.patientRepository = PatientRepository;
	}
	
	public Patient addPatient(Patient Patient) {
		return patientRepository.save(Patient);
	}
	
	public List<Patient> findAllPatients(){
		return patientRepository.findAll();
	}
	
	public Patient findPatientById(Long id) {
		return patientRepository.findPatientById(id).
				orElseThrow(() -> new PatientNotFoundException("Patient by id " + id + " was not found"));
	}
	
	public Patient updatePatient(Patient Patient) {
		return patientRepository.save(Patient);
	}
	public void deletePatient(Long id) {
		patientRepository.deletePatientById(id);
	}
	
	public Long getDoctorCount(String name) {
		Long count = patientRepository.getDoctorCount(name);
		return count;
	}

}

