package com.iktakademija.FinalProject.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.AddressEntity;
import com.iktakademija.FinalProject.entities.AdminEntity;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.AdminDTO;
import com.iktakademija.FinalProject.entities.dtos.NewAddressDTO;
import com.iktakademija.FinalProject.entities.dtos.NewPersonDTO;
import com.iktakademija.FinalProject.entities.dtos.NewUserDTO;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.AddressRepository;
import com.iktakademija.FinalProject.repositories.AdminRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PersonRepository personRepository;
	
//	@Autowired
//	private StudentRepository studentRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	/************************************************************
	 * Controller related
	 ************************************************************/
	
//	@Override
//	public UserEntity createUser(NewUserDTO source) {
//		
//		Optional<RoleEntity> opr = roleRepository.findById(source.getRoleId());
//		if (opr.isPresent() == false) return null;
//		RoleEntity role = opr.get();
//		
//		Optional<PersonEntity> opp = personRepository.findById(source.getPersonalityId());
//		if (opp.isPresent() == false) return null;
//		PersonEntity personality = opp.get();
//		
//		return new UserEntity(source.getUsername(), source.getPassword(), personality, role);
//	}
	
	@Override
	public AdminEntity createAdmin(NewUserDTO source) {
		
		Optional<RoleEntity> opr = roleRepository.findByRole(source.getRole());
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonalityId());
		if (opp.isPresent() == false) return null;
		PersonEntity personality = opp.get();
		
		return new AdminEntity(source.getUsername(), source.getPassword(), personality, role);
	}
	
	@Override
	public StudentEntity createStudent(NewUserDTO source) {
		
		Optional<RoleEntity> opr = roleRepository.findByRole(source.getRole());
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonalityId());
		if (opp.isPresent() == false) return null;
		PersonEntity personality = opp.get();
		
		return new StudentEntity(source.getUsername(), source.getPassword(), personality, role);		
	}
	
	@Override
	public ParentEntity createParent(NewUserDTO source) {
		
		Optional<RoleEntity> opr = roleRepository.findByRole(source.getRole());
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonalityId());
		if (opp.isPresent() == false) return null;
		PersonEntity personality = opp.get();
		
		return new ParentEntity(source.getUsername(), source.getPassword(), personality, role);		
	}
	
	@Override
	public TeacherEntity createTeacher(NewUserDTO source) {
		
		Optional<RoleEntity> opr = roleRepository.findByRole(source.getRole());
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonalityId());
		if (opp.isPresent() == false) return null;
		PersonEntity personality = opp.get();
		
		return new TeacherEntity(source.getUsername(), source.getPassword(), personality, role);		
	}	
	
	@Override
	public AddressEntity createAddress(NewAddressDTO source) {		
		
		// TODO Check is same address exists in database
		AddressEntity address = new AddressEntity();
		address.setCity(source.getCity());
		address.setStreet(source.getStreet());
		address.setNumber(source.getNumber());
		address.setApartment(source.getApartment());
		address.setStatus(EStatus.ACTIVE);
		
		address = addressRepository.save(address);
		
		return address;
	}
	
	@Override
	public PersonEntity createPerson(NewPersonDTO source) {
		
		Optional<AddressEntity> op = addressRepository.findById(source.getAddress());
		if (op.isPresent() == false) return null;
		AddressEntity address = op.get();
		
		PersonEntity person = new PersonEntity();
		person.setAddress(address);
		person.setBirthdate(source.getBirthdate());
		person.setFirstname(source.getFirstname());
		person.setJmbg(source.getJmbg());
		person.setLastname(source.getLastname());
		person.setMphone(source.getMphone());
		person.setStatus(EStatus.ACTIVE);
		
		person = personRepository.save(person);
		
		return person;
	}
	
	
	/************************************************************
	 * Repository related
	 ************************************************************/
	
	@Override
	public List<AdminDTO> findAllAdmins() {

		// Get list of users from database
		List<AdminEntity> admins = adminRepository.findAll();

		// Return list of converted AdminEntity to AdminDTO
		return admins.stream().map(admin -> new AdminDTO(admin)).collect(Collectors.toList());
	}

}
