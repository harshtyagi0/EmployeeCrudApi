package com.assessment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class EmpCurdApi2Application {

	public static void main(String[] args) {
		SpringApplication.run(EmpCurdApi2Application.class, args);
	}

	@Bean
	BCryptPasswordEncoder passEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManagerBean();
    }

}
