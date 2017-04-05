package io.egen.movieflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import io.egen.movieflix.service.AuthTokenService;


@RestController
public class AuthTokenController {

	@Autowired
	private AuthTokenService service;
	
	public AuthTokenController(AuthTokenService service) {
		this.service = service;
	}
}
