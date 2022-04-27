package com.dp.issuetrackerapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dp.issuetrackerapp.dao.PersonRepository;
import com.dp.issuetrackerapp.entity.Person;

@Service
public class PersonServiceImpl implements PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonServiceImpl(PersonRepository thePersonRepository) {
		personRepository = thePersonRepository;
	}

	@Override
	public List<Person> findAll() {
		return personRepository.findAll();
	}

	@Override
	public Person findById(int theId) {
		Optional<Person> result = personRepository.findById(theId);

		Person thePerson = null;

		if (result.isPresent()) {
			thePerson = result.get();
		} else {
			throw new RuntimeException("Did not find project id - " + theId);
		}

		return thePerson;
	}

	@Override
	public void save(Person thePerson) {
		personRepository.save(thePerson);
	}

}
