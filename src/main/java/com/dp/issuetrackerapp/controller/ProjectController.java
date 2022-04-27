package com.dp.issuetrackerapp.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.entity.Project;
import com.dp.issuetrackerapp.service.PersonServiceDao;
import com.dp.issuetrackerapp.service.PersonService;
import com.dp.issuetrackerapp.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	private EntityManager entityManager;
	private ProjectService projectService;

	@Autowired
	private PersonService personService;

	@Autowired
	private PersonServiceDao personServiceDao;

	public ProjectController(ProjectService theProjectService) {
		projectService = theProjectService;
	}

	@GetMapping("/list")
	public String listProjects(Model theModel) {

		List<Project> theProjects = projectService.findAll();
		theModel.addAttribute("projects", theProjects);

		return "projects/list-projects";
	}

	@GetMapping("/listMyProjects")
	public String listMyProjects(Model theModel, Authentication authentication) {

		Session currentSession = entityManager.unwrap(Session.class);

		Query<Person> theQuery = currentSession.createQuery("from Person where email=:eMail", Person.class);
		String theEmail;

		theEmail = authentication.getName();

		theQuery.setParameter("eMail", theEmail);
		Integer id = null;
		try {
			id = theQuery.getSingleResult().getId();
		} catch (Exception e) {
			id = null;
		}

		Person person = personServiceDao.findByEmail(theEmail);
		List<Project> projects = person.getProjects();

		theModel.addAttribute("projects", projects);

		return "projects/list-projects";
	}


	@PostMapping("/listPersonsProjects")
	public String listPersonsProjects(Model theModel, @RequestParam("projectId") int theId) {

		Project project = projectService.findById(theId);
		Set<Person> persons = project.getPersons();

		theModel.addAttribute("persons", persons);

		return "projects/list-projects";
	}


	@GetMapping("/showFormNewProject")
	public String showFormNewProject(Model theModel) {


		List<Person> persons = personService.findAll();
		theModel.addAttribute("persons", persons);

		theModel.addAttribute("project", new Project());

		return "projects/project-form";
	}

	@PostMapping("/showFormChangeProject")
	public String showFormChangeProject(@RequestParam("projectId") int theId,
	                                    Model theModel) {


		List<Person> persons = personService.findAll();

		theModel.addAttribute("persons", persons);
		Project theProject = projectService.findById(theId);

		theModel.addAttribute("project", theProject);

		return "projects/project-form-update";
	}


	@PostMapping("/save")
	public String saveProject(@ModelAttribute("project") Project theProject) {

		if (theProject.getCreated() == null) {
			theProject.setCreated(LocalDateTime.now());
		}

		projectService.save(theProject);

		return "redirect:/projects/list";
	}

	@PostMapping("/saveUpdate")
	public String saveUpdate(@ModelAttribute("project") Project theProject) {

		projectService.save(theProject);
		return "redirect:/projects/list";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteProject(@RequestParam(name = "projectId") int projectId) {

		projectService.deleteById(projectId);
		return "redirect:/projects/list";
	}
}
