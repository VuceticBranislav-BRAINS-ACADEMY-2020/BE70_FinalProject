package com.iktakademija.FinalProject.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

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

	@Value("#{'${cors.addresses}'.split(',')}") 
	private List<String> corsAddresses;
	
	@Value("#{'${cors.methods}'.split(',')}") 
	private List<String> corsMethods;
	
	@Value("#{'${cors.headers}'.split(',')}") 
	private List<String> corsHeaders;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Add filter after all filters in chain based on key with provided class of token
		http.cors().and()//.csrf().disable()
			.csrf()
			.ignoringAntMatchers("/login")
			.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
			.addFilterAfter(new JWTAuthorizationFilter(this.securityKey), UsernamePasswordAuthenticationFilter.class)
			// Exclude one endpoint from security and authenticate all other endpoints
			.authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest().authenticated();
	}
	 
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		// Create configuration
		CorsConfiguration configuration = new CorsConfiguration();
		// Setup valid origin addresses
		configuration.setAllowedOriginPatterns(corsAddresses);
		// Setup valid access methods
		configuration.setAllowedMethods(corsMethods);
		configuration.setAllowedHeaders(corsHeaders);
		configuration.setAllowCredentials(true);
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		// Apply configuration to all endpoints
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

}
