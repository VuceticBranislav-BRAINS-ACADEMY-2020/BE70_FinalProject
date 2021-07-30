package com.iktakademija.FinalProject.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.entities.ClassEntity;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.repositories.ClassRepository;
import com.iktakademija.FinalProject.repositories.GradeRepository;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private ClassRepository classRepository;

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private SubjectRepository subjectRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private GroupRepository groupRepository;

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<GradeDTO> getDTOList() {
		List<GradeDTO> list = new ArrayList<>();
		for (GradeEntity grade : gradeRepository.findAllUndeleted())
			list.add(this.createDTO(grade));
		return list;
	}

	@Override
	public GradeDTO createDTO(GradeEntity source) {
		GradeDTO retVal = new GradeDTO();
		if (source == null)
			return retVal;
		retVal.setId(source.getId());
		retVal.setType(source.getType());
		retVal.setValue(source.getValue());
		retVal.setEntered(source.getEntered());
		retVal.setStage(source.getStage());
		retVal.setVersion(source.getVersion());
		retVal.setStatus(source.getStatus());
		retVal.setGroupName(
				source.getStd_grp().getGroup().getClazz().getName() + "-" + source.getStd_grp().getGroup().getLetter());
		retVal.setTeacherName(source.getSub_tch().getTeachers().getPerson().getFirstname() + " "
				+ source.getSub_tch().getTeachers().getPerson().getLastname());
		retVal.setSubjectName(source.getSub_tch().getSub_cls().getSubject().getName());
		retVal.setStudentName(source.getStd_grp().getStudent().getPerson().getFirstname() + " "
				+ source.getStd_grp().getStudent().getPerson().getLastname());
		return retVal;
	}

	@Override
	public GradeDTO getGradeDTO(Integer gradeId) {
		Optional<GradeEntity> op = gradeRepository.findById(gradeId);
		if (op.isPresent() == false)
			return null;
		return this.createDTO(op.get());
	}

	@Override
	public List<GradeDTO> createDTOList(List<GradeEntity> source) {
		List<GradeDTO> list = new ArrayList<>();
		for (GradeEntity grade : source)
			list.add(this.createDTO(grade));
		return list;
	}

	@Override
	public List<GradeDTO> getPageDTO(Integer page) {

		Pageable pageRequest = PageRequest.of(page.intValue(), 5);
		Slice<GradeEntity> retVal = gradeRepository.findAll(pageRequest);
		List<GradeDTO> list = this.createDTOList(retVal.getContent());
		return list;

	}

	public List<GradeEntity> getFiltered(Integer studentId, Integer subjectId, Integer teacherId, Integer groupId,
			Integer classId, EStage stage) {

		StudentEntity student = null;
		SubjectEntity subject = null;
		TeacherEntity teacher = null;
		GroupEntity group = null;
		ClassEntity clazz = null;

		if (studentId != null)
			student = studentRepository.findById(studentId).orElse(null);
		if (subjectId != null)
			subject = subjectRepository.findById(subjectId).orElse(null);
		if (teacherId != null)
			teacher = teacherRepository.findById(teacherId).orElse(null);
		if (groupId != null)
			group = groupRepository.findById(groupId).orElse(null);
		if (classId != null)
			clazz = classRepository.findById(classId).orElse(null);

		return getFilteredByObjects(student, subject, teacher, group, clazz, stage);
	}

	@SuppressWarnings("unchecked")
	private List<GradeEntity> getFilteredByObjects(StudentEntity student, SubjectEntity subject, TeacherEntity teacher,
			GroupEntity group, ClassEntity clazz, EStage stage) {

		// TODO Need havy optimisation
		String sql = "SELECT DISTINCT z FROM GradeEntity AS z "
				+ "INNER JOIN JoinTableStudentGroup AS a ON z.std_grp = a.id "
				+ "INNER JOIN JoinTableSubjectTeacher AS b ON z.sub_tch = b.id "
				+ "INNER JOIN JoinTableSubjectClass AS c ON b.sub_cls = c.id "
				+ "INNER JOIN GroupEntity as d ON c.clazz = d.clazz WHERE z.stage = :stage ";

		if (student != null)
			sql = sql + "AND a.student = :studentId ";
		if (subject != null)
			sql = sql + "AND c.subject = :subjectId ";
		if (teacher != null)
			sql = sql + "AND b.teachers = :teacherId ";
		if (group != null)
			sql = sql + "AND b.group = :groupId ";
		if (clazz != null)
			sql = sql + "AND d.clazz = :classId ";

		Query query = entityManager.createQuery(sql);
		if (stage == null)
			query.setParameter("stage", EStage.FIRST);
		else
			query.setParameter("stage", stage);

		if (student != null)
			query.setParameter("studentId", student);
		if (subject != null)
			query.setParameter("subjectId", subject);
		if (teacher != null)
			query.setParameter("teacherId", teacher);
		if (group != null)
			query.setParameter("groupId", group);
		if (clazz != null)
			query.setParameter("classId", clazz);

		List<GradeEntity> list = new ArrayList<GradeEntity>();
		list = query.getResultList();
		return list;
	}
}
