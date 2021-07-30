package com.iktakademija.FinalProject.services;

import org.slf4j.event.Level;

import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;

public interface LoggingService {

	/**
	 * Post message to logger before leaving controler.<BR>
	 * Should be used at exit from controller method.
	 */
	void loggOutMessage(String message, Level lvl);

	/**
	 * Post message inside controler.<BR>
	 * Should be used after entering message and before leaving message.
	 */
	void loggMessage(String message, Level lvl);

	/**
	 * Logg user access to endpoint by logging its username and authentication
	 * role.<BR>
	 * Also this method logg endpoint URI for easier tracking.<BR>
	 * Method must be called inside method marked with @RequestMapping.<BR>
	 * No safety mechanism are provided so use it on your own peril.
	 * 
	 * Note: Only one role is allo@Override wed per user.
	 * 
	 * @return Return {@link ERole} for user currently triggering endpoint.
	 */
	ERole loggAndGetUser(UserEntity user, Level lvl);

	/**
	 * Get current request resource path and query parameters.
	 * 
	 * @return String that represent endpoint resource path.
	 */
	String getCurrentURL();

	/**
	 * Log enter to token granting endpoint.
	 * 
	 * @param username that request token
	 * @param lvl      represent {@link Level}
	 */
	public void getLoggAccessToken(String username, Level lvl);

	/**
	 * Post two message inside controler.<BR>
	 * Should be used after entering message and before leaving message.
	 */
	void loggTwoMessage(String message1, String message2, Level lvl);

	/**
	 * Post two message to logger before leaving controler.<BR>
	 * Should be used at exit from controller method.
	 */
	void loggTwoOutMessage(String message1, String message2, Level lvl);

	/**
	 * Post message inside controler without header.<BR>
	 */
	void loggMessageWithoutHeader(String message, Level lvl);

}
