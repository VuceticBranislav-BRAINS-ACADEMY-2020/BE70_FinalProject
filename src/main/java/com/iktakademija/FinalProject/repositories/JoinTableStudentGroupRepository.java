package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface JoinTableStudentGroupRepository extends CrudRepository<JoinTableStudentGroup, Integer> {

	JoinTableStudentGroup findByStudentAndGroup(StudentEntity student, GroupEntity group);
	
	@Override
	List<JoinTableStudentGroup> findAll();
	
	@Override
	Optional<JoinTableStudentGroup> findById(Integer id);
	
}
