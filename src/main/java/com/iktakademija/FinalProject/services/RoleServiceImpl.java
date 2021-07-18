package com.iktakademija.FinalProject.services;

import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.dtos.RoleDTO;

@Service
public class RoleServiceImpl implements RoleService {
	
	@Override
	public RoleDTO createDTO(RoleEntity source) {
		
		RoleDTO retVal = new RoleDTO();		
		retVal.setId(source.getId());
		retVal.setRoleId(source.getRole());
		retVal.setVersion( source.getVersion());
		retVal.setStatus( source.getStatus());
		return retVal;
		
	}
	
}
