package com.RelationshipsQuantum.entities;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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

	@ManyToOne
    private Person father;

    @OneToMany
    private List <Person> children;

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

	public List<Person> getChildren() {
		return children;
	}

	public void setChildren(List<Person> children) {
		this.children = children;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public boolean isDescendantOf(Person person) {
		if (children.stream().anyMatch(person1 -> person1.id == person1.getId())) return true;
		boolean result = false;

		for (int i=0;i<children.size();i++) {
			boolean coincidence = isDescendantOf(children.get(i));
			if (coincidence) return coincidence;
		}
		return result;
	}

	public void addChildren(Person child) {
		this.children.add(child);
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}
}
