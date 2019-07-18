package com.RelationshipsQuantum.services.impl;
import com.RelationshipsQuantum.entities.Person;
import com.RelationshipsQuantum.models.PersonModel;
import com.RelationshipsQuantum.repositories.PersonRepository;
import com.RelationshipsQuantum.services.PersonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	public PersonServiceImpl(PersonRepository personRepository) {this.personRepository = personRepository;}

	@Override
	public List<PersonModel> findAll() {
		return personRepository.findAll().stream().map(PersonModel::from).collect(Collectors.toList());
	}

	@Override
	public PersonModel findOne(long id) throws Exception {
		Person person = personRepository.findById(id).orElseThrow(Exception::new);
		return PersonModel.from(person);
	}

	@Override
	public PersonModel save(PersonModel personModel) {
		Person person = new Person();
		person.setAge(personModel.getAge());
		person.setName(personModel.getName());
		person.setSurname(personModel.getSurname());
		person.setNationality(personModel.getNationality());
		person.setChildren(new ArrayList<Person>());
		Person savedPerson = personRepository.save(person);
		return PersonModel.from(savedPerson);
	}

	@Override
	public PersonModel addChild(long id, long child) throws Exception {
		Optional<Person> retrievedChild = personRepository.findById(child);
		if (retrievedChild.isPresent()) {
			Person retrievedFather = personRepository.findById(id).orElseThrow(Exception::new);
			if (retrievedChild.get().isDescendantOf(retrievedFather)) throw new Exception();
			retrievedFather.addChildren(retrievedChild.get());
			personRepository.save(retrievedFather);
			return PersonModel.from(retrievedFather);
		}
		throw new Exception("El hijo no existe");
	}

	@Override
	public void delete(long id) throws Exception {
		Person person = personRepository.findById(id).orElseThrow(Exception::new);
		personRepository.delete(person);
	}

	@Override
	public PersonModel update(long id, PersonModel personModel) throws Exception {
		Person person = personRepository.findById(id).orElseThrow(Exception::new);
		person.setName(personModel.getName());
		person.setSurname(personModel.getSurname());
		person.setAge(personModel.getAge());
		person.setNationality(personModel.getNationality());
		return PersonModel.from(person);
	}
}
