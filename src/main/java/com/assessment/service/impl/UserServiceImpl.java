package com.assessment.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.assessment.entity.MyRoles;
import com.assessment.entity.MyUser;
import com.assessment.model.MyUserModel;
import com.assessment.repository.RoleRepository;
import com.assessment.repository.UserRepository;
import com.assessment.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final RoleRepository roleRepository;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder,
			RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.roleRepository = roleRepository;
	}

	@Override
	public boolean creatingUser(MyUserModel userModel) {
		MyUser user = new MyUser();
		user.setId(userModel.getId());
		user.setUserName(userModel.getUserName());
		user.setPassword(passwordEncoder.encode(userModel.getPassword()));
		List<MyRoles> roles = new ArrayList<>();
		userModel.getRolesModel().stream().forEach(rM -> {
			Optional<MyRoles> role = roleRepository.findByRole(rM.getRole());
			if (!role.isPresent()) {
//				Creating new Role
				MyRoles myRole = new MyRoles();
				myRole.setRole(rM.getRole());
				myRole.setRoleId(rM.getRoleId());
				roles.add(myRole);
			} else {
//				Redirecting to already present role 
				MyRoles r = role.get();
				roles.add(r);
			}

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
