package com.project.HospitalManagement.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;


@Entity
@Table(name="Patient")
public class Patient {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	private String name;
	private int age;
	@Column(nullable = false)
	private String visitedDoctor;
	private Date dateofVisit;
	@Type(type="text")
	private String docPres;
	
	public Patient() {}


	public Patient(long id, String name, int age, String visitedDoctor, Date dateofVisit, String docPres) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.visitedDoctor = visitedDoctor;
		this.dateofVisit = dateofVisit;
		this.docPres = docPres;
	}

	public Long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getVisitedDoctor() {
		return visitedDoctor;
	}

	public void setVisitedDoctor(String visitedDoctor) {
		this.visitedDoctor = visitedDoctor;
	}

	public Date getDateofVisit() {
		return dateofVisit;
	}

	public void setDateofVisit(Date dateofVisit) {
		this.dateofVisit = dateofVisit;
	}


	public String getDocPres() {
		return docPres;
	}

	public void setDocPres(String docPres) {
		this.docPres = docPres;
	}

	@Override
	public String toString() {
		return "Patient [id=" + id + ", name=" + name + ", age=" + age + ", visitedDoctor=" + visitedDoctor
				+ ", dateofVisit=" + dateofVisit + ", docPres=" + docPres + "]";
	}

}
