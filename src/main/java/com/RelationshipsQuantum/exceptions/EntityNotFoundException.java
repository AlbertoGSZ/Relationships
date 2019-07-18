package com.RelationshipsQuantum.exceptions;
import javassist.NotFoundException;

public class EntityNotFoundException extends NotFoundException {

	public EntityNotFoundException(Class clazz) {
		super(clazz.getSimpleName() + " not found");
	}

	public EntityNotFoundException(Class clazz, long id) {
		super(clazz.getSimpleName() + " not found with id " + id);
	}

}
