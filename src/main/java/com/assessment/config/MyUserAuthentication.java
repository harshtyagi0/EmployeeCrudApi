package com.assessment.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.assessment.entity.MyRoles;

public class MyUserAuthentication implements Authentication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String principal; // user name
	private final String password; // credentials
	private final List<MyRoles> roles;

	public MyUserAuthentication(String principal, String password, List<MyRoles> roles) {
		this.principal = principal;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> sgaList = new ArrayList<>();
		roles.stream().forEach(myRoles -> {
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(myRoles.getRole());
			sgaList.add(sga);
		});
		return sgaList;
	}

	@Override
	public Object getCredentials() {
		return password;
	}

	@Override
	public Object getDetails() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return principal;
	}

	@Override
	public boolean isAuthenticated() {

		return false;
	}

	@Override
	public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {

	}

}
