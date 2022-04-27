package com.dp.issuetrackerapp.dao;

import com.dp.issuetrackerapp.entity.Role;

public interface RoleDao {

	public Role findRoleByName(String theRoleName);

}
