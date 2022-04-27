package com.dp.issuetrackerapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RoleController {

	@GetMapping("/")
	public String showHome() {

		return "redirect:/issues/listMyIssues";
	}
}
