package com.RelationshipsQuantum.models;

import com.RelationshipsQuantum.entities.Person;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;

public class PersonModel {


	private long id;

	@NotNull
	private String name;

	@NotNull
	private String surname;

	@NotNull
	private String nationality;

    @NotNull
    @Min(0)
	private int age;

    private PersonModel father;

    private List <PersonModel> children;

    public static PersonModel from(Person person){
    	PersonModel model1 = new PersonModel();
    	model1.setAge(person.getAge());
    	model1.setId(person.getId());
    	model1.setName(person.getName());
    	model1.setSurname(person.getSurname());
    	model1.setNationality(person.getNationality());
    	model1.setChildren(person.getChildren().stream().map(PersonModel::from).collect(Collectors.toList()));
    	return model1;
	}

	public long getId() {
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

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public List<PersonModel> getChildren() {
		return children;
	}

	public void setChildren(List<PersonModel> children) {
		this.children = children;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}
}
