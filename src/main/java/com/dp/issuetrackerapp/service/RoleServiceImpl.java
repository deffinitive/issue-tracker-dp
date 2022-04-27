package com.dp.issuetrackerapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dp.issuetrackerapp.dao.RoleRepository;
import com.dp.issuetrackerapp.entity.Role;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleRepository roleRepository;

	@Autowired
	public RoleServiceImpl(RoleRepository theRoleRepository) {
		roleRepository = theRoleRepository;
	}

	@Override
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

}
