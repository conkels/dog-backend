package com.example.dog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Dog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String breed;
	
	private String name;
	
	private Integer age;
	
	private Boolean isMale;

	public Dog(Integer id, String breed, String name, Integer age, Boolean isMale) {
		super();
		this.id = id;
		this.breed = breed;
		this.name = name;
		this.age = age;
		this.isMale = isMale;
	}

	public Dog() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getIsMale() {
		return isMale;
	}

	public void setIsMale(Boolean isMale) {
		this.isMale = isMale;
	}

	@Override
	public String toString() {
		return "Dog [id=" + id + ", breed=" + breed + ", name=" + name + ", age=" + age + ", isMale=" + isMale + "]";
	}
	
	
	
}
