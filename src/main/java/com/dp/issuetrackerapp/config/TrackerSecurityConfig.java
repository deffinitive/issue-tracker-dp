package com.dp.issuetrackerapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.dp.issuetrackerapp.service.PersonServiceDao;

@Configuration
@EnableWebSecurity
public class TrackerSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PersonServiceDao personServiceDao;

	@Autowired
	private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.authorizeRequests()
				.antMatchers("/").hasAuthority("Zamestnanec")
				.antMatchers("/projects/**").hasAuthority("Zamestnanec")
				.antMatchers("/issues/**").hasAuthority("Zamestnanec")
				.antMatchers("/register/**").hasAuthority("Administrátor")
				.antMatchers("/persons/list").hasAnyAuthority("Administrátor","Manažér")
				.antMatchers("/persons/edit/**").hasAuthority("Administrátor")
				.and()
				.formLogin()
				.loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser")
				.successHandler(customAuthenticationSuccessHandler)
				.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/access-denied");

	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
		auth.setUserDetailsService(personServiceDao); //custom user details service
		auth.setPasswordEncoder(passwordEncoder()); //bcrypt password encoder
		return auth;
	}

}






