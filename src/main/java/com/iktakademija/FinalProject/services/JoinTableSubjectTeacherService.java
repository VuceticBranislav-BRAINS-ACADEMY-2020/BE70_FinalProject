package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.JoinTableSubjectTeacherDTO;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;

public interface JoinTableSubjectTeacherService {
	JoinTableSubjectTeacherDTO createDTO(JoinTableSubjectTeacher source);
	List<JoinTableSubjectTeacherDTO> getDTOList();
	JoinTableSubjectTeacherDTO getEntityDTO(Integer id);
	JoinTableSubjectTeacherDTO setEntity(Integer id, JoinTableSubjectTeacherDTO newEntity);
	JoinTableSubjectTeacherDTO removeEntity(Integer id);
	JoinTableSubjectTeacher createEntity(JoinTableSubjectTeacherDTO source);
	
	JoinTableSubjectTeacher addTeacherToSubjectByGroup(Integer teacherid, Integer subjectid, Integer groupid);
}
