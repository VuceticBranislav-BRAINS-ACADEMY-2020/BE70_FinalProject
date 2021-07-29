package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.JoinTableStudentParent;
import com.iktakademija.FinalProject.entities.dtos.JoinTableStudentParentDTO;

public interface JoinTableStudentParentService {
	JoinTableStudentParentDTO createDTO(JoinTableStudentParent source);
	List<JoinTableStudentParentDTO> getDTOList();
	JoinTableStudentParentDTO getEntityDTO(Integer id);
	JoinTableStudentParentDTO setEntity(Integer id, JoinTableStudentParentDTO newEntity);
	JoinTableStudentParentDTO removeEntity(Integer id);
	JoinTableStudentParent createEntity(JoinTableStudentParentDTO source);
}
