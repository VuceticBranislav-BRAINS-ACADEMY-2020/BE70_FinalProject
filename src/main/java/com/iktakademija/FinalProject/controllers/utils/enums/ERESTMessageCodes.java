package com.iktakademija.FinalProject.controllers.utils.enums;

/**
 * REST Message Codes
 * <BR> 1 - Note. {@link #NONE}
 * <BR> 100 - Database updated successfully. {@link #DATABASE_UPDATED}
 * <BR> 101 - Nothing to change. {@link #NOTHING_CHANGED}
 * <BR> 110 - List is empty. {@link #LIST_EMPTY}
 */
public enum ERESTMessageCodes {

	// Enumerations. Keep unique numbers.
	NONE		     (1,   "None."), 
	DATABASE_UPDATED (100, "Database updated successfully."),
	NOTHING_CHANGED  (101, "Nothing to change."),	
	LIST_EMPTY       (110, "List is empty.");

	ERESTMessageCodes(int value, String message) {
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
