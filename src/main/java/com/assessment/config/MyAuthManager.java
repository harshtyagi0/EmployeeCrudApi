package com.assessment.config;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assessment.entity.MyUser;
import com.assessment.exception.UserNotExist;
import com.assessment.exception.UserNotFoundException;
import com.assessment.repository.UserRepository;

@Service
public class MyAuthManager implements AuthenticationManager {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public MyAuthManager(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String userName = authentication.getPrincipal().toString();

		MyUser user = userRepository.findByName(userName)
				.orElseThrow(() -> new UserNotExist("User doesn't exit with userName : " + userName));
		String password = authentication.getCredentials().toString();
		if (!passwordEncoder.matches(password, user.getPassword())) {
			throw new UserNotFoundException("User Not Found");
		}

		MyUserAuthentication myUserAuthentication = new MyUserAuthentication(userName, password, user.getRoles());
		myUserAuthentication.setAuthenticated(true);
		return myUserAuthentication;
	}

}
