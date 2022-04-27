package com.dp.issuetrackerapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dp.issuetrackerapp.dao.IssueRepository;
import com.dp.issuetrackerapp.entity.Issue;

@Service
public class IssueServiceImpl implements IssueService {

	private IssueRepository issueRepository;


	@Autowired
	public IssueServiceImpl(IssueRepository theIssueRepository) {
		issueRepository = theIssueRepository;
	}

	@Override
	public List<Issue> findAll() {
		return issueRepository.findAll();
	}

	@Override
	public Issue findById(int theId) {
		Optional<Issue> result = issueRepository.findById(theId);

		Issue theIssue = null;

		if (result.isPresent()) {
			theIssue = result.get();
		} else {
			throw new RuntimeException("Did not find issue id - " + theId);
		}

		return theIssue;
	}

	@Override
	public void save(Issue theIssue) {
		issueRepository.save(theIssue);
	}

	@Override
	public void deleteById(int issueId) {
		issueRepository.deleteById(issueId);
	}


}
