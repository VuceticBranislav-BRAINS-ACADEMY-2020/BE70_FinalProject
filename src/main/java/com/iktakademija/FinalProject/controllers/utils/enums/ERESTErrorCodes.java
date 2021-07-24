package com.iktakademija.FinalProject.controllers.utils.enums;

/**
 * REST Message Codes
 * <BR> 1 - Note.
 * <BR> 100 - Invalid procedure parameters, or null.
 * <BR> 110 - Requested item not found.
 * <BR> 111 - Found item do not satisfy requirements. 
 * <BR> 112 - Item with provided credentials already exists in data base. 
 * <BR> 150 - Role do not exist or invalide. 
 */
public enum ERESTErrorCodes {

	/**
	 * Code: 1<BR>Message: "None."
	 */
	NONE			   (1,   "None."), 
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
	ROLE_NOT_EXISTS    (150, "Role do not exist or invalide.");

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
