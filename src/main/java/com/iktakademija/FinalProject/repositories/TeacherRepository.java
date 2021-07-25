package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {

	@Override
	@Query(value = "FROM TeacherEntity AS t WHERE t.id = :id AND t.status <> 'DELETED'")
	Optional<TeacherEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM TeacherEntity AS t WHERE t.status <> 'DELETED'")
	List<TeacherEntity> findAllUndeleted();
	
	@Query(value = "SELECT count(a) FROM JoinTableStudentGroup AS a INNER JOIN JoinTableSubjectTeacher AS b ON a.group=b.group "
			+ "INNER JOIN JoinTableSubjectClass AS c ON b.sub_cls=c.id INNER JOIN GroupEntity AS d ON c.clazz = d.clazz "
			+ "WHERE d.id = a.group AND a.student = :studentID and b.group = :groupID and b.teachers = :teacherID and c.subject = :subjectID")
	Integer findStudenstThatListenSubjectFromTeeacher(
			@Param("studentID") StudentEntity studentID, @Param("subjectID") SubjectEntity subjectID, 
			@Param("teacherID") TeacherEntity teacherID, @Param("groupID") GroupEntity groupID);		
	
	@Query(value = "FROM TeacherEntity AS t WHERE t.username = :username AND t.status <> 'DELETED'")
	Optional<TeacherEntity> findByUsername(@Param("username") String username);
	
}
