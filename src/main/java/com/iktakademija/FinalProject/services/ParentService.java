package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;

public interface ParentService {	
	ParentDTO createDTO(ParentEntity source);
	List<ParentDTO> getDTOList();
	ParentDTO getParentDTO(Integer parentId);
	ParentDTO setParent(Integer parentId, NewUserDTO newParent);
	ParentDTO removeParent(Integer parentId);
	ParentEntity createParent(NewUserDTO source);	
}
