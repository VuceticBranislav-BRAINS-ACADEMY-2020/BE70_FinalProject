package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.ParentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {
	
	@Override
	public List<ParentEntity> findAll();

}
