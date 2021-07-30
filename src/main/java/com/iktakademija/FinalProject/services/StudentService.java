package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.NewStudentDTO;
import com.iktakademija.FinalProject.dtos.ParentDTO;
import com.iktakademija.FinalProject.dtos.StudentDTO;
import com.iktakademija.FinalProject.entities.StudentEntity;

public interface StudentService {
	StudentDTO createDTO(StudentEntity source);

	List<StudentDTO> createDTOList(List<StudentEntity> source);

	List<StudentDTO> getDTOList();

	StudentDTO getStudentDTO(Integer studentId);

	StudentDTO setStudent(Integer studentId, NewStudentDTO newStudent);

	StudentDTO removeStudent(Integer studentId);

	StudentEntity createStudent(NewStudentDTO source);

	List<ParentDTO> getAllParents(StudentEntity parentId);
}
