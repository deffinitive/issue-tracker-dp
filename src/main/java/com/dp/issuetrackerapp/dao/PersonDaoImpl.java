package com.dp.issuetrackerapp.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dp.issuetrackerapp.entity.Issue;
import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.entity.Project;

@Repository
public class PersonDaoImpl implements PersonDao {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Person findByEmail(String theEmail) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Person> theQuery = currentSession.createQuery("from Person where email=:eMail", Person.class);
		theQuery.setParameter("eMail", theEmail);
		Person thePerson = null;
		try {
			thePerson = theQuery.getSingleResult();
		} catch (Exception e) {
			thePerson = null;
		}

		return thePerson;
	}

	@Override
	@Transactional
	public List<Issue> findIssuesByCurrentPersonId(String theEmail) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Person> theQuery = currentSession.createQuery("from Person where email=:eMail", Person.class);
		theQuery.setParameter("eMail", theEmail);
		Integer id = null;
		try {
			id = theQuery.getSingleResult().getId();
		} catch (Exception e) {
			id = null;
		}

		Query<Issue> theQueryIssue = currentSession.createQuery("from Issue where issue_id=:issueId", Issue.class);
		theQueryIssue.setParameter("issueId", id);

		List<Issue> issues = theQueryIssue.getResultList();

		return issues;
	}

	@Override
	@Transactional
	public List<Project> findProjectsByCurrentPersonId(String theEmail) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Person> theQuery = currentSession.createQuery("from Person where email=:eMail", Person.class);
		theQuery.setParameter("eMail", theEmail);
		Integer id = null;
		try {
			id = theQuery.getSingleResult().getId();
		} catch (Exception e) {
			id = null;
		}

		Query<Project> theQueryIssue = currentSession.createQuery("from Project where project_id=:projectId", Project.class);
		theQueryIssue.setParameter("issueId", id);

		List<Project> projects = theQueryIssue.getResultList();

		return projects;
	}

	@Override
	public void save(Person thePerson) {

		Session currentSession = entityManager.unwrap(Session.class);

		currentSession.saveOrUpdate(thePerson);
	}
}
