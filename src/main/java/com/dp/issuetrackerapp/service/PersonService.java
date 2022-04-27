package com.dp.issuetrackerapp.service;

import java.util.List;

import com.dp.issuetrackerapp.entity.Person;

public interface PersonService {

	public List<Person> findAll();

	public Person findById(int theId);

	public void save(Person thePerson);


}
