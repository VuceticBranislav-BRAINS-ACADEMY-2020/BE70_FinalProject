package com.iktakademija.FinalProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	@Override
	public Optional<StudentEntity> findById(Integer id);

}
