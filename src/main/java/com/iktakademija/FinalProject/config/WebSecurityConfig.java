package com.iktakademija.FinalProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * Security config managed bean for work with web applications.<BR>
 * Security is enabled on per endpoint basis.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.secret-key}")
	private String securityKey;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // Filter origins that is allowed to make requests. In production will be enabled
			// Add filter after all filters in chain based on key with provided class of token
			.addFilterAfter(new JWTAuthorizationFilter(this.securityKey), UsernamePasswordAuthenticationFilter.class)
			// Exclude one endpoint from security and authenticate all other endpoints
			.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated();
	}

}
