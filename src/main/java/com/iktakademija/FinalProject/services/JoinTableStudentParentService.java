package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.JoinTableStudentParentDTO;
import com.iktakademija.FinalProject.entities.JoinTableStudentParent;

public interface JoinTableStudentParentService {
	JoinTableStudentParentDTO createDTO(JoinTableStudentParent source);
	List<JoinTableStudentParentDTO> getDTOList();
	JoinTableStudentParentDTO getEntityDTO(Integer id);
	JoinTableStudentParentDTO setEntity(Integer id, JoinTableStudentParentDTO newEntity);
	JoinTableStudentParentDTO removeEntity(Integer id);
	JoinTableStudentParent createEntity(JoinTableStudentParentDTO source);
}
