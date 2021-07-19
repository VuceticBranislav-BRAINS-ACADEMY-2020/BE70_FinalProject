package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewStudentDTO;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;

public interface StudentService {	
	StudentDTO createDTO(StudentEntity source);	
	List<StudentDTO> getDTOList();
	StudentDTO getStudentDTO(Integer studentId);
	StudentDTO setStudent(Integer studentId, NewStudentDTO newStudent);
	StudentDTO removeStudent(Integer studentId);	
	StudentEntity createStudent(NewStudentDTO source);
}
