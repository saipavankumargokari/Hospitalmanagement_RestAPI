package com.project.HospitalManagement.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.HospitalManagement.Exception.DoctorNotFoundException;
import com.project.HospitalManagement.Repository.DoctorRepository;
import com.project.HospitalManagement.model.Doctor;


@Component
public class DoctorService {

	private final DoctorRepository doctorRepository;

	@Autowired
	public DoctorService(DoctorRepository doctorRepository) {
		this.doctorRepository = doctorRepository;
	}
	
	public Doctor addDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	
	public List<Doctor> findAllDoctors(){
		return doctorRepository.findAll();
	}
	
	public Doctor findDoctorById(Long id) {
		return doctorRepository.findDoctorById(id).
				orElseThrow(() -> new DoctorNotFoundException("Doctor by id " + id + " was not found"));
	}
	
	public Doctor updateDoctor(Doctor doctor) {
		return doctorRepository.save(doctor);
	}
	public void deleteDoctor(Long id) {
		doctorRepository.deleteDoctorById(id);
	}

	public Doctor findDoctorByName(String name) {
		return doctorRepository.findDoctorByName(name);

	}
}
