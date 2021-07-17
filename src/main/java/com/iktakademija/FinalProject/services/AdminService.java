package com.iktakademija.FinalProject.services;

import java.util.List;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;

public interface AdminService {
	
	// Controller related
	//UserEntity createUser(NewUserDTO source);	
	AdminEntity createAdmin(NewUserDTO source);
	StudentEntity createStudent(NewUserDTO source);
	ParentEntity createParent(NewUserDTO source);
	TeacherEntity createTeacher(NewUserDTO source);	
	AddressEntity createAddress(NewAddressDTO source);
	
	// Repository related
	List<AdminDTO> findAllAdmins();
	
}
