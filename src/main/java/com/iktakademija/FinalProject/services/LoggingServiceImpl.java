package com.iktakademija.FinalProject.services;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
	 * Logg user access to endpoint by logging its username and authentication role.<BR>
	 * Also this method logg endpoint URI for easier tracking.<BR>
	 * Method must be called inside method marked with @RequestMapping.<BR>
	 * No safety mechanism are provided so use it on your own peril.
	 * 
	 * Note: Only one role is allowed per user.
	 * 
	 * @return Return {@link ERole} for user currently triggering endpoint.
	 */
	@Override
	public ERole getRoleAndLogg(UserEntity user, Level lvl) {			
		// Get current request resource path and query parameters
		String path = ServletUriComponentsBuilder.fromCurrentRequest().scheme(null).host(null).build().toUriString();
				
		// Logg message
		logg(String.format(" >>> %s {%s} access to endpoint %s.", user.getUsername(), user.getRole().getRole().toString(), path), lvl);

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
	public void loggOutMessage(String message, Level lvl) {					
		// Get authentication holder
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		// Get username an role list
		String username = null;	
		String roleList = null;
		List<String> roles = null;
		if ((authentication instanceof AnonymousAuthenticationToken) == false) {
			username = authentication.getName();
			roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
			
			// Convert role list to one string
			if(roles != null)
				roleList = roles.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("-", "{", "}"));
		}
		
		// Logg message
		if (message == null) 
			logg(String.format(" <<< %s %s leave endpoint.", username, roleList), lvl);		
		logg(String.format(" <<< %s %s leave endpoint with message: %s.", username, roleList, message), lvl);
	}
	
	
	/**
	 * Post message inside controler.<BR>
	 * Should be used after entering message and before leaving message.
	 */
	public void loggMessage(String message, Level lvl) {							
		logg(String.format("  |  %s", message), lvl);
	}

	/**
	 * Log message based on provided level.
	 * 
	 * @param message to logg.
	 * @param lvl represent message {@link Level}. 
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
	
}
