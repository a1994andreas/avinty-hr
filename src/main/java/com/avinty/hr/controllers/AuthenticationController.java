package com.avinty.hr.controllers;

import com.avinty.hr.models.JWTRequest;
import com.avinty.hr.models.JWTResponse;
import com.avinty.hr.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {
	private final AuthenticationService authenticationService;

	@PostMapping(value = "/authenticate", consumes = { APPLICATION_JSON_VALUE }, produces = { APPLICATION_JSON_VALUE })
	public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest){
		return authenticationService.authenticate(jwtRequest);
	}

	@ExceptionHandler
	@ResponseStatus(value = HttpStatus.FORBIDDEN)
	public String handleBadRequestError(BadCredentialsException ex){
		return "Invalid Credentials";
	}
}
