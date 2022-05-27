package com.project.HospitalManagement;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.HospitalManagement.Controller.PatientController;
import com.project.HospitalManagement.Repository.PatientRepository;
import com.project.HospitalManagement.Service.PatientService;
import com.project.HospitalManagement.model.Patient;

@WebMvcTest(PatientController.class)
class PatientControllerTests {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientService patientService;
	
	@MockBean
	private PatientRepository patientRepository;
	
	Patient patient;
	
	@BeforeEach
	void setUp() throws Exception {
		
		patient = new Patient();
		patient.setId(1);
		patient.setName("Ravi");
		patient.setAge(25);
		patient.setVisitedDoctor("Sai Pavan");
		patient.setDateofVisit(new Date());
		patient.setDocPres("Dolo");
		
	}

	@Test
	final void testGetAllPatients() throws Exception {
		List<Patient> listpatient = new ArrayList<Patient>();
		listpatient.add(new Patient(1,"Ravi",25,"Sai Pavan",new Date(),"Dolo"));
		listpatient.add(new Patient(1,"Manideep",26,"Sai Pavan",new Date(),"Dolo"));
		
		Mockito.when(patientService.findAllPatients()).thenReturn(listpatient);
		String uri = "/patient/all";
		mockMvc.perform(get(uri)).andExpect(status().isOk());
	}
	
	
	@Test
	final void testGetPatientByID() throws Exception {
		Mockito.when(patientService.findPatientById(anyLong())).thenReturn(patient);
		String uri = "/patient/1";
		mockMvc.perform(get(uri)).andExpectAll(status().isOk());
	}
	
	@Test
	final void testAddPatient() throws Exception {
		patient = new Patient();
		patient.setId(1);
		patient.setName("Ravi");
		patient.setAge(25);
		patient.setVisitedDoctor("Sai Pavan");
		patient.setDateofVisit(new Date());
		patient.setDocPres("Dolo");
		
		String inputinJson = this.maptoJson(patient);
		String uri = "/patient/add";
		Mockito.when(patientService.addPatient(Mockito.any(Patient.class))).thenReturn(patient);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
			.content(inputinJson).contentType(org.springframework.http.MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());	
	}

	private String maptoJson(Patient patient) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(patient);
	}
}
