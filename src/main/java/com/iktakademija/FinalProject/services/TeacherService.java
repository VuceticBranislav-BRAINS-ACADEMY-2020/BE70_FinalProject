package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface TeacherService {
	
	TeacherDTO createDTO(TeacherEntity source);
	List<TeacherDTO> getDTOList();
	TeacherDTO getTeacherDTO(Integer teacherId);
	TeacherDTO setTeacher(Integer teacherId, NewTeacherDTO newTeacher);
	TeacherDTO removeTeacher(Integer teacherId);
	TeacherEntity createTeacher(NewTeacherDTO source);
	
	boolean doStudentListenSubjectFromTeeacherGroup(Integer student, Integer subject, Integer teacher, Integer group);
	List<GradeDTO> findAllGradesForStudentsAndSubjects(TeacherEntity teacher);
	List<SubjectEntity> getAllSubjectsByTeacher(Integer teacherId);
	
}
