package com.avinty.hr.services;

import com.avinty.hr.models.JWTRequest;
import com.avinty.hr.models.JWTResponse;

public interface AuthenticationService {
	JWTResponse authenticate(JWTRequest jwtRequest);
}
