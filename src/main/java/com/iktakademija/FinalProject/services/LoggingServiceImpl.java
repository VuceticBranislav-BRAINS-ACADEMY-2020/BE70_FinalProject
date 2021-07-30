package com.iktakademija.FinalProject.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.iktakademija.FinalProject.entities.UserEntity;
import com.iktakademija.FinalProject.entities.enums.ERole;

/**
 * Wrapper aroung log4j logger.<BR>
 * Used for internal purposes to easily identify log entries.
 * 
 * @author GM
 *
 */
@Service
public class LoggingServiceImpl implements LoggingService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	RoleService roleService;

	/**
	 * Log enter to token granting endpoint.
	 * 
	 * @param username that request token
	 * @param lvl      represent {@link Level}
	 */
	@Override
	public void getLoggAccessToken(String username, Level lvl) {

		logg(String.format(" >>> %s access to endpoint %s.", username, getCurrentURL()), lvl);
	}

	/**
	 * Logg user access to endpoint by logging its username and authentication
	 * role.<BR>
	 * Also this method logg endpoint URI for easier tracking.<BR>
	 * Method must be called inside method marked with @RequestMapping.<BR>
	 * No safety mechanism are provided so use it on your own peril.
	 * 
	 * Note: Only one role is allowed per user.
	 * 
	 * @return Return {@link ERole} for user currently triggering endpoint.
	 */
	@Override
	public ERole loggAndGetUser(UserEntity user, Level lvl) {
		// Get current request resource path and query parameters
		String path = getCurrentURL();

		// Logg message
		logg(String.format(" >>> %s {%s} access to endpoint %s.", user.getUsername(),
				user.getRole().getRole().toString(), path), lvl);

		// Return
		return user.getRole().getRole();
	}
//	@Override
//	public List<ERole> getRoleAndLogg(Level lvl) {			
//		// Get current request resource path and query parameters
//		String path = ServletUriComponentsBuilder.fromCurrentRequest().scheme(null).host(null).build().toUriString();
//		
//		// Get authentication holder
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		
//		// Get username
//		String username = null;	
//		String roleList = null;
//		List<String> roles = null;
//		if ((authentication instanceof AnonymousAuthenticationToken) == false) {
//			username = authentication.getName();
//			roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
//			roleList = roles.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("-", "{", "}"));
//		}
//		
//		// Logg message
//		logg(String.format(" >>> %s %s access to endpoint %s.", username, roleList,  path), lvl);
//
//		// Return 		
//		return roleService.getRoleFromStringList(roles);
//	}

	/**
	 * Post message to logger before leaving controler.<BR>
	 * Should be used at exit from controller method.
	 */
	@Override
	public void loggOutMessage(String message, Level lvl) {
		logg(String.format(" <<< Leave endpoint with message: %s.", message), lvl);
	}

	/**
	 * Post two message to logger before leaving controler.<BR>
	 * Should be used at exit from controller method.
	 */
	@Override
	public void loggTwoOutMessage(String message1, String message2, Level lvl) {
		logg(String.format("  |  %s", message1), lvl);
		logg(String.format(" <<< Leave endpoint with message: %s.", message2), lvl);
	}

	/**
	 * Post message inside controler.<BR>
	 * Should be used after entering message and before leaving message.
	 */
	@Override
	public void loggMessage(String message, Level lvl) {
		logg(String.format("  |  %s", message), lvl);
	}

	/**
	 * Post message inside controler without header.<BR>
	 */
	@Override
	public void loggMessageWithoutHeader(String message, Level lvl) {
		logg(String.format("%s", message), lvl);
	}

	/**
	 * Post two message inside controler.<BR>
	 * Should be used after entering message and before leaving message.
	 */
	@Override
	public void loggTwoMessage(String message1, String message2, Level lvl) {
		logg(String.format("  |  %s", message1), lvl);
		logg(String.format("  |  %s", message2), lvl);
	}

	/**
	 * Log message based on provided level.
	 * 
	 * @param message to logg.
	 * @param lvl     represent message {@link Level}.
	 */
	private void logg(String message, Level lvl) {
		switch (lvl) {
		case ERROR:
			logger.error(message);
			break;
		case WARN:
			logger.warn(message);
			break;
		case TRACE:
			logger.trace(message);
			break;
		case INFO:
			logger.info(message);
			break;
		case DEBUG:
			logger.debug(message);
			break;
		default:
			logger.debug(message);
		}
	}

	/**
	 * Get current request resource path and query parameters.
	 * 
	 * @return String that represent endpoint resource path.
	 */
	@Override
	public String getCurrentURL() {
		return ServletUriComponentsBuilder.fromCurrentRequest().scheme(null).host(null).build().toUriString();
	}
}
