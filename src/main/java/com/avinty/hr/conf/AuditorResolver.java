package com.avinty.hr.conf;

import com.avinty.hr.models.CustomUserDetails;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorResolver implements AuditorAware<Integer> {

	@Override
	public Optional<Integer> getCurrentAuditor() {
		Optional<Object> principal = Optional.ofNullable(SecurityContextHolder.getContext())
				.map(SecurityContext::getAuthentication)
				.filter(Authentication::isAuthenticated)
				.map(Authentication::getPrincipal);

		if(principal.isPresent())
			return  Optional.of(((CustomUserDetails) principal.get()).getId());
		else
			return  Optional.of(Integer.valueOf(0));
	}

}
