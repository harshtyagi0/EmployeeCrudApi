package com.assessment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	final MyAuthManager myAuthManager;
	private final SecurityProblemSupport securityExceptionHandler;

	public SecurityConfig(MyAuthManager myAuthManager, SecurityProblemSupport securityExceptionHandler) {
		this.myAuthManager = myAuthManager;
		this.securityExceptionHandler = securityExceptionHandler;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.parentAuthenticationManager(myAuthManager);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/user").permitAll()
				.antMatchers(HttpMethod.POST, "/employee").hasAuthority("HR")
				.antMatchers(HttpMethod.GET, "/employee/*").hasAnyAuthority("HR","Employee")
				.antMatchers(HttpMethod.PUT, "/employee").hasAuthority("Employee")
				.antMatchers(HttpMethod.DELETE, "/employee/*").hasAuthority("HR")
				.antMatchers(HttpMethod.GET, "/employee").hasAnyAuthority("HR","Admin")
				.anyRequest().authenticated().and()
				.exceptionHandling().authenticationEntryPoint(securityExceptionHandler)
				.accessDeniedHandler(securityExceptionHandler).and().httpBasic();
	}

}
