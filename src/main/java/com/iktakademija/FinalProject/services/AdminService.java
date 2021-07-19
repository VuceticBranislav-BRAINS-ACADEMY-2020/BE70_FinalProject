package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;

public interface AdminService {
	
	/************************************************************
	 * Controller related
	 ************************************************************/
	AdminDTO createDTO(AdminEntity source);	
	List<AdminDTO> getDTOList();
	AdminDTO getAdminDTO(Integer adminId);
	AdminDTO setAdmin(Integer adminId, NewUserDTO newAdmin);
	AdminDTO removeAdmin(Integer adminId);
	//UserEntity createUser(NewUserDTO source);	
	AdminEntity createAdmin(NewUserDTO source);
	StudentEntity createStudent(NewUserDTO source);
//	ParentEntity createParent(NewUserDTO source);
//	TeacherEntity createTeacher(NewUserDTO source);		

	/************************************************************
	 * Repository related
	 ************************************************************/
	List<AdminDTO> findAllAdmins();
	
	
}
