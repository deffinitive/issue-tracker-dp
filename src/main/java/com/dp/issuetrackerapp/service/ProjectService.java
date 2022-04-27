package com.dp.issuetrackerapp.service;

import java.util.List;

import com.dp.issuetrackerapp.entity.Project;

public interface ProjectService {

	public List<Project> findAll();

	public Project findById(int theId);

	public void save(Project theProject);

	public void deleteById(int theId);
}
