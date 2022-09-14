package com.assessment.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.assessment.config.MyAuthManager;
import com.assessment.config.MyUserAuthentication;
import com.assessment.util.JwtUtil;

@Component
public class JwtFilter extends OncePerRequestFilter {

	private final JwtUtil jwtUtil;
	private final MyAuthManager myAuthManager;
	private final Authentication authentication;

	JwtFilter(JwtUtil jwtUtil, MyAuthManager myAuthManager, Authentication authentication) {
		this.jwtUtil = jwtUtil;
		this.myAuthManager = myAuthManager;
		this.authentication = authentication;

	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authorizationHeader = request.getHeader("Authorization");

		String token = null;
		String userName = null;

		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			token = authorizationHeader.substring(7);
			userName = jwtUtil.extractUsername(token);
		}
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

			MyUserAuthentication myUserAuthentication = (MyUserAuthentication) myAuthManager
					.authenticate(authentication);

			if (jwtUtil.validateToken(token, myUserAuthentication)) {
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						myUserAuthentication.getPrincipal(), myUserAuthentication.getCredentials(),
						myUserAuthentication.getAuthorities());
				usernamePasswordAuthenticationToken
						.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			}
		}
		
		filterChain.doFilter(request, response);

	}

}
