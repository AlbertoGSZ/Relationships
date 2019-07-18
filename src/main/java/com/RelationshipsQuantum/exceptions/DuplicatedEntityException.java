package com.RelationshipsQuantum.exceptions;
import com.RelationshipsQuantum.exceptions.generic.ConflictException;

public class DuplicatedEntityException extends ConflictException {

	public DuplicatedEntityException() {
		super("Duplicated entity");
	}
}
