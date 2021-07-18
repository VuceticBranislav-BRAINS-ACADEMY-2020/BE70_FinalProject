package com.iktakademija.FinalProject.services;

import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.dtos.RoleDTO;

public interface RoleService {
	
	public RoleDTO createDTO(RoleEntity source);
	
}
