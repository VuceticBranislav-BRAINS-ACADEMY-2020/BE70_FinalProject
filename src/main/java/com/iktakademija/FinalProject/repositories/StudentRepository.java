package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {

	Optional<StudentEntity> findById(Integer id);

	@Query(value = "FROM StudentEntity AS s WHERE s.status <> 'DELETED'")
	List<StudentEntity> findAllUndeleted();

	@Query(value = "FROM StudentEntity AS s WHERE s.id IN (SELECT student FROM JoinTableStudentParent WHERE parent = :ID)")
	List<StudentEntity> findAllChildrens(@Param(value = "ID") ParentEntity parentID);

	List<StudentEntity> findAllById(Integer id);

	List<StudentEntity> findAll();
}
