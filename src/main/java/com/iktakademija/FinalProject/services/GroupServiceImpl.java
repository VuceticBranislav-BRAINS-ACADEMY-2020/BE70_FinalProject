package com.iktakademija.FinalProject.services;

import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.dtos.GroupDTO;

@Service
public class GroupServiceImpl implements GroupService {

	@Override
	public GroupDTO createDTO(GroupEntity source) {
		GroupDTO retVal = new GroupDTO();
		if (source == null) return retVal;
		retVal.setId(source.getId());
		retVal.setLetter(source.getLetter());
		retVal.setHomeClassMaster(source.getHomeClassMaster());
		retVal.setClazz(source.getClazz());
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

}
