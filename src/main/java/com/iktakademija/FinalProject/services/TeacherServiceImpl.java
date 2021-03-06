package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.GradeRepository;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.PersonRepository;
import com.iktakademija.FinalProject.repositories.RoleRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.utils.Encryption;

@Service
public class TeacherServiceImpl implements TeacherService {

	@Autowired
	private PersonService personService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private GradeService gradeService;

	@Override
	public TeacherEntity createTeacher(NewTeacherDTO source) {
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_TEACHER);
		if (opr.isPresent() == false)
			return null;
		RoleEntity role = opr.get();

		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false)
			return null;
		PersonEntity person = opp.get();

		TeacherEntity teacher = new TeacherEntity(source.getUsername(), Encryption.passwordEncode(source.getPassword()),
				person, role);
		teacher = teacherRepository.save(teacher);
		return teacher;
	}

	@Override
	public TeacherDTO createDTO(TeacherEntity source) {
		TeacherDTO retVal = new TeacherDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setUsername(source.getUsername());
		retVal.setPerson(personService.createDTO(source.getPerson()));
		retVal.setRole(roleService.createDTO(source.getRole()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

	@Override
	public List<TeacherDTO> getDTOList() {
		List<TeacherDTO> list = new ArrayList<>();
		for (TeacherEntity teacher : teacherRepository.findAllUndeleted())
			list.add(this.createDTO(teacher));
		return list;
	}

	@Override
	public TeacherDTO getTeacherDTO(Integer teacherId) {
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public TeacherDTO setTeacher(Integer teacherId, NewTeacherDTO newTeacher) {

		// Find teacher by id
		Optional<TeacherEntity> op1 = teacherRepository.findById(teacherId);
		if (op1.isPresent() == false)
			return null;
		TeacherEntity teacher = op1.get();

		// Find person
		Optional<PersonEntity> op2 = personRepository.findById(newTeacher.getPersonId());
		if (op2.isPresent() == false)
			return null;
		PersonEntity person = op2.get();

		// Change teacher
		if (newTeacher.getPersonId() != null)
			teacher.setPerson(person);
		if (newTeacher.getUsername() != null)
			teacher.setUsername(newTeacher.getUsername());
		if (newTeacher.getPassword() != null)
			teacher.setPassword(Encryption.passwordEncode(newTeacher.getPassword()));
		if (newTeacher.getStatus() != null)
			teacher.setStatus(newTeacher.getStatus());

		// Save and return DTO
		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);
	}

	@Override
	public TeacherDTO removeTeacher(Integer teacherId) {
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false)
			return null;
		TeacherEntity teacher = op.get();
		teacher.setStatus(EStatus.DELETED);
		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);
	}

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public boolean doStudentListenSubjectFromTeeacherGroup(Integer student, Integer subject, Integer teacher,
			Integer group) {

		Optional<StudentEntity> op1 = studentRepository.findById(student);
		if (op1.isPresent() == false)
			return false;
		StudentEntity student1 = op1.get();

		Optional<TeacherEntity> op2 = teacherRepository.findById(teacher);
		if (op2.isPresent() == false)
			return false;
		TeacherEntity teacher1 = op2.get();

		Optional<GroupEntity> op3 = groupRepository.findById(group);
		if (op3.isPresent() == false)
			return false;
		GroupEntity group1 = op3.get();

		Optional<SubjectEntity> op4 = subjectRepository.findById(subject);
		if (op4.isPresent() == false)
			return false;
		SubjectEntity subject1 = op4.get();

		if (teacherRepository.findStudenstThatListenSubjectFromTeeacher(student1, subject1, teacher1, group1) > 0) {
			return true;
		}

		return false;
	}

	@Override
	public List<GradeDTO> findAllGradesForStudentsAndSubjects(TeacherEntity teacher) {

		List<GradeEntity> list = gradeRepository.findAllGradesForStudentsAndSubjects(teacher);
		List<GradeDTO> retVal = gradeService.createDTOList(list);
		return retVal;

	}

	@Override
	public List<SubjectEntity> getAllSubjectsByTeacher(Integer teacherId) {

		// Check teacher id
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false)
			return null;
		TeacherEntity teacher = op.get();

		return teacher.getSubject().stream().map(i -> i.getSub_cls().getSubject()).collect(Collectors.toList());
	}

}
