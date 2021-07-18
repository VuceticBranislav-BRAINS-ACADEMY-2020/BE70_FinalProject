package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.dtos.GroupDTO;

public interface GroupService {
	
	public GroupDTO createDTO(GroupEntity source);
	
}
