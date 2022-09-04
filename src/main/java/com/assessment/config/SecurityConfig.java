package com.assessment.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${my.user.name}")
	String userName;
	@Value("${my.password}")
	String password;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		String password = passEncoder().encode("tyagi");
		auth.inMemoryAuthentication().withUser(userName).password(new BCryptPasswordEncoder().encode(password))
				.roles("dev");
	}
	
	@Bean
	BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}

}
