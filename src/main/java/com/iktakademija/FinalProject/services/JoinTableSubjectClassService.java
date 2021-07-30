package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.JoinTableSubjectClassDTO;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;

public interface JoinTableSubjectClassService {
	JoinTableSubjectClassDTO createDTO(JoinTableSubjectClass source);

	List<JoinTableSubjectClassDTO> getDTOList();

	JoinTableSubjectClassDTO getEntityDTO(Integer id);

	JoinTableSubjectClassDTO setEntity(Integer id, JoinTableSubjectClassDTO newEntity);

	JoinTableSubjectClassDTO removeEntity(Integer id);

	JoinTableSubjectClass createEntity(JoinTableSubjectClassDTO source);
}
