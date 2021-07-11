package com.iktakademija.FinalProject.config;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Accept request and check if it is valid, then setup security context holder.
 */
public class JWTAuthorizationFilter extends OncePerRequestFilter {

	private final String authorization = "Authorization";
	private final String bearer = "Bearer ";
	
	private String securityKey;
	
	// It is not Bean so need constructor
	public JWTAuthorizationFilter(String securityKey) {
		super();
		this.securityKey = securityKey;
	}

	/**
	 * Additional filter for authentication. All requests must satisfy this criteria.
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Check if jwt token exists
		if (checkJWTToken(request)) {
			// Check the validity of jwt token, return authorities/claims
			Claims claims = validateToken(request);
			if (claims.get("authorities") != null) {
				// If valid do setup spring security based on token authorities
				setUpSpringAuthentication(claims);
			} else {
				// If validation fail, clear all context so it will be refused.
				SecurityContextHolder.clearContext();
			}			
		} else {
			// If validation fail, clear all context so it will be refused.
			SecurityContextHolder.clearContext();
		}	
		// Invoke filter chain	
		filterChain.doFilter(request, response);
	}
	
	/** 
	 * Check if "Authorization" is presented in header.<BR>
	 * Header must beguin with "Bearer " string followed by token value. 
	 * @param request HTTP request
	 * @return True if header is presented and has bearer. Otherwise request has no walid token.
	 */
	private boolean checkJWTToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(this.authorization);
		if (authorizationHeader == null || !authorizationHeader.startsWith(this.bearer))
			return false;
		return true;
	}
	
	/**
	 * Return claims for user from provided token
	 * Claims contains responsibility of user.
	 * @param request HTTP request
	 * @return All Claims of user.
	 */
	private Claims validateToken(HttpServletRequest request) {
		// Get token from authorization header with removed bearer. 
		String jwtToken = request.getHeader(this.authorization).replace(this.bearer, "");
		// Get Claims from token
		return Jwts.parser()  					 // parse token...
				.setSigningKey(this.securityKey) // ...using security...
				.parseClaimsJws(jwtToken) 		 // ... from provided token ...
				.getBody(); 					 // ... get all Claims from body
	}
	
	/**
	 * Setup Spring authentication context holder to accepts access based on Claims
	 * @param claims of user
	 */
	@SuppressWarnings("unchecked")
	private void setUpSpringAuthentication(Claims claims) {
		List<String> authorities = (List<String>) claims.get("authorities");
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null,
				authorities.stream()
				.map(SimpleGrantedAuthority::new)
				.collect(Collectors.toList()));
		SecurityContextHolder.getContext().setAuthentication(auth);
	}

}
