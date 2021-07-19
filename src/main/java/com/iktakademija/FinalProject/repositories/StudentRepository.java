package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	@Override
	@Query(value = "FROM student AS s WHERE s.id=:id AND s.status<>'DELETED'")
	Optional<StudentEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM student AS s WHERE s.status<>'DELETED'")
	List<StudentEntity> findAllUndeleted();
}
