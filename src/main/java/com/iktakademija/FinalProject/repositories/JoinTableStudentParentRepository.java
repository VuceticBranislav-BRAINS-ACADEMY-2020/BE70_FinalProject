package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface JoinTableStudentParentRepository extends CrudRepository<JoinTableStudentParent, Integer> {

	@Query(value = "SELECT id FROM ParentEntity AS p WHERE p.id IN (SELECT parent FROM JoinTableStudentParent WHERE student = :ID)")
	List<Integer> findParentOfChild(@Param(value = "ID") StudentEntity parentID);
	
	@Query(value = "SELECT id FROM StudentEntity AS s WHERE s.id IN (SELECT student FROM JoinTableStudentParent WHERE parent = :ID)")
	List<Integer> findChildOfParent(@Param(value = "ID") ParentEntity childID);
	
	@Override
	List<JoinTableStudentParent> findAll();
	
	@Override
	Optional<JoinTableStudentParent> findById(Integer id);
	
	List<JoinTableStudentParent> findByStudent(StudentEntity student);
	
	List<JoinTableStudentParent> findByParent(ParentEntity parent);
}
