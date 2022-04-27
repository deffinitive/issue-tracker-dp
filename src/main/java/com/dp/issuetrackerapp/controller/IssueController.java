package com.dp.issuetrackerapp.controller;

import java.time.LocalDateTime;
import java.util.List;

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

import com.dp.issuetrackerapp.entity.Issue;
import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.service.IssueService;
import com.dp.issuetrackerapp.service.PersonServiceDao;
import com.dp.issuetrackerapp.service.PersonService;

@Controller
@RequestMapping("/issues")
public class IssueController {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private PersonServiceDao personServiceDao;

	@Autowired
	private PersonService personService;

	private IssueService issueService;

	public IssueController(IssueService theIssueService) {
		issueService = theIssueService;
	}

	@GetMapping("/list")
	public String listIssues(Model theModel) {

		List<Issue> theIssues = issueService.findAll();
		theModel.addAttribute("issues", theIssues);
		return "issues/list-issues";
	}


	@GetMapping("/listMyIssues")
	public String listMyIssues(Model theModel, Authentication authentication) {

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
		List<Issue> issues = person.getIssues();

		theModel.addAttribute("issues", issues);

		return "issues/list-issues";
	}

	@GetMapping("/showFormNewIssue")
	public String showFormNewIssue(Model theModel) {

		List<Person> persons = personService.findAll();

		theModel.addAttribute("persons", persons);
		theModel.addAttribute("issue", new Issue());

		return "issues/issue-form";
	}

	@PostMapping("/showFormChangeIssue")
	public String showFormChangeIssue(@RequestParam("issueId") int theId,
	                                  Model theModel) {

		Issue theIssue = issueService.findById(theId);

		theModel.addAttribute("issue", theIssue);
		List<Person> persons = personService.findAll();
		theModel.addAttribute("persons", persons);

		return "issues/issue-form-update";
	}

	@PostMapping("/save")
	public String saveIssue(Issue theIssue) {

		if (theIssue.getCreated() == null) {
			theIssue.setCreated(LocalDateTime.now());
		}

		issueService.save(theIssue);

		return "redirect:/issues/list";
	}

	@PostMapping("/saveUpdate")
	public String saveIssueUpdate(@ModelAttribute("issue") Issue theIssue, @ModelAttribute("person") Person thePerson) {

		issueService.save(theIssue);
		return "redirect:/issues/list";
	}


	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String deleteIssue(@RequestParam(name = "issueId") int issueId) {
		issueService.deleteById(issueId);
		return "redirect:/issues/list";
	}

}
