package com.dp.issuetrackerapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.issuetrackerapp.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
