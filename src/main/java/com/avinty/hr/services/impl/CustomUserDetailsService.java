package com.avinty.hr.services.impl;

import com.avinty.hr.models.CustomUserDetails;
import com.avinty.hr.models.Employee;
import com.avinty.hr.repositories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final EmployeeRepository employeeRepository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee employee = employeeRepository.findByEmail(email);
		if (employee == null) {
			throw new UsernameNotFoundException(email);
		}

		List<GrantedAuthority> authorities = new LinkedList<>();
		authorities.add(new SimpleGrantedAuthority("ROLE_"+employee.getRole()));
		CustomUserDetails user = new CustomUserDetails(employee.getEmail(), employee.getPassword(), authorities);
		user.setId(employee.getId());
//
//		CustomUserDetails user = (CustomUserDetails) User.withUsername(employee.getEmail())
//				.password(employee.getPassword())
//				.disabled(employee.getIs_active())
//				.roles(employee.getRole()).build();
//		user.setId(employee.getId());
		return user;
	}
}
