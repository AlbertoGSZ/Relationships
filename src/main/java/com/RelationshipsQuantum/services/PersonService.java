package com.RelationshipsQuantum.services;

import com.RelationshipsQuantum.models.PersonModel;

import java.util.List;

public interface PersonService {

	List<PersonModel> findAll();

	PersonModel findOne(long id) throws Exception;

	PersonModel save(PersonModel personModel);

	PersonModel addChild(long id, long child) throws Exception;

	void delete(long id) throws Exception;

	PersonModel update(long id, PersonModel personModel) throws Exception;
}
