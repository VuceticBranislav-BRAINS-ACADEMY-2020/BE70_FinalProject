package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.ClassEntity;

public interface ClassRepository extends CrudRepository<ClassEntity, Integer> {

	@Query(value = "FROM ClassEntity AS t WHERE t.status <> 'DELETED'")
	List<ClassEntity> findAllUndeleted();

	Optional<ClassEntity> findById(Integer id);

	List<ClassEntity> findAllById(Integer id);

	List<ClassEntity> findAll();
}
