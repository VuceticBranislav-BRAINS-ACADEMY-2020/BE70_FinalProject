package com.iktakademija.FinalProject.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Contains methodes for encoding and validating password using hashing
 * algorithm.
 * 
 * @see BCryptPasswordEncoder
 * @see Encryption#validatePassword
 * @see Encryption#passwordEncode
 */
public class Encryption {

	/**
	 * Encode input string as password using BCrypt hashing algorithm.<BR>
	 * Should be used for all password endcoding. A bcrypt hash string is of the
	 * form:<BR>
	 * $2b$[cost]$[22 character salt][31 character hash]<BR>
	 * 
	 * @param password Input string used as password
	 * @return BCrypt encoded string
	 * @see BCryptPasswordEncoder
	 * @see Encryption#validatePassword
	 */
	public static String passwordEncode(String password) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.encode(password);
	}

	/**
	 * Check is provided password valide. Compare password in raw format with
	 * endcoded password using BCript hashing algorithm.<BR>
	 * Should be used for all password validations.
	 * 
	 * @param password        Input string as raw password
	 * @param encodedPassword Encoded password for comparison
	 * @return True if password match. Otherwise False.
	 * @see BCryptPasswordEncoder
	 * @see Encryption#passwordEncode
	 */
	public static boolean validatePassword(String password, String encodedPassword) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder.matches(password, encodedPassword);
	}

//	// Generate password in command line
//	// Should be used only for testing
//	// Do not remove. Do not uncomment.
//	public static void main(String[] args) {
//		System.out.println(passwordEncode("admin"));
//	}

}
