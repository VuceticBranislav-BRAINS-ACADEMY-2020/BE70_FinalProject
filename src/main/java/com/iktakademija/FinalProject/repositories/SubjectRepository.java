package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.SubjectEntity;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer> {

	Optional<SubjectEntity> findById(Integer id);

	@Query(value = "FROM SubjectEntity AS s WHERE s.status <> 'DELETED'")
	List<SubjectEntity> findAllUndeleted();

	List<SubjectEntity> findAllById(Integer id);

	List<SubjectEntity> findAll();
}
