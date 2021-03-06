package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.dtos.NewStudentDTO;
import com.iktakademija.FinalProject.dtos.ParentDTO;
import com.iktakademija.FinalProject.dtos.StudentDTO;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.GradeRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private ParentService parentService;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeService gradeService;

	@Override
	public StudentEntity createStudent(NewStudentDTO source) {
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
		student = studentRepository.save(student);
		return student;
	}

	@Override
	public StudentDTO createDTO(StudentEntity source) {
		StudentDTO retVal = new StudentDTO();
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPerson(personService.createDTO(source.getPerson()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setGrades(getAllGradesForStudent(source));
		return retVal;
	}

	@Override
	public List<StudentDTO> createDTOList(List<StudentEntity> source) {
		List<StudentDTO> list = new ArrayList<>();
		for (StudentEntity student : source)
			list.add(this.createDTO(student));
		return list;
	}

	@Override
	public List<StudentDTO> getDTOList() {
		return this.createDTOList(studentRepository.findAllUndeleted());
	}

	@Override
	public StudentDTO getStudentDTO(Integer studentId) {
		Optional<StudentEntity> op = studentRepository.findById(studentId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public StudentDTO setStudent(Integer studentId, NewStudentDTO newStudent) {
		Optional<StudentEntity> opa = studentRepository.findById(studentId);
		if (opa.isPresent() == false)
			return null;
		StudentEntity student = opa.get();
		if (newStudent.getPassword() != null)
			student.setPassword(Encryption.passwordEncode(newStudent.getPassword()));

		Optional<PersonEntity> opp = personRepository.findById(newStudent.getPersonId());
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();

		if (newStudent.getPersonId() != null)
			student.setPerson(person);
		if (newStudent.getUsername() != null)
			student.setUsername(newStudent.getUsername());
		if (newStudent.getStatus() != null)
			student.setStatus(newStudent.getStatus());

		student = studentRepository.save(student);
		return this.createDTO(student);
	}

	@Override
	public StudentDTO removeStudent(Integer studentId) {
		Optional<StudentEntity> op = studentRepository.findById(studentId);
		if (op.isPresent() == false)
			return null;
		StudentEntity student = op.get();
		student.setStatus(EStatus.DELETED);
		student = studentRepository.save(student);
		return this.createDTO(student);
	}

	public List<ParentDTO> getAllParents(StudentEntity parentId) {
		return parentService.createDTOList(parentRepository.findAllParents(parentId));
	}

	private List<GradeDTO> getAllGradesForStudent(StudentEntity source) {

		List<GradeEntity> list = gradeRepository.findAllGradesForStudent(source);
		List<GradeDTO> retVal = gradeService.createDTOList(list);

		return retVal;
	}
}
