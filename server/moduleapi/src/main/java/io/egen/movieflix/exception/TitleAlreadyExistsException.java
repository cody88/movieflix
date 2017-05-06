package io.egen.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class TitleAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -4160592924846605040L;
	
	public TitleAlreadyExistsException(String message) {
		super(message);
	}
	
	public TitleAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}

}
