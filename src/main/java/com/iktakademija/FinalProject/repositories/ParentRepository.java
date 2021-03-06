package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface ParentRepository extends CrudRepository<ParentEntity, Integer> {

	Optional<ParentEntity> findById(Integer id);

	@Query(value = "FROM ParentEntity AS p WHERE p.status <> 'DELETED'")
	List<ParentEntity> findAllUndeleted();

	@Query(value = "FROM ParentEntity AS p WHERE p.id IN (SELECT parent FROM JoinTableStudentParent WHERE student = :ID)")
	List<ParentEntity> findAllParents(@Param(value = "ID") StudentEntity childID);

	@Query(value = "FROM StudentEntity AS s WHERE s.id IN (SELECT student FROM JoinTableStudentParent WHERE parent = :ID)")
	List<StudentEntity> findAllChildrens(@Param(value = "ID") ParentEntity parentID);

}
