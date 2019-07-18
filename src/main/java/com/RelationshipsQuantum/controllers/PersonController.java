package com.RelationshipsQuantum.controllers;

import com.RelationshipsQuantum.models.PersonModel;
import com.RelationshipsQuantum.services.PersonService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class PersonController {

	private final PersonService personService;

	public PersonController(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping("/people") //Implementación correcta //Testado
	public List<PersonModel> findAll() {
		return personService.findAll();
	}

	@GetMapping("/people/{id}") //Implementación correcta //Testado
	public PersonModel findOne(@PathVariable long id) throws Exception {
		return personService.findOne(id);
	}

	@PostMapping("/people") //Implementación correcta //Testado
	public PersonModel save(@Valid @RequestBody PersonModel personModel) {
		return personService.save(personModel);
	}

	@PutMapping("/child/{id}/{child}") //Implementación correcta //Testado
	public PersonModel child(@PathVariable long id, @PathVariable long child) throws Exception {
		return personService.addChild(id, child);
	}

	@PutMapping("/people/{id}") //Implementación correcta //Testado
	public PersonModel update(@PathVariable long id, @Valid @RequestBody PersonModel personModel) throws Exception {
		return personService.update(id, personModel);
	}

	/*@DeleteMapping("/people/{id}") //Aún por implementar
	public void delete(@PathVariable long id) throws Exception {
		personService.delete(id);
	}*/

}
