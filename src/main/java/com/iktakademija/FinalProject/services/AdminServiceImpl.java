package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.AdminDTO;
import com.iktakademija.FinalProject.dtos.NewAdminDTO;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.AdminRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	/************************************************************
	 * Controller related
	 ************************************************************/

	@Override
	public AdminEntity createAdmin(NewAdminDTO source) {
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_ADMIN);
		if (opr.isPresent() == false)
			return null;
		RoleEntity role = opr.get();

		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();

		AdminEntity admin = new AdminEntity(source.getUsername(), Encryption.passwordEncode(source.getPassword()),
				person, role);
		admin.setEmail(source.getEmail());
		return adminRepository.save(admin);
	}

	@Override
	public StudentEntity createStudent(NewAdminDTO source) {
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_STUDENT);
		if (opr.isPresent() == false)
			return null;
		RoleEntity role = opr.get();

		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();
		StudentEntity student = new StudentEntity(source.getUsername(), Encryption.passwordEncode(source.getPassword()),
				person, role);
		return student;
	}

	@Override
	public AdminDTO createDTO(AdminEntity source) {
		AdminDTO retVal = new AdminDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPerson(personService.createDTO(source.getPerson()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setEmail(source.getEmail());
		return retVal;
	}

	/************************************************************
	 * Repository related
	 ************************************************************/

	@Override
	public List<AdminDTO> findAllAdmins() {

		// Get list of users from database
		List<AdminEntity> admins = adminRepository.findAll();

		// Return list of converted AdminEntity to AdminDTO
		return admins.stream().map(admin -> this.createDTO(admin)).collect(Collectors.toList());
	}

	@Override
	public List<AdminDTO> getDTOList() {
		List<AdminDTO> list = new ArrayList<>();
		for (AdminEntity admin : adminRepository.findAllUndeleted())
			list.add(this.createDTO(admin));
		return list;
	}

	@Override
	public AdminDTO getAdminDTO(Integer adminId) {
		Optional<AdminEntity> op = adminRepository.findById(adminId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public AdminDTO setAdmin(Integer adminId, NewAdminDTO newAdmin) {
		Optional<AdminEntity> opa = adminRepository.findById(adminId);
		if (opa.isPresent() == false)
			return null;
		AdminEntity admin = opa.get();
		if (newAdmin.getPassword() != null)
			admin.setPassword(Encryption.passwordEncode(newAdmin.getPassword()));

		Optional<PersonEntity> opp = personRepository.findById(newAdmin.getPersonId());
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();

		if (newAdmin.getPersonId() != null)
			admin.setPerson(person); // TODO Fix this
		if (newAdmin.getUsername() != null)
			admin.setUsername(newAdmin.getUsername());
		if (newAdmin.getEmail() != null)
			admin.setEmail(newAdmin.getEmail());

		admin = adminRepository.save(admin);
		return this.createDTO(admin);
	}

	@Override
	public AdminDTO removeAdmin(Integer adminId) {
		Optional<AdminEntity> op = adminRepository.findById(adminId);
		if (op.isPresent() == false)
			return null;
		AdminEntity admin = op.get();
		admin.setStatus(EStatus.DELETED);
		admin = adminRepository.save(admin);
		return this.createDTO(admin);
	}

}
