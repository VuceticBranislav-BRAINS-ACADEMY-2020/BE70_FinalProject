package com.iktakademija.FinalProject.controllers.utils.enums;

/**
 * REST Message Codes
 * <BR> 1 - Note.
 * <BR> 2 - Unexpected behavoury.
 * <BR> 50 - Username not in data base.
 * <BR> 51 - Token not availible for this user.
 * <BR> 100 - Invalid procedure parameters, or null.
 * <BR> 110 - Requested item not found.
 * <BR> 111 - Found item do not satisfy requirements. 
 * <BR> 112 - Item with provided credentials already exists in data base. 
 * <BR> 150 - Role do not exist or invalide. 
 * <BR> 200 - Teacher not authorised to give grade in name of another teacher.
 * <BR> 201 - Access not allowed.
 * <BR> 202 - Grade ID can not be found in database.
 */
public enum ERESTErrorCodes {

	/**
	 * Code: 1<BR>Message: "None."
	 */
	NONE			   (1,   "None."), 
	/**
	 * Code: 2<BR>Message: "Unexpected behavoury."
	 */
	SOMETHING_WRONG    (2,   "Unexpected behavoury."), 
	/**
	 * Code: 50<BR>Message: "Username not in data base."
	 */
	TOKEN_BAD_USERNAME (50,   "Username not in data base."), 
	/**
	 * Code: 51<BR>Message: "Token not availible for this user."
	 */
	TOKEN_BAD_PASSWORD (51,   "Token not availible for this user."), 
	/**
	 * Code: 100<BR>Message: "Invalid procedure parameters, or null."
	 */
	INVALID_PARAMETERS (100, "Invalid procedure parameters, or null."),
	/**
	 *  Code: 110<BR>Message: "Requested item not found"
	 */
	NOT_FOUND          (110, "Requested item not found."),
	/**
	 * Code: 111<BR>Message: "Found item do not satisfy requirements."
	 */
	NOT_ADEQUATE       (111, "Found item do not satisfy requirements."),
	/**
	 * Code: 112<BR>Message: "Item with provided credentials already exists in data base."
	 */
	ALREADY_EXISTS     (112, "Item with provided credentials already exists in data base."),
	/**
	 * Code: 150<BR>Message: "Role do not exist or invalide."
	 */
	ROLE_NOT_EXISTS    (150, "Role do not exist or invalide."),
	/**
	 * Code: 200<BR>Message: "Teacher not authorised to give grade in name of another teacher."
	 */
	TEACHER_CANT_GRADE (200, "Teacher not authorised to give grade in name of another teacher."),
	/**
	 * Code: 201<BR>Message: "Access not allowed."
	 */
	ACCESS_NOT_ALLOWED (201, "Access not allowed."),
	/**
	 * Code: 202<BR>Message: "Grade ID can not be found in database."
	 */
	NO_SUCH_GRADE (202, "Grade ID can not be found in database.");	
	
	ERESTErrorCodes(int value, String message) {
		this.value = value;
		this.message = message;
	}

	private final int value;
	private final String message;

	public int getValue() {
		return value;
	}

	public String getMessage() {
		return message;
	}

}
