package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.dtos.JoinTableStudentGroupDTO;

public interface JoinTableStudentGroupService {
	JoinTableStudentGroupDTO createDTO(JoinTableStudentGroup source);
	List<JoinTableStudentGroupDTO> getDTOList();
	JoinTableStudentGroupDTO getEntityDTO(Integer id);
	JoinTableStudentGroupDTO setEntity(Integer id, JoinTableStudentGroupDTO newEntity);
	JoinTableStudentGroupDTO removeEntity(Integer id);
	JoinTableStudentGroup createEntity(JoinTableStudentGroupDTO source);
}
