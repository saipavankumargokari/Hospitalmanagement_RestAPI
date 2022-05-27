package com.project.HospitalManagement.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Doctor")
public class Doctor{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false, updatable = false)
	private Long id;
	
	private String name;
	private int age;
	private String gender;
	private String specField;
		
	public Doctor() {}
	
	

	public Doctor(long i, String name, int age, String gender, String specField) {
		super();
		this.id = i;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.specField = specField;
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
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecField() {
		return specField;
	}
	public void setSpecField(String specField) {
		this.specField = specField;
	}
	
	@Override
	public String toString() {
		return "Doctor [id=" + id + ", name=" + name + ", age=" + age + ", gender=" + gender + ", specField="
				+ specField + "]";
	}

}
