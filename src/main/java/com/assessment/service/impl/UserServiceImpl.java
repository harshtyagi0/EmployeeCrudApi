package com.assessment.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assessment.entity.MyRoles;
import com.assessment.entity.MyUser;
import com.assessment.model.MyUserModel;
import com.assessment.repository.UserRepository;
import com.assessment.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public boolean creatingUser(MyUserModel userModel) {
		MyUser user = new MyUser();
		user.setId(userModel.getId());
		user.setUserName(userModel.getUserName());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		List<MyRoles> roles = new ArrayList<>();
		userModel.getRolesModel().stream().forEach(rM -> {
			MyRoles myRole = new MyRoles();
			myRole.setRole(rM.getRole());
			myRole.setRoleId(rM.getRoleId());
			roles.add(myRole);
		});
		user.setRoles(roles);
		try {
			userRepository.save(user);
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}
