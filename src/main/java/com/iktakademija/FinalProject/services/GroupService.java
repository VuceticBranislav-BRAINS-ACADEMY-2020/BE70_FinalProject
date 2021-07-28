package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.dtos.GroupDTO;
import com.iktakademija.FinalProject.entities.dtos.NewGroupDTO;

public interface GroupService {
	
	public GroupDTO createDTO(GroupEntity source);
	List<GroupDTO> getDTOList();
	GroupDTO getGroupDTO(Integer groupId);
	GroupDTO setGroup(Integer groupId, NewGroupDTO newGroup);
	GroupDTO removeGroup(Integer groupId);
	GroupEntity createGroup(NewGroupDTO source);
}
