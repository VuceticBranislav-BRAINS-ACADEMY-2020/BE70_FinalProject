package com.iktakademija.FinalProject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface JoinTableStudentGroupRepository extends CrudRepository<JoinTableStudentGroup, Integer> {

	JoinTableStudentGroup findByStudentAndGroup(StudentEntity student, GroupEntity group);
	
}
