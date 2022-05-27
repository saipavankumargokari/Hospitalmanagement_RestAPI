package com.project.HospitalManagement.Exception;

public class PatientNotFoundException extends RuntimeException{
	
	public PatientNotFoundException(String message) {
		super(message);
	}
}
