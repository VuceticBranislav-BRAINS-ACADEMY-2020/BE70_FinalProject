package com.iktakademija.FinalProject.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.dtos.GroupDTO;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private ClassService classService;
	
	@Override
	public GroupDTO createDTO(GroupEntity source) {
		GroupDTO retVal = new GroupDTO();
		if (source == null) return retVal;
		retVal.setId(source.getId());
		retVal.setLetter(source.getLetter());
		retVal.setHomeClassMaster(source.getHomeClassMaster());
		retVal.setClazz(classService.createDTO(source.getClazz()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}	
	
}
