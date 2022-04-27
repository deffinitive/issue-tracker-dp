package com.dp.issuetrackerapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.service.PersonServiceDao;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private PersonServiceDao personServiceDao;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		String email = authentication.getName();

		System.out.println("email=" + email);

		Person thePerson = personServiceDao.findByEmail(email);

		HttpSession session = request.getSession();
		session.setAttribute("user", thePerson);

		// forward to home(my issues)
		response.sendRedirect(request.getContextPath() + "/issues/listMyIssues");
	}

}
