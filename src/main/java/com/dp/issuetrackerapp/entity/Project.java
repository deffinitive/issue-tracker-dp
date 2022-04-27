package com.dp.issuetrackerapp.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "project")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "project_id")
	private Integer projectId;

	@Column(name = "project_name")
	private String projectName;

	@Column(name = "target_end_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate targetEndDate;

	@Column(name = "actual_end_date")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate actualEndDate;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime created;

	@OneToMany(mappedBy = "project",
			cascade = {CascadeType.PERSIST, CascadeType.MERGE,
					CascadeType.DETACH, CascadeType.REFRESH})
	private List<Issue> issues;


	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "person_project",
			joinColumns = @JoinColumn(name = "project_project_id"),
			inverseJoinColumns = @JoinColumn(name = "person_person_id"))
	private Set<Person> persons = new HashSet<>();

	public Project() {
	}

	public Project(String projectName, LocalDate targetEndDate, LocalDate actualEndDate, String createdBy,
	               LocalDateTime created, List<Issue> issues, Set<Person> persons) {
		super();
		this.projectName = projectName;
		this.targetEndDate = targetEndDate;
		this.actualEndDate = actualEndDate;
		this.createdBy = createdBy;
		this.created = created;
		this.issues = issues;
		this.persons = persons;
	}

	public Integer getProjectId() {
		return projectId;
	}

	public void setProjectId(Integer projectId) {
		this.projectId = projectId;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public LocalDate getTargetEndDate() {
		return targetEndDate;
	}

	public void setTargetEndDate(LocalDate targetEndDate) {
		this.targetEndDate = targetEndDate;
	}

	public LocalDate getActualEndDate() {
		return actualEndDate;
	}

	public void setActualEndDate(LocalDate actualEndDate) {
		this.actualEndDate = actualEndDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Project [projectId=" + projectId + ", projectName=" + projectName + ", targetEndDate="
				+ targetEndDate + ", actualEndDate=" + actualEndDate + ", createdBy=" + createdBy
				+ ", created=" + created + ", issues=" + issues + "]";
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}


	public void addPerson(Person person) {
		this.persons.add(person);
	}


	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	public void add(Issue tempIssue) {

		if (issues == null) {
			issues = new ArrayList<>();
		}

		issues.add(tempIssue);

		tempIssue.setProject(this);
	}


}
