package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.RoleDTO;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;

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
	
	@Override
	public List<ERole> getRoleFromStringList(List<String> list) {
		
		// Prepare rturn value
		List<ERole> retVal = new ArrayList<ERole>();
		
		// Convert all string to roles
		ERole role;
		for (String string : list) {
			role = ERole.valueOf(string);
			if (role != null) {
				retVal.add(null);
			}
		}
		return retVal;
	}
	
	@Override
	public ERole getRoleFromString(String role) {

		return ERole.valueOf(role);
	}
	
}
