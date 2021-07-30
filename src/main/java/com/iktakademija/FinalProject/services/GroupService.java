package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.GroupDTO;
import com.iktakademija.FinalProject.dtos.NewGroupDTO;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;

public interface GroupService {
	
	GroupDTO createDTO(GroupEntity source);
	List<GroupDTO> getDTOList();
	GroupDTO getGroupDTO(Integer groupId);
	GroupDTO setGroup(Integer groupId, NewGroupDTO newGroup);
	GroupDTO removeGroup(Integer groupId);
	GroupEntity createGroup(NewGroupDTO source);
	
	JoinTableStudentGroup addStudentToGroup(Integer studentId, Integer groupId);
}
