package com.dp.issuetrackerapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.issuetrackerapp.dao.RoleRepository;
import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.entity.Role;
import com.dp.issuetrackerapp.service.PersonService;

@Controller
@RequestMapping("/persons")
public class PersonController {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleRepository roleRepository;

	public PersonController(PersonService thePersonService) {
		personService = thePersonService;
	}

	@GetMapping("/list")
	public String listPersons(Model theModel) {

		List<Person> thePersons = personService.findAll();
		theModel.addAttribute("persons", thePersons);
		return "persons/list-persons";
	}

	@PostMapping("/save")
	public String savePerson(Person person) {

		personService.save(person);
		return "redirect:/persons/list";
	}

	@GetMapping("/edit/{id}")
	public String showEditPersonForm(@PathVariable("id") Integer id, Model model) {

		List<Role> roles = roleRepository.findAll();
		model.addAttribute("roles", roles);

		Person person = personService.findById(id);
		model.addAttribute("person", person);

		return "persons/person-form";
	}

}
