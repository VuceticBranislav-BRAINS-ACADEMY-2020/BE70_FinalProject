package com.iktakademija.FinalProject.repositories;

import org.springframework.data.repository.CrudRepository;

import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.SubjectEntity;

public interface JoinTableSubjectClassRepository extends CrudRepository<JoinTableSubjectClass, Integer> {

	JoinTableSubjectClass findByClazzAndSubject(ClassEntity clazz, SubjectEntity subject);
}
