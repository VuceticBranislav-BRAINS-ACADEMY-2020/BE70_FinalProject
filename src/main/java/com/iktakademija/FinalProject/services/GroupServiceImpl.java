package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.GroupDTO;
import com.iktakademija.FinalProject.dtos.NewGroupDTO;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.repositories.ClassRepository;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableStudentGroupRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;

@Service
public class GroupServiceImpl implements GroupService {

	@Autowired
	private ClassService classService;

	@Autowired
	private GroupRepository groupRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private TeacherService teacherService;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private JoinTableStudentGroupRepository joinTableStudentGroupRepository;

	@Override
	public GroupEntity createGroup(NewGroupDTO source) {

		if (source == null)
			return null;

		GroupEntity group = new GroupEntity();
		Optional<ClassEntity> op1 = classRepository.findById(source.getClazz());
		if (op1.isPresent() == false)
			return null;
		group.setClazz(op1.get());

		Optional<TeacherEntity> op2 = teacherRepository.findById(source.getHomeClassMaster());
		if (op2.isPresent() == false)
			return null;
		group.setHomeClassMaster(op2.get());

		group.setLetter(source.getLetter());
		group.setStatus(source.getStatus());

		group = groupRepository.save(group);
		return group;
	}

	@Override
	public GroupDTO createDTO(GroupEntity source) {
		GroupDTO retVal = new GroupDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setLetter(source.getLetter());
		retVal.setHomeClassMaster(teacherService.createDTO(source.getHomeClassMaster()));
		retVal.setClazz(classService.createDTO(source.getClazz()));
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		return retVal;
	}

	@Override
	public List<GroupDTO> getDTOList() {
		List<GroupDTO> list = new ArrayList<>();
		for (GroupEntity group : groupRepository.findAllUndeleted())
			list.add(this.createDTO(group));
		return list;
	}

	@Override
	public GroupDTO getGroupDTO(Integer groupId) {
		Optional<GroupEntity> op = groupRepository.findById(groupId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public GroupDTO setGroup(Integer groupId, NewGroupDTO newGroup) {

		// Find group by id
		Optional<GroupEntity> op = groupRepository.findById(groupId);
		if (op.isPresent() == false)
			return null;
		GroupEntity group = op.get();

		// Change group
		Optional<ClassEntity> op1 = classRepository.findById(newGroup.getClazz());
		if (op1.isPresent() == false)
			return null;
		group.setClazz(op1.get());
		if (newGroup.getClazz() != null)
			group.setClazz(op1.get());

		Optional<TeacherEntity> op2 = teacherRepository.findById(newGroup.getHomeClassMaster());
		if (op2.isPresent() == false)
			return null;
		group.setHomeClassMaster(op2.get());

		group.setLetter(newGroup.getLetter());
		group.setStatus(newGroup.getStatus());

		// Save and return DTO
		group = groupRepository.save(group);
		return this.createDTO(group);
	}

	@Override
	public GroupDTO removeGroup(Integer groupId) {
		Optional<GroupEntity> op = groupRepository.findById(groupId);
		if (op.isPresent() == false)
			return null;
		GroupEntity group = op.get();
		group.setStatus(EStatus.DELETED);
		group = groupRepository.save(group);
		return this.createDTO(group);
	}

	@Override
	public JoinTableStudentGroup addStudentToGroup(Integer studentId, Integer groupId) {

		// Find group by id
		Optional<GroupEntity> op1 = groupRepository.findById(groupId);
		if (op1.isPresent() == false)
			return null;
		GroupEntity group = op1.get();

		// Find student by id
		Optional<StudentEntity> op2 = studentRepository.findById(studentId);
		if (op2.isPresent() == false)
			return null;
		StudentEntity student = op2.get();

		JoinTableStudentGroup item = new JoinTableStudentGroup();
		item.setGroup(group);
		item.setStudent(student);

		item = joinTableStudentGroupRepository.save(item);
		return item;
	}

}
