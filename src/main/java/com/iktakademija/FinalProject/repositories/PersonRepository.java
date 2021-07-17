package com.iktakademija.FinalProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.PersonEntity;

public interface PersonRepository extends CrudRepository<PersonEntity, Integer> {

	@Override
	public Optional<PersonEntity> findById(Integer id);

}
