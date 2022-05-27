package com.project.HospitalManagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
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
import com.project.HospitalManagement.Controller.DoctorController;
import com.project.HospitalManagement.Repository.DoctorRepository;
import com.project.HospitalManagement.Service.DoctorService;
import com.project.HospitalManagement.model.Doctor;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;



@WebMvcTest(DoctorController.class)
class DoctorControllerTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private DoctorService doctorService;
	
	@MockBean
	private DoctorRepository doctorRepository;
	
	Doctor doctor;

	@BeforeEach
	void setUp() throws Exception {
		doctor = new Doctor();
		doctor.setId(1);
		doctor.setName("Sai Pavan");
		doctor.setAge(25);
		doctor.setSpecField("Cardiologist");
		doctor.setGender("Male");
	}
	

	@Test
	final void testGetAllDoctors() throws Exception {
		
		List<Doctor> listdoctors = new ArrayList<>();
		listdoctors.add(new Doctor(1,"Sai Pavan",24,"Male","Cardiologist"));
		listdoctors.add(new Doctor(2,"Nithin",23,"Male","Psychiatrist"));
		Mockito.when(doctorService.findAllDoctors()).thenReturn(listdoctors);
		String uri = "/doctor/all";
		mockMvc.perform(get(uri)).andExpect(status().isOk());
		
	}
	
	@Test
	final void testGetDoctorByName() throws Exception {
		
		Mockito.when(doctorService.findDoctorByName(anyString())).thenReturn(doctor);
		String uri = "/doctor/find/Sai?Pavan";
		mockMvc.perform(get(uri)).andExpectAll(status().isOk());
	}
	
	@Test
	final void testGetDoctorByID() throws Exception {
		Mockito.when(doctorService.findDoctorByName(anyString())).thenReturn(doctor);
		String uri = "/doctor/1";
		mockMvc.perform(get(uri)).andExpectAll(status().isOk());
	}
	
	@Test
	final void testAddDoctor() throws Exception {
		doctor = new Doctor();
		doctor.setId(1);
		doctor.setName("Sai Pavan");
		doctor.setAge(25);
		doctor.setSpecField("Cardiologist");
		doctor.setGender("Male");
		String inputinJson = this.maptoJson(doctor);
		String uri = "/doctor/add";
		Mockito.when(doctorService.addDoctor(Mockito.any(Doctor.class))).thenReturn(doctor);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(uri)
			.accept(org.springframework.http.MediaType.APPLICATION_JSON)
			.content(inputinJson).contentType(org.springframework.http.MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());	
	}

	private String maptoJson(Doctor doctor2) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(doctor2);
	}
}
