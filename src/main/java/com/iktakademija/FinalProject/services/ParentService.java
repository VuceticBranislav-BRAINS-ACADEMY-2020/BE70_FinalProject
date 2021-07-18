package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;

public interface ParentService {
	
	List<ParentDTO> getDTOList();
	ParentDTO getParentDTO(Integer parentId);
	ParentDTO createDTO(ParentEntity source);
}
