package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;

public interface TeacherService {
	TeacherDTO createDTO(TeacherEntity source);
	List<TeacherDTO> getDTOList();
	TeacherDTO getTeacherDTO(Integer teacherId);
	TeacherDTO setTeacher(Integer teacherId, NewUserDTO newTeacher);
	TeacherDTO removeTeacher(Integer teacherId);
	TeacherEntity createTeacher(NewUserDTO source);
}
