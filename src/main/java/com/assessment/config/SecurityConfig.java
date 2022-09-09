package com.assessment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//	@Value("${my.user.name}")
//	String userName;
//	@Value("${my.password}")-
//	String password;

	final MyAuthManager myAuthManager;

	public SecurityConfig(MyAuthManager myAuthManager) {
		this.myAuthManager = myAuthManager;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication().withUser(userName).password(new BCryptPasswordEncoder().encode(password))
//				.roles("dev");
		auth.parentAuthenticationManager(myAuthManager);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/user").permitAll().anyRequest().authenticated().and()
				.httpBasic();
	}

}
