package com.project.HospitalManagement.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import com.project.HospitalManagement.model.Doctor;


public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	void deleteDoctorById(Long id);

	Optional<Doctor> findDoctorById(Long id);

	
	@Query(value = "select * from doctor d where d.name=?1",nativeQuery = true)
	Doctor findDoctorByName(String name);

}
