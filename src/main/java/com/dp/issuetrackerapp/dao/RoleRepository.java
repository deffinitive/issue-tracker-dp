package com.dp.issuetrackerapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dp.issuetrackerapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

}
