package com.quiroprax.api.infra.configurations;

import com.quiroprax.api.infra.filters.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

	@Autowired
	private SecurityFilter securityFilter;
	
	private static final String[] SWAGGER_GET_ENDPOINTS = new String[] {
			"/swagger-ui.html",
			"/swagger-ui/**",
			"/v3/api-docs",
			"/v3/api-docs/**"
	};
		
    @Bean
    SecurityFilterChain getSecurityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
	                        .requestMatchers("/atendentes/login")
	                        	.permitAll()
                        	//.requestMatchers(HttpMethod.GET, "/info")
                        	//	.permitAll()
                        	.requestMatchers(HttpMethod.GET, SWAGGER_GET_ENDPOINTS)
                        		.permitAll()
	                        .anyRequest()
	                        	.authenticated()
                        )
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    AuthenticationManager getAuthenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
}