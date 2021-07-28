package com.iktakademija.FinalProject.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;

public interface RoleRepository extends CrudRepository<RoleEntity, Integer> {
	
	public Optional<RoleEntity> findById(Integer id);
	
	// Return ERole enumeration from RoleEntity for given Id
	@Query(value = "FROM RoleEntity AS r WHERE r.id = :ID")
	public ERole findRoleById(@Param("ID") Integer id);
	
	// Return ERole enumeration from RoleEntity for given role name
	@Query(value = "FROM RoleEntity AS r WHERE r.role = :Role")
	public Optional<RoleEntity> findByRole(@Param("Role") ERole role);
	
}
