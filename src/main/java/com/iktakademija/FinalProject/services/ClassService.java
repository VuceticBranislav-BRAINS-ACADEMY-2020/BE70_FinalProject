package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.dtos.ClassDTO;
import com.iktakademija.FinalProject.entities.dtos.NewClassDTO;

public interface ClassService {

	ClassDTO createDTO(ClassEntity source);

	List<ClassDTO> getDTOList();

	ClassDTO getClassDTO(Integer classId);

	ClassDTO removeClass(Integer classId);

	ClassDTO setClass(Integer classId, NewClassDTO newClass);

	ClassEntity createClass(NewClassDTO source);
	
}
