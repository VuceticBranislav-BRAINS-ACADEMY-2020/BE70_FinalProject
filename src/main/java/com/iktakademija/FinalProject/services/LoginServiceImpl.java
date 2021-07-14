package com.iktakademija.FinalProject.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import com.iktakademija.FinalProject.entities.UserEntity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class LoginServiceImpl implements LoginService {
	
	@Value("${spring.security.secret-key}")
	private String securityKey;
	
	@Value("${spring.security.token-duration}")
	private Integer tokenDuration;
	
	public String getJWTToken(UserEntity userEntity) {
		// Generate list of authorities
		List<GrantedAuthority> grantedAuthority = AuthorityUtils
				.commaSeparatedStringToAuthorityList(userEntity.getRole().getRole().toString());
		// Build token
		String token = Jwts.builder().setId("softtekJWT") // not important
				.setSubject(userEntity.getUsername()) // ...setup owner of token...
				// Generate claims
				.claim("authorities",
						grantedAuthority.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
				.setIssuedAt(new Date(System.currentTimeMillis())) // ...setup issued time and date...
				.setExpiration(new Date(System.currentTimeMillis() + this.tokenDuration)) // ...setup exiration date...
				.signWith(SignatureAlgorithm.HS512, this.securityKey) // ... sign token...
				.compact(); // ...generate token.
		return token;
	}
	
}
