package com.dp.issuetrackerapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dp.issuetrackerapp.dao.ProjectRepository;
import com.dp.issuetrackerapp.entity.Project;

@Service
public class ProjectServiceImpl implements ProjectService {

	private ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository theProjectRepository) {
		projectRepository = theProjectRepository;
	}

	@Override
	public List<Project> findAll() {
		return projectRepository.findAll();
	}


	@Override
	public Project findById(int theId) {
		Optional<Project> result = projectRepository.findById(theId);

		Project theProject = null;

		if (result.isPresent()) {
			theProject = result.get();
		} else {

			throw new RuntimeException("Did not find project id - " + theId);
		}

		return theProject;
	}

	@Override
	public void save(Project theProject) {
		projectRepository.save(theProject);
	}

	@Override
	public void deleteById(int theId) {
		projectRepository.deleteById(theId);
	}


}
