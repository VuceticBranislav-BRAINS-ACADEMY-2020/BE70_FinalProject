package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.ClassDTO;
import com.iktakademija.FinalProject.dtos.NewClassDTO;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;

public interface ClassService {

	ClassDTO createDTO(ClassEntity source);
	List<ClassDTO> getDTOList();
	ClassDTO getClassDTO(Integer classId);
	ClassDTO removeClass(Integer classId);
	ClassDTO setClass(Integer classId, NewClassDTO newClass);
	ClassEntity createClass(NewClassDTO source);
	
	JoinTableSubjectClass addSubjectToClass(Integer subjectId, Integer classId, Integer fond);
}
