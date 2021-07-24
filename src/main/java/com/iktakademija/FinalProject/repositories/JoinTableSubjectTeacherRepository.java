package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;

public interface JoinTableSubjectTeacherRepository extends CrudRepository<JoinTableSubjectTeacher, Integer> {
	
	List<JoinTableSubjectTeacher> findAllByGroup(Integer group);
	
}
