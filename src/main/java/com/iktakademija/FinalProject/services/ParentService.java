package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.dtos.NewParentDTO;
import com.iktakademija.FinalProject.entities.dtos.ParentDTO;
import com.iktakademija.FinalProject.entities.dtos.StudentDTO;

public interface ParentService {	
	ParentDTO createDTO(ParentEntity source);
	List<ParentDTO> createDTOList(List<ParentEntity> source);	
	List<ParentDTO> getDTOList();
	ParentDTO getParentDTO(Integer parentId);
	ParentDTO setParent(Integer parentId, NewParentDTO newParent);
	ParentDTO removeParent(Integer parentId);
	ParentEntity createParent(NewParentDTO source);
	List<StudentDTO> getAllChildrens(ParentEntity childId);	
}
