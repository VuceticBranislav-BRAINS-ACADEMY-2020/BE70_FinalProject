package com.iktakademija.FinalProject.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktakademija.FinalProject.dtos.ChangeGradeDTO;
import com.iktakademija.FinalProject.dtos.EmailObject;
import com.iktakademija.FinalProject.dtos.GradeDTO;
import com.iktakademija.FinalProject.dtos.NewGradeDTO;
import com.iktakademija.FinalProject.entities.GradeEntity;
import com.iktakademija.FinalProject.entities.GroupEntity;
import com.iktakademija.FinalProject.entities.JoinTableStudentGroup;
import com.iktakademija.FinalProject.entities.JoinTableSubjectClass;
import com.iktakademija.FinalProject.entities.JoinTableSubjectTeacher;
import com.iktakademija.FinalProject.entities.ParentEntity;
import com.iktakademija.FinalProject.entities.StudentEntity;
import com.iktakademija.FinalProject.entities.SubjectEntity;
import com.iktakademija.FinalProject.entities.TeacherEntity;
import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;
import com.iktakademija.FinalProject.entities.enums.EStage;
import com.iktakademija.FinalProject.entities.enums.EStatus;
import com.iktakademija.FinalProject.exceptions.NewGradeDTOValidator;
import com.iktakademija.FinalProject.repositories.GradeRepository;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableStudentGroupRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectClassRepository;
import com.iktakademija.FinalProject.repositories.JoinTableSubjectTeacherRepository;
import com.iktakademija.FinalProject.repositories.ParentRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.securities.Views;
import com.iktakademija.FinalProject.services.EmailService;
import com.iktakademija.FinalProject.services.GradeService;
import com.iktakademija.FinalProject.services.LoggingService;
import com.iktakademija.FinalProject.services.LoginService;
import com.iktakademija.FinalProject.utils.ERESTErrorCodes;
import com.iktakademija.FinalProject.utils.RESTError;

@RestController
@RequestMapping(path = "/api/v1/grade")
public class GradeController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private LoggingService loggingService;
	
	@Autowired
	private NewGradeDTOValidator newGradeDTOValidator;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;	
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GradeService gradeService;
	
	@Autowired
	private JoinTableStudentGroupRepository joinTableStudentGroupRepository;		
	
	@Autowired
	private JoinTableSubjectTeacherRepository joinTableSubjectTeacherRepository;
	
	@Autowired
	private JoinTableSubjectClassRepository joinTableSubjectClassRepository;
	
	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private EmailService emailService;	
	
	@Autowired
	private ParentRepository parentRepository;	
		
