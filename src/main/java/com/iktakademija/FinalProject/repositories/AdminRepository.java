package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.AdminEntity;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {
	
	@Override
	public List<AdminEntity> findAll();

}
