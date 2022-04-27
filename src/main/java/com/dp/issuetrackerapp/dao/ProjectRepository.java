package com.dp.issuetrackerapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.issuetrackerapp.entity.Project;

public interface ProjectRepository extends JpaRepository<Project, Integer> {

}
