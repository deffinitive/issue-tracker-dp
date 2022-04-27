package com.dp.issuetrackerapp.service;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dp.issuetrackerapp.dao.PersonDao;
import com.dp.issuetrackerapp.dao.RoleDao;
import com.dp.issuetrackerapp.entity.Person;
import com.dp.issuetrackerapp.entity.Role;
import com.dp.issuetrackerapp.person.newPerson;

@Service
public class PersonServiceDaoImpl implements PersonServiceDao {

	@Autowired
	private PersonDao personDao;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Override
	@Transactional
	public Person findByEmail(String email) {

		return personDao.findByEmail(email);
	}


	@Override
	@Transactional
	public void save(newPerson newPerson) {
		Person person = new Person();

		person.setPassword(passwordEncoder.encode(newPerson.getPassword()));
		person.setFirstName(newPerson.getFirstName());
		person.setLastName(newPerson.getLastName());
		person.setEmail(newPerson.getEmail());

		// default role zamestnanec
		person.setRoles(Arrays.asList(roleDao.findRoleByName("Zamestnanec")));

		personDao.save(person);
	}

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Person person = personDao.findByEmail(email);
		if (person == null) {
			throw new UsernameNotFoundException("Invalid email or password.");
		}
		return new User(person.getEmail(), person.getPassword(),
				mapRolesToAuthorities(person.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}
}
