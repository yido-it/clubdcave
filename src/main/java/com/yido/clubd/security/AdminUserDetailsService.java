package com.yido.clubd.security;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.yido.clubd.model.DrMsMaininfo;
import com.yido.clubd.service.DrMsMaininfoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminUserDetailsService implements UserDetailsService {

	@Autowired
	private DrMsMaininfoService userService;

	@Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
	
		DrMsMaininfo gUser = userService.selectDrMsMaininfo(userId);

        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+gUser.getMsDivision()));

        return new User(gUser.getMsId(), gUser.getMsPassword(), authorities);
    }
}
