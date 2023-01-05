package com.yido.clubd.security;

import java.util.Collection;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;


import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class CustomAuthenticationProvider implements AuthenticationProvider {

	private AdminUserDetailsService userDetailService;
	
	private PasswordEncoder passwordEncoder;

	public CustomAuthenticationProvider(AdminUserDetailsService adminUserDetailsService, PasswordEncoder passwordEncoder) {
		this.userDetailService = adminUserDetailsService;
		this.passwordEncoder = passwordEncoder;
	}
	
	/**
	 * 로그인 아이디, 비밀번호 체크 
	 */
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		UserDetails userDetails;
	
		try {
			userDetails = userDetailService.loadUserByUsername(username);
		} catch(Exception e) {
			throw new BadCredentialsException("잘못된 아이디입니다.");
		}

		Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

		if (passwordEncoder.matches(password, userDetails.getPassword()) ) {
        	return new UsernamePasswordAuthenticationToken(userDetails, null, authorities);        	
        } else {
        	throw new BadCredentialsException("잘못된 패스워드입니다.");
        }			
	}
	
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}

}
