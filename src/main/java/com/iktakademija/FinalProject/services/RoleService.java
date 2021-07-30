package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.dtos.RoleDTO;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;

public interface RoleService {

	public RoleDTO createDTO(RoleEntity source);

	public List<ERole> getRoleFromStringList(List<String> list);

	public ERole getRoleFromString(String role);

}
