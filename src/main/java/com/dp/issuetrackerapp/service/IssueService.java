package com.dp.issuetrackerapp.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dp.issuetrackerapp.entity.Issue;

public interface IssueService {

	public List<Issue> findAll();

	@Query("SELECT FROM person WHERE person_id = : theId")
	public Issue findById(int theId);

	public void save(Issue theIssue);

	public void deleteById(@Param("theId") int theId);


}
