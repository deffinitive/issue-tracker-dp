package com.dp.issuetrackerapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.issuetrackerapp.entity.Issue;

public interface IssueRepository extends JpaRepository<Issue, Integer> {

}
