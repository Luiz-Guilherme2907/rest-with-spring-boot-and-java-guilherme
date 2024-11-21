package br.com.guilherme.exception;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class InvalidJwtAuthenticationExcaption extends AuthenticationException {
	private static final long serialVersionUID = 1L;
	public InvalidJwtAuthenticationExcaption(String ex) {
		super(ex);
	}


}