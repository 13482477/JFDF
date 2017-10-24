package com.jhonelee.jfdf.security.userdetail.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.jhonelee.jfdf.security.userdetail.entity.CachedUserDetail;
import com.jhonelee.jfdf.user.entity.User;
import com.jhonelee.jfdf.user.repository.UserRepository;

@Component
public class CachedUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = this.userRepository.findByUsername(username);
		
		CachedUserDetail cachedUserDetail = new CachedUserDetail();
		
		cachedUserDetail.setUsername(user.getUsername());
		cachedUserDetail.setPassword(user.getPassword());
		cachedUserDetail.setAuthorities(this.extractAuthoritiesFromUser(user));

		return cachedUserDetail;
	}
	
	private List<SimpleGrantedAuthority>  extractAuthoritiesFromUser(User user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		Set<String> authorityMarks = new HashSet<String>();

		user.getRoles().forEach(role -> {
			role.getAuthorities().forEach(authority -> {
				authorityMarks.add(authority.getAuthorityCode());
			});
		});
		
		authorityMarks.forEach(authorityMark -> {
			SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authorityMark);
			authorities.add(simpleGrantedAuthority);
		});
		
		return authorities;
	}
	
	

}
