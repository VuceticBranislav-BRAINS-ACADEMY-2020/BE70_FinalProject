package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.SubjectEntity;

public interface SubjectRepository extends CrudRepository<SubjectEntity, Integer> {

	@Override
	@Query(value = "FROM SubjectEntity AS s WHERE s.id=:id AND s.status<>'DELETED'")
	Optional<SubjectEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM SubjectEntity AS s WHERE s.status<>'DELETED'")
	List<SubjectEntity> findAllUndeleted();
}
