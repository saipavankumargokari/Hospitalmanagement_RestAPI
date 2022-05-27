package com.project.HospitalManagement.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.HospitalManagement.model.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long>{

	Optional<Patient> findPatientById(Long id);

	void deletePatientById(Long id);
	
	@Query(value = "select Count(visited_doctor) from patient p where p.visited_doctor=?1",nativeQuery = true)
	Long getDoctorCount(String name);

}
