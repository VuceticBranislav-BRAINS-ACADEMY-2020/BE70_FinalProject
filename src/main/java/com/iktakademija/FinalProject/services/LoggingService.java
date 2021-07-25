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
	 * Logg user access to endpoint by logging its username and authentication role.<BR>
	 * Also this method logg endpoint URI for easier tracking.<BR>
	 * Method must be called inside method marked with @RequestMapping.<BR>
	 * No safety mechanism are provided so use it on your own peril.
	 * 
	 * Note: Only one role is allowed per user.
	 * 
	 * @return Return {@link ERole} for user currently triggering endpoint.
	 */
	ERole getRoleAndLogg(UserEntity user, Level lvl);
	
}
