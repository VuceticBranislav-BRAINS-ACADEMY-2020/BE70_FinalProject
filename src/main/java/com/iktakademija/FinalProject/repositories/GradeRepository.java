package com.iktakademija.FinalProject.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;

public interface GradeRepository extends PagingAndSortingRepository<GradeEntity, Integer> {

	//SELECT * FROM db_finalproject.grades as a inner join student_groups as b  on a.idstd_grp = b.id where b.idstudent = 407;
	@Query(value = "FROM GradeEntity AS a INNER JOIN FETCH a.std_grp AS b WHERE b.student = :student")
	List<GradeEntity> findAllGradesForStudent(@Param("student") StudentEntity student);

	Optional<GradeEntity> findById(@Param("id") Integer id);

	@Query(value = "FROM GradeEntity AS g WHERE g.id = :id")
	Optional<GradeEntity> findByIdIgnoreState(@Param("id") Integer id);
	
	@Query(value = "FROM GradeEntity AS g WHERE g.status <> 'DELETED'")
	List<GradeEntity> findAllUndeleted();
		
	@Query(value = "FROM GradeEntity AS a LEFT JOIN JoinTableStudentGroup AS b ON a.std_grp = b.id "
			+ "INNER JOIN JoinTableSubjectTeacher AS c ON a.sub_tch = c.id "
			+ "WHERE b.group = c.group AND a.status <> 'DELETED' AND c.teachers = :teacher")
	List<GradeEntity> findAllGradesForStudentsAndSubjects(@Param("teacher") TeacherEntity teacher);
	
	Page<GradeEntity> findAll(Pageable pageable);
}
