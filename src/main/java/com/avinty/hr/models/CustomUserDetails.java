package com.avinty.hr.models;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
/*
* This class is used to store additional information for the logged in user.
* The id will be used at AuditorResolver in order to populate the created_by updated_by
* fields on the database.
*
* */

@Setter
@Getter
public class CustomUserDetails extends org.springframework.security.core.userdetails.User implements UserDetails {
	private Integer id;

	public CustomUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	public CustomUserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
}
