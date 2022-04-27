package com.dp.issuetrackerapp.dao;

import java.util.List;

import com.dp.issuetrackerapp.entity.Issue;
import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.entity.Project;

public interface PersonDao {

	public Person findByEmail(String email);

	public List<Issue> findIssuesByCurrentPersonId(String email);

	public List<Project> findProjectsByCurrentPersonId(String theEmail);

	public void save(Person person);

}
