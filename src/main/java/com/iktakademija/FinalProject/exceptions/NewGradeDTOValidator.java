package com.iktakademija.FinalProject.exceptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.iktakademija.FinalProject.dtos.NewGradeDTO;
import com.iktakademija.FinalProject.repositories.GroupRepository;
import com.iktakademija.FinalProject.repositories.StudentRepository;
import com.iktakademija.FinalProject.repositories.SubjectRepository;
import com.iktakademija.FinalProject.repositories.TeacherRepository;
import com.iktakademija.FinalProject.services.TeacherService;

@Component
public class NewGradeDTOValidator implements Validator {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private TeacherRepository teacherRepository;

	@Autowired
	private SubjectRepository subjectRepository;
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private GroupRepository groupRepository;
		
	@Override
	public boolean supports(Class<?> clazz) {
		return NewGradeDTO.class.equals(clazz);
	}

	/**
	 * Validate NewGradeDTO.
	 * Check are requested IDs are present in base.<BR>
	 * Check is granting grade valid for given student by given teacher for given subject in given grade.
	 */
	@Override
	public void validate(Object target, Errors errors) {

		NewGradeDTO dto = (NewGradeDTO) target;
		String errorMessages = null;

		// Check is ID Student valide
		if (studentRepository.findById(dto.getIdStudent()).isPresent() == false) {
			errorMessages = "Invalid Student ID.";
		}

		// Check is ID Teacher valide
		if (teacherRepository.findById(dto.getIdTeacher()).isPresent() == false) {
			if (errorMessages == null) {
				errorMessages = "Invalid Teacher ID.";
			} else {
				errorMessages = errorMessages +"\nInvalid Teacher ID.";
			}
		}

		// Check is ID Teacher valide
		if (subjectRepository.findById(dto.getIdSubject()).isPresent() == false) {
			if (errorMessages == null) {
				errorMessages = "Invalid Subject ID.";
			} else {
				errorMessages = errorMessages +"\nInvalid Subject ID.";
			}
		}
		
		// Check is ID Group valide
		if (groupRepository.findById(dto.getIdGroup()).isPresent() == false) {
			if (errorMessages == null) {
				errorMessages = "Invalid Group ID.";
			} else {
				errorMessages = errorMessages +"\nInvalid Group ID.";
			}
		}
		
		// Check relations
		if (teacherService.doStudentListenSubjectFromTeeacherGroup(dto.getIdStudent(), dto.getIdSubject(), dto.getIdTeacher(), dto.getIdGroup()) == false) {
			if (errorMessages == null) {
				errorMessages = "Invalid ID combination. Grade can not be added.";
			} else {
				errorMessages = errorMessages +"\nInvalid ID combination. Grade can not be added.";
			}
		}
		
		if (errorMessages != null) {
			errors.reject("400", errorMessages);
		}
		
	}

}
