package io.egen.movieflix.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.egen.movieflix.entity.UserRating;
import io.egen.movieflix.service.UserRatingService;


@RestController
@RequestMapping(path = "userrating")
public class UserRatingController {

	@Autowired
	private UserRatingService service;

	public UserRatingController(UserRatingService service) {
		this.service = service;
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{titleId}/{userId}")
	public UserRating getUserRating(@RequestHeader(value="ah") String authToken,
			@PathVariable("titleId") String titleId,
			@PathVariable("userId") String userId) {
		return service.getUserRating(authToken, titleId, userId);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/add", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRating create(@RequestHeader(value="ah") String authToken, @RequestBody UserRating userRating) {
		return service.addUserRating(authToken, userRating);
	}
	
	@RequestMapping(method = RequestMethod.POST, path = "/update", consumes=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public UserRating update(@RequestHeader(value="ah") String authToken, @RequestBody UserRating userRating) {
		return service.updateUserRating(authToken, userRating);
	}
}
