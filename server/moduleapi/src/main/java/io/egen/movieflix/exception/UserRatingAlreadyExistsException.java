package io.egen.movieflix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class UserRatingAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 8502560516275989657L;

	public UserRatingAlreadyExistsException(String message) {
		super(message);
	}
	
	public UserRatingAlreadyExistsException(String message, Throwable cause) {
		super(message, cause);
	}
}
