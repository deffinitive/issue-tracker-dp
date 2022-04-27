package com.dp.issuetrackerapp.controller;

import java.util.logging.Logger;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.person.newPerson;
import com.dp.issuetrackerapp.service.PersonServiceDao;

@Controller
@RequestMapping("/register")
public class RegistrationController {

	@Autowired
	private PersonServiceDao personServiceDao;

	private Logger logger = Logger.getLogger(getClass().getName());

	@InitBinder
	public void initBinder(WebDataBinder dataBinder) {

		StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);

		dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
	}

	@GetMapping("/showRegistrationForm")
	public String showMyLoginPage(Model theModel) {

		theModel.addAttribute("newPerson", new newPerson());

		return "registration-form";
	}

	@PostMapping("/processRegistrationForm")
	public String processRegistrationForm(
			@Valid @ModelAttribute("newPerson") newPerson theNewPerson,
			BindingResult theBindingResult,
			Model theModel) {

		String email = theNewPerson.getEmail();
		logger.info("Processing registration form for: " + email);

		if (theBindingResult.hasErrors()) {
			return "registration-form";
		}

		Person existing = personServiceDao.findByEmail(email);
		if (existing != null) {
			theModel.addAttribute("newPerson", new newPerson());
			theModel.addAttribute("registrationError", "Email používateľa už existuje.");

			logger.warning("Email používateľa už existuje.");
			return "registration-form";
		}

		personServiceDao.save(theNewPerson);

		logger.info("Úspešne vytvorený účet: " + email);

		return "registration-confirmation";
	}
}
