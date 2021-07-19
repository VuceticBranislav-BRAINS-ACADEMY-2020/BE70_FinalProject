package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAdminDTO;

public interface AdminService {
	
	/************************************************************
	 * Controller related
	 ************************************************************/
	AdminDTO createDTO(AdminEntity source);	
	List<AdminDTO> getDTOList();
	AdminDTO getAdminDTO(Integer adminId);
	AdminDTO setAdmin(Integer adminId, NewAdminDTO newAdmin);
	AdminDTO removeAdmin(Integer adminId);
	AdminEntity createAdmin(NewAdminDTO source);
	StudentEntity createStudent(NewAdminDTO source);	

	/************************************************************
	 * Repository related
	 ************************************************************/
	List<AdminDTO> findAllAdmins();
	
	
}
