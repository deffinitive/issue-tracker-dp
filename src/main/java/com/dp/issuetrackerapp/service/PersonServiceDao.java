package com.dp.issuetrackerapp.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.person.newPerson;

public interface PersonServiceDao extends UserDetailsService {

	public Person findByEmail(String email);

	public void save(newPerson newPerson);
}
