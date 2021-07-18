package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface JoinTableStudentParentRepository extends CrudRepository<JoinTableStudentParent, Integer> {

	@Query(value = "SELECT id FROM parent AS p WHERE p.id IN (SELECT parent FROM student_parent WHERE student = :ID)")
	List<Integer> findParentOfChild(@Param(value = "ID") StudentEntity parentID);
	
	@Query(value = "SELECT id FROM student AS s WHERE s.id IN (SELECT student FROM student_parent WHERE parent = :ID)")
	List<Integer> findChildOfParent(@Param(value = "ID") ParentEntity childID);

}
