package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.PersonEntity;
import com.iktakademija.FinalProject.entities.RoleEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.dtos.NewGradeDTO;
import com.iktakademija.FinalProject.entities.dtos.NewTeacherDTO;
import com.iktakademija.FinalProject.entities.dtos.TeacherDTO;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectTeacherRepository;
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
	private JoinTableSubjectTeacherRepository joinTableSubjectTeacherRepository;
	
	@Override
	public TeacherEntity createTeacher(NewTeacherDTO source) {		
		Optional<RoleEntity> opr = roleRepository.findByRole(ERole.ROLE_TEACHER);
		if (opr.isPresent() == false) return null;
		RoleEntity role = opr.get();
		
		Optional<PersonEntity> opp = personRepository.findById(source.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();
		
		TeacherEntity teacher = new TeacherEntity(source.getUsername(), Encryption.passwordEncode(source.getPassword()), person, role);		
		return teacherRepository.save(teacher);	
	}	
	
	@Override
	public TeacherDTO createDTO(TeacherEntity source) {
		TeacherDTO retVal = new TeacherDTO();
		if (source == null) return retVal;
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
		if (op.isPresent() == false) return null;
		return this.createDTO(op.get());
	}

	@Override
	public TeacherDTO setTeacher(Integer teacherId, NewTeacherDTO newTeacher) {
		Optional<TeacherEntity> opa = teacherRepository.findById(teacherId);
		if (opa.isPresent() == false) return null;
		TeacherEntity teacher = opa.get();		
		if (newTeacher.getPassword() != null) teacher.setPassword(Encryption.passwordEncode(newTeacher.getPassword()));
		
		Optional<PersonEntity> opp = personRepository.findById(newTeacher.getPersonId());
		if (opp.isPresent() == false) return null;
		PersonEntity person = opp.get();	
		
		if (newTeacher.getPersonId() != null) teacher.setPerson(person); // TODO Fix this
		if (newTeacher.getUsername() != null) teacher.setUsername(newTeacher.getUsername());

		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);
	}

	@Override
	public TeacherDTO removeTeacher(Integer teacherId) {
		Optional<TeacherEntity> op = teacherRepository.findById(teacherId);
		if (op.isPresent() == false) return null;
		TeacherEntity teacher = op.get();		
		teacher.setStatus(EStatus.DELETED);			
		teacher = teacherRepository.save(teacher);
		return this.createDTO(teacher);	
	}
	
	@PersistenceContext
	private EntityManager entityManager;	
	
	@Override
	public boolean doStudentListenSubjectFromTeeacherGroup(Integer student, Integer subject, Integer teacher, Integer group) {
		
		// Find all groups that teacher teach
//		TypedQuery<GroupEntity> query1 = entityManager.createQuery(
//		  "SELECT * FROM subject_teachers as e WHERE e.idgroup = 612;" , GroupEntity.class);
//		List<ClassEntity> aaaaa = query.getResultList();
		
		Optional<StudentEntity> op1 = studentRepository.findById(student); 
		if (op1.isPresent() == false ) return false;
		StudentEntity student1 = op1.get();	
				
		Optional<TeacherEntity> op2 = teacherRepository.findById(teacher);
		if (op2.isPresent() == false ) return false;
		TeacherEntity teacher1 = op2.get();		

		Optional<GroupEntity> op3 = groupRepository.findById(group); 
		if (op3.isPresent() == false ) return false;
		GroupEntity group1 = op3.get();	
		
		Optional<SubjectEntity> op4 = subjectRepository.findById(subject); 
		if (op4.isPresent() == false ) return false;
		SubjectEntity subject1 = op4.get();	
		
		if (teacherRepository.findStudenstThatListenSubjectFromTeeacher(student1, subject1, teacher1, group1) > 0) {
			return true;
		}
		
		return false;
		
//		// Get groups that teacher teachs
//		List<JoinTableSubjectTeacher> list = joinTableSubjectTeacherRepository.findAllByGroup(group); 
//		List<GroupEntity> groups = list.stream().map(g -> g.getGroup()).collect(Collectors.toList());
		
		// Get student

		
		
		
		// Get students in 
//		List<JoinTableSubjectTeacher> list = joinTableSubjectTeacherRepository.findByTeachers(teacher);
//		List<GroupEntity> groups = list.stream().map(group -> group.getGroup()).collect(Collectors.toList());
//		return groups;
		
	}
	
//	@PersistenceContext
//	private EntityManager entityManager;	
//		TypedQuery<ClassEntity> query = entityManager.createQuery(
//				  "SELECT e FROM ClassEntity e WHERE e.id IN ('10', '11')" , ClassEntity.class);
//				List<ClassEntity> aaaaa = query.getResultList();
//		
//		TypedQuery<JoinTableSubjectClass> query1 = entityManager.createQuery(
//				  "SELECT e FROM JoinTableSubjectClass e WHERE e.clazz IN (?1)" , JoinTableSubjectClass.class);
//				List<JoinTableSubjectClass> employees = query1.setParameter(1, aaaaa).getResultList();
//
//	String sql = "SELECT a FROM AddressEntity AS a LEFT JOIN FETCH a.users AS u WHERE u.name = :name";
//	// mesto ON postoji FETCH koji je lista veza
		
	
//	@Override
//	public List<GroupEntity> findAllGroups(TeacherEntity teacher) {
//		List<JoinTableSubjectTeacher> list = joinTableSubjectTeacherRepository.findByTeachers(teacher);
//		List<GroupEntity> groups = list.stream().map(group -> group.getGroup()).collect(Collectors.toList());
//		return groups;
//	}
//	
//	public List<StudentEntity> findAllStudents(TeacherEntity teacher) {
//		
//		
//		return null;
//	}

}
