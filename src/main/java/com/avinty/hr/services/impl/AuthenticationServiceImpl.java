package com.avinty.hr.services.impl;
import com.avinty.hr.security.CustomAuthenticationManager;
import com.avinty.hr.models.JWTRequest;
import com.avinty.hr.models.JWTResponse;
import com.avinty.hr.security.JWTUtility;
import com.avinty.hr.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
	private final CustomAuthenticationManager authenticationManager;
	private final JWTUtility jwtUtility;
	private final CustomUserDetailsService customUserDetailsService;

	@Override
	public JWTResponse authenticate(JWTRequest jwtRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						jwtRequest.getEmail(),
						jwtRequest.getPassword()
				)
		);


		final UserDetails userDetails
				= customUserDetailsService.loadUserByUsername(jwtRequest.getEmail());

		final String token =
				jwtUtility.generateToken(userDetails);

		return new JWTResponse(token);
	}
}
