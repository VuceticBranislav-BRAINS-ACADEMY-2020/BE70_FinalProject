package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {

	@Override
	@Query(value = "FROM TeacherEntity AS t WHERE t.id=:id AND t.status<>'DELETED'")
	Optional<TeacherEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM TeacherEntity AS t WHERE t.status<>'DELETED'")
	List<TeacherEntity> findAllUndeleted();
}
