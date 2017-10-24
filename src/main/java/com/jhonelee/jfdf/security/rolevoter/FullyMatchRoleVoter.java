package com.jhonelee.jfdf.security.rolevoter;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/**

 * @author lizhiqiang
 *
 */
public class FullyMatchRoleVoter implements AccessDecisionVoter<Object> {

	private String rolePrefix = "ROLE_";

	@Override
	public boolean supports(ConfigAttribute attribute) {
		if ((attribute.getAttribute() != null) && attribute.getAttribute().startsWith(getRolePrefix())) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return true;
	}

	@Override
	public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
		Collection<? extends GrantedAuthority> authorities = extractAuthorities(authentication);
		
		Set<String> userFullyAuthorities = new HashSet<String>();
		for (GrantedAuthority authority : authorities) {
			userFullyAuthorities.add(authority.getAuthority());
		}

		for (ConfigAttribute attribute : attributes) {
			if (this.supports(attribute)) {
				if (!userFullyAuthorities.contains(attribute.getAttribute())) {
					return ACCESS_DENIED;
				}
			}
		}

		return ACCESS_GRANTED;
	}
	
	public void setRolePrefix(String rolePrefix) {
        this.rolePrefix = rolePrefix;
    }

	public String getRolePrefix() {
		return rolePrefix;
	}

	Collection<? extends GrantedAuthority> extractAuthorities(Authentication authentication) {
		return authentication.getAuthorities();
	}

}
