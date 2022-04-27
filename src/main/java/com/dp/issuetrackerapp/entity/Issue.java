package com.dp.issuetrackerapp.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
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
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "issue")
public class Issue {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "issue_id")
	private Integer issueId;

	@Column(name = "issue_name")
	private String issueName;

	@Column(name = "issue_description")
	private String issueDescription;

	@Column(name = "estimated_time")
	private String estimatedTime;

	@Column(name = "spent_time")
	private String spentTime;

	@Column(name = "created")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime created;

	@Column(name = "created_by")
	private String createdBy;

	@Column(name = "priority")
	private String priority;

	@Column(name = "status")
	private String status;

	@Column(name = "type")
	private String type;

	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "person_issue",
			joinColumns = @JoinColumn(name = "issue_issue_id"),
			inverseJoinColumns = @JoinColumn(name = "person_person_id"))
	private Set<Person> persons = new HashSet<>();

	public void addPerson(Person person) {
		this.persons.add(person);
	}


	public Set<Person> getPersons() {
		return persons;
	}

	public void setPersons(Set<Person> persons) {
		this.persons = persons;
	}

	@ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinColumn(name = "project_project_id")
	private Project project;

	public Issue() {
	}

	public Issue(String issueName, String issueDescription, String estimatedTime, String spentTime,
	             LocalDateTime created, String createdBy, String priority, String status, String type, Set<Person> persons,
	             Project project) {
		super();
		this.issueName = issueName;
		this.issueDescription = issueDescription;
		this.estimatedTime = estimatedTime;
		this.spentTime = spentTime;
		this.created = created;
		this.createdBy = createdBy;
		this.priority = priority;
		this.status = status;
		this.type = type;
		this.persons = persons;
		this.project = project;

	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Integer getIssueId() {
		return issueId;
	}

	public void setIssueId(Integer issueId) {
		this.issueId = issueId;
	}

	public String getIssueName() {
		return issueName;
	}

	public void setIssueName(String issueName) {
		this.issueName = issueName;
	}

	public String getIssueDescription() {
		return issueDescription;
	}

	public void setIssueDescription(String issueDescription) {
		this.issueDescription = issueDescription;
	}

	public String getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(String estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getSpentTime() {
		return spentTime;
	}

	public void setSpentTime(String spentTime) {
		this.spentTime = spentTime;
	}

	@Override
	public String toString() {
		return "Issue [id=" + issueId + ", issueName=" + issueName + ", issueDescription=" + issueDescription
				+ ", estimatedTime=" + estimatedTime + ", created=" + created + ", createdBy=" + createdBy
				+ ", priority=" + priority + ", status=" + status + "]";
	}


}
