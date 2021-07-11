package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

	public AdminEntity findByUsername(String username);
	public List<AdminEntity> findAll();
}