//	@InitBinder
//	protected void initBinder(final WebDataBinder binder) {
//		binder.addValidators(newGradeDTOValidator);
//	}
	
	/**
	 * REST endpoint that returns all grades from data base. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>GRD10</B>
	 * 
	 * @return set of {@link GradeDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin")
	public ResponseEntity<?> getAllGrades() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GradeController.getAllGrades()", Level.INFO);

		// Get list of all grades
		List<GradeDTO> retVal = gradeService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * REST endpoint that returns grade from data base by id. Method always return
	 * {@link HttpStatus.OK} if there is no internal error.<BR>
	 * 
	 * Postman code: <B>GRD11</B>
	 * 
	 * @return {@link GradeDTO} from database or empty set if nothing to return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/{id}")
	public ResponseEntity<?> getGradeById(@PathVariable(value = "id") Integer gradeId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: TeacherController.getTeacherById()", Level.INFO);

		// Check teacher id
		if (gradeId == null) {
			loggingService.loggTwoOutMessage("Invalid grade id.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.INVALID_PARAMETERS),
					HttpStatus.BAD_REQUEST);
		}

		GradeDTO dto = gradeService.getGradeDTO(gradeId);
		if (dto == null) {
			loggingService.loggTwoOutMessage("Teacher not found.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NOT_FOUND), HttpStatus.BAD_REQUEST);
		}

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<GradeDTO>(dto, HttpStatus.OK);
	}
	
	private String createErrorMessage(BindingResult result) {
		return result.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining("\n"));
	}	
	
	/**
	 * Grant grade to student. Teacher must be login with right credentials.<BR>
	 * Teacher can not add grade in another teacher name but admin can.<BR>
	 * Validation must be meet befor granting.<BR>
	 * 
	 * @param newGrade hold all data nessesery to grant grade
	 * @param result contains {@link BindingResult} data
	 * @return {@link HttpStatus.OK} if grade is granted.
	 */
	// GRD01
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})
	@RequestMapping(method = RequestMethod.POST, path = "")
	public ResponseEntity<?> addGrade(@RequestBody NewGradeDTO newGrade, BindingResult result) {						

		// Primer koriscenja validatora direktno
		newGradeDTOValidator.validate(newGrade, result);
		if (result.hasErrors())
			return new ResponseEntity<>(createErrorMessage(result), HttpStatus.BAD_REQUEST);
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);	
		loggingService.loggTwoMessage("Method: GradeController.addGrade()", newGrade.toString(), Level.INFO);	
			
		// Validation should do magic
		TeacherEntity teacher = teacherRepository.findById(newGrade.getIdTeacher()).get();
		StudentEntity student = studentRepository.findById(newGrade.getIdStudent()).get();
		SubjectEntity subject = subjectRepository.findById(newGrade.getIdSubject()).get();
		GroupEntity   group   = groupRepository.findById(newGrade.getIdGroup()).get();	
		
		// Determinate if teacher can add grade and print message
		boolean canAdd = user.getRole().getRole().equals(ERole.ROLE_TEACHER);
		if (canAdd) {
			if (user.getId().equals(newGrade.getIdTeacher()) && teacher.getStatus().equals(EStatus.ACTIVE)) {
				loggingService.loggMessage("Authorized to grant grade.", Level.INFO);
			}
			else {
				loggingService.loggTwoOutMessage("NOT Authorized to grant grade.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
				return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.TEACHER_CANT_GRADE), HttpStatus.BAD_REQUEST);	
			}			
		} else
			loggingService.loggMessage("Administration mode.", Level.INFO);
			
		// Should be valid because of validation steps
		JoinTableStudentGroup studentGroup = joinTableStudentGroupRepository.findByStudentAndGroup(student, group);		
		JoinTableSubjectClass subjectClass = joinTableSubjectClassRepository.findByClazzAndSubject(group.getClazz(), subject);
		JoinTableSubjectTeacher subjectTeacher = joinTableSubjectTeacherRepository.findByTeachersAndSubclsAndGroup(teacher, subjectClass, group);
		
		// All is clear, grant grade.
		GradeEntity grade = new GradeEntity();
		try {			
			grade.setEntered(LocalDate.now());
			grade.setStage(newGrade.getStage());
			grade.setStd_grp(studentGroup);
			grade.setSub_tch(subjectTeacher);
			grade.setType(newGrade.getType());
			grade.setValue(newGrade.getValue());
			grade.setStatus(EStatus.ACTIVE);
			grade = gradeRepository.save(grade);
			
			// Prepare and send mail
			EmailObject object = new EmailObject();
			
			List<ParentEntity> parents = parentRepository.findAllParents(student);
			if (parents.isEmpty()) {
				loggingService.loggMessage("No parents found to send eMail.", Level.ERROR);
			} else {
				
				// Get all mails from parents list
				List<String> emails = parents.stream().map(n -> n.getEmail()).collect(Collectors.toList());				
				object.setTo(emailService.emailDeture(emails.toArray(new String[0])));

				object.setSubject(String.format("Grade report. Teacher %s %s granted grade from %s to %s %s",
						teacher.getPerson().getFirstname(), teacher.getPerson().getLastname(), subject.getName(),
						student.getPerson().getFirstname(), student.getPerson().getLastname()));
				emailService.sendTemplateMessage(object, gradeService.createDTO(grade));
			}	
			loggingService.loggTwoMessage(String.format("Grade (%s) granted to student (%s) from teacher (%s) on subject (%s).", grade.getId(), student.getId(), teacher.getId(), subject.getId()), "Grade added successfully.", Level.INFO);	
		} catch (Exception e) {
			loggingService.loggTwoOutMessage("Grade adding fail.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.SOMETHING_WRONG), HttpStatus.BAD_REQUEST);
		} 
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);	
		return new ResponseEntity<GradeDTO>(gradeService.createDTO(grade), HttpStatus.OK);		
	}

	
	/**
	 * Change existing grade.
	 * 
	 * @param newGrade hold all data nessesery to change grade
	 * @return {@link HttpStatus.OK} if grade is changed.
	 */
	// GRD12
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})	
	@RequestMapping(method = RequestMethod.PUT, path = "")
	public ResponseEntity<?> changeGrade( @RequestBody ChangeGradeDTO newGrade) {			
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);	
		loggingService.loggTwoMessage("Method: GradeController.changeGrade()", newGrade.toString(), Level.INFO);	
			
		// Is grade valid. Can be ACTIVE or DELETED
		Optional<GradeEntity> op = gradeRepository.findByIdIgnoreState(newGrade.getId());
		if (op.isPresent() == false ) {
			loggingService.loggTwoOutMessage("Grade ID not found in database.", HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NO_SUCH_GRADE), HttpStatus.BAD_REQUEST);
		}
		GradeEntity grade = op.get();
		
		// Determinate if teacher can add grade and print message
		boolean isTeacher = user.getRole().getRole().equals(ERole.ROLE_TEACHER);
		if (isTeacher) {
			if (user.getId().equals(grade.getSub_tch().getTeachers().getId()) && grade.getSub_tch().getTeachers().getStatus().equals(EStatus.ACTIVE)) {
				loggingService.loggMessage("Authorized to grant grade.", Level.INFO);
			}
			else {
				loggingService.loggTwoOutMessage("NOT Authorized to grant grade.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
				return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.TEACHER_CANT_GRADE), HttpStatus.BAD_REQUEST);	
			}			
		} else
			loggingService.loggMessage("Administration mode.", Level.INFO);			
		
		// All is clear, grant grade.		
		try {
			grade.setEntered(LocalDate.now());
			if (newGrade.getStage() != null) grade.setStage(newGrade.getStage());
			if (newGrade.getType() != null) grade.setType(newGrade.getType());
			if (newGrade.getValue() != null) grade.setValue(newGrade.getValue());
			if (newGrade.getState() != null) grade.setStatus(newGrade.getState());
			grade = gradeRepository.save(grade);
			
			// Prepare and send mail
			EmailObject object = new EmailObject();
			
			List<ParentEntity> parents = parentRepository.findAllParents(grade.getStd_grp().getStudent());
			if (parents.isEmpty()) {
				loggingService.loggMessage("No parents found to send eMail.", Level.ERROR);
			} else {

			// Get all mails from parents list
			List<String> emails = parents.stream().map(n -> n.getEmail()).collect(Collectors.toList());				
			object.setTo(emailService.emailDeture(emails.toArray(new String[0])));

				object.setSubject(String.format("Grade report. Teacher %s %s changed grade from %s to %s %s",
						grade.getSub_tch().getTeachers().getPerson().getFirstname(), 
						grade.getSub_tch().getTeachers().getPerson().getLastname(), 
						grade.getSub_tch().getSub_cls().getSubject().getName(),
						grade.getStd_grp().getStudent().getPerson().getFirstname(), 
						grade.getStd_grp().getStudent().getPerson().getLastname()));
				emailService.sendTemplateMessage(object, gradeService.createDTO(grade));      
			}	
			loggingService.loggTwoMessage(String.format("Grade (%s) changed for student (%s) from teacher (%s) on subject (%s).", grade.getId(), grade.getStd_grp().getStudent().getId(), grade.getSub_tch().getTeachers().getId(), grade.getSub_tch().getSub_cls().getSubject().getId()), "Grade changed successfully.", Level.INFO);	
		} catch (Exception e) {
			loggingService.loggTwoOutMessage("Grade changing fail.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.SOMETHING_WRONG), HttpStatus.BAD_REQUEST);
		} 
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);	
		return new ResponseEntity<GradeDTO>(gradeService.createDTO(grade), HttpStatus.OK);		
	}
	
	/**
	 * Delete grade.
	 * 
	 * @return {@link HttpStatus.OK} if grade is deleted.
	 */
	// GRD13
	@Secured({"ROLE_ADMIN", "ROLE_TEACHER"})	
	@RequestMapping(method = RequestMethod.DELETE, path = "")
	public ResponseEntity<?> deleteGrade(@RequestParam Integer id) {			
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);	
		loggingService.loggMessage("Method: GradeController.deleteGrade()", Level.INFO);	
			
		// Is grade valid. Can be ACTIVE or DELETED
		Optional<GradeEntity> op = gradeRepository.findByIdIgnoreState(id);
		if (op.isPresent() == false ) {
			loggingService.loggTwoOutMessage("Grade ID not found in database.", HttpStatus.OK.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.NO_SUCH_GRADE), HttpStatus.BAD_REQUEST);
		}
		GradeEntity grade = op.get();
		
		// Determinate if teacher can add grade and print message
		boolean isTeacher = user.getRole().getRole().equals(ERole.ROLE_TEACHER);
		if (isTeacher) {
			if (user.getId().equals(grade.getSub_tch().getTeachers().getId()) && grade.getSub_tch().getTeachers().getStatus().equals(EStatus.ACTIVE)) {
				loggingService.loggMessage("Authorized to delete grade.", Level.INFO);
			}
			else {
				loggingService.loggTwoOutMessage("NOT Authorized to delete grade.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
				return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.TEACHER_CANT_GRADE), HttpStatus.BAD_REQUEST);	
			}			
		} else
			loggingService.loggMessage("Administration mode.", Level.INFO);			
		
		// All is clear, grant grade.
		try {
			grade.setEntered(LocalDate.now());
			grade.setStatus(EStatus.DELETED);
			gradeRepository.save(grade);
			
			// Prepare and send mail
			EmailObject object = new EmailObject();
			
			List<ParentEntity> parents = parentRepository.findAllParents(grade.getStd_grp().getStudent());
			if (parents.isEmpty()) {
				loggingService.loggMessage("No parents found to send eMail.", Level.ERROR);
			} else {

			// Get all mails from parents list
			List<String> emails = parents.stream().map(n -> n.getEmail()).collect(Collectors.toList());				
			object.setTo(emailService.emailDeture(emails.toArray(new String[0])));

				object.setSubject(String.format("Grade report. Teacher %s %s delete grade from %s to %s %s",
						grade.getSub_tch().getTeachers().getPerson().getFirstname(), 
						grade.getSub_tch().getTeachers().getPerson().getLastname(), 
						grade.getSub_tch().getSub_cls().getSubject().getName(),
						grade.getStd_grp().getStudent().getPerson().getFirstname(), 
						grade.getStd_grp().getStudent().getPerson().getLastname()));
				emailService.sendTemplateMessage(object, gradeService.createDTO(grade));            
			}	
			loggingService.loggTwoMessage(String.format("Grade (%s) deleted for student (%s) from teacher (%s) on subject (%s).", grade.getId(), grade.getStd_grp().getStudent().getId(), grade.getSub_tch().getTeachers().getId(), grade.getSub_tch().getSub_cls().getSubject().getId()), "Grade changed successfully.", Level.INFO);	
		} catch (Exception e) {
			loggingService.loggTwoOutMessage("Grade changing fail.", HttpStatus.BAD_REQUEST.toString(), Level.INFO);
			return new ResponseEntity<RESTError>(new RESTError(ERESTErrorCodes.SOMETHING_WRONG), HttpStatus.BAD_REQUEST);
		} 
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);	
		return new ResponseEntity<>(HttpStatus.OK);		
	}

	/**
	 * REST endpoint to filter grades.<BR>
	 * 
	 * Postman code: <B>GRD19</B>
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/filter")
	public ResponseEntity<?> getFiltered() {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GradeController.getFiltered()", Level.INFO);

		// Get list of all grades
		List<GradeDTO> retVal = gradeService.getDTOList();

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}
	

	/**
	 * REST endpoint that returns grades from data base in pages.
	 * 
	 * Postman code: <B>GRD14</B>
	 * 
	 * @return pages of {@link GradeDTO} from database or empty set if nothing to
	 *         return.
	 */
	@Secured("ROLE_ADMIN")
	@RequestMapping(method = RequestMethod.GET, path = "/admin/page/{id}")
	public ResponseEntity<?> getAllGradesPage(@PathVariable(value = "id") Integer pageId) {

		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GradeController.getAllGrades()", Level.INFO);

		// Get list of all users on page
		List<GradeDTO> retVal = gradeService.getPageDTO(pageId);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}
	
	/**
	 * Filter all grades
	 * 
	 * Postman code: <B>GRD40</B>
	 */
	@Secured("ROLE_ADMIN")
	@JsonView(value = Views.Admin.class)
	@RequestMapping(method = RequestMethod.GET, path = "/admin/search")
	public ResponseEntity<?> searchAll(@RequestParam(required = false, name = "StudentId") Integer studentId, 
			@RequestParam(required = false, name = "SubjectId") Integer subjectId, 
			@RequestParam(required = false, name = "TeacherId") Integer teacherId, 
			@RequestParam(required = false, name = "GroupId") Integer groupId,
			@RequestParam(required = false, name = "ClassId") Integer classId, 
			@RequestParam(required = false, name = "Stage") EStage stage) {
		
		// Logging and retriving user.
		UserEntity user = loginService.getUser();
		loggingService.loggAndGetUser(user, Level.INFO);
		loggingService.loggMessage("Method: GradeController.searchAll()", Level.INFO);

		// Get list of all users on page
		List<GradeEntity> list = gradeService.getFiltered(studentId, subjectId, teacherId, groupId, classId, stage);
		List<GradeDTO> retVal = gradeService.createDTOList(list);

		// Log results and make respons
		loggingService.loggOutMessage(HttpStatus.OK.toString(), Level.INFO);
		return new ResponseEntity<List<GradeDTO>>(retVal, HttpStatus.OK);
	}
	
}
