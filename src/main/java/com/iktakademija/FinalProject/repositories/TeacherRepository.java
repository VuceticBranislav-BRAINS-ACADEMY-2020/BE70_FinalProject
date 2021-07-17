package com.iktakademija.FinalProject.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {

	@Override
	public Optional<TeacherEntity> findById(Integer id);

}
