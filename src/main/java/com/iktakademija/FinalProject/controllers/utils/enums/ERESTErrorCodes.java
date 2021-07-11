package com.iktakademija.FinalProject.controllers.utils.enums;

/**
 * REST Message Codes
 * <BR> 1 - Note. {@link #NONE}
 * <BR> 100 - Invalid procedure parameters, or null. {@link #INVALID_PARAMETERS}
 * <BR> 110 - Requested item not found. {@link #NOT_FOUND}
 * <BR> 111 - Found item do not satisfy requirements. {@link #NOT_ADEQUATE}
 * <BR> 112 - Item with provided credentials already exists in data base. {@link #ALREADY_EXISTS}
 */
public enum ERESTErrorCodes {

	// Enumerations. Keep unique numbers.
	NONE			   (1,   "None."), 
	INVALID_PARAMETERS (100, "Invalid procedure parameters, or null."),
	NOT_FOUND          (110, "Requested item not found"),
	NOT_ADEQUATE       (111, "Found item do not satisfy requirements."),
	ALREADY_EXISTS     (112, "Item with provided credentials already exists in data base.");

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
