package com.iktakademija.FinalProject.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface JoinTableSubjectTeacherRepository extends CrudRepository<JoinTableSubjectTeacher, Integer> {
	
	List<JoinTableSubjectTeacher> findAllByGroup(Integer group);
	
	@Query(value = "FROM JoinTableSubjectTeacher WHERE teachers = :teacher AND sub_cls = :sub_cls AND group = :group")
	JoinTableSubjectTeacher findByTeachersAndSubclsAndGroup(@Param("teacher") TeacherEntity teacher, @Param("sub_cls") JoinTableSubjectClass sub_cls, @Param("group") GroupEntity group);
}
