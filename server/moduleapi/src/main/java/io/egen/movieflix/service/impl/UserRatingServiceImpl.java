package io.egen.movieflix.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.egen.movieflix.entity.UserRating;
import io.egen.movieflix.exception.AuthorizationException;
import io.egen.movieflix.repository.AuthTokenRepository;
import io.egen.movieflix.repository.TitleRepository;
import io.egen.movieflix.repository.UserRatingRepository;
import io.egen.movieflix.service.UserRatingService;


@Service
public class UserRatingServiceImpl implements UserRatingService {

	@Autowired
	private UserRatingRepository repository;
	@Autowired
	private AuthTokenRepository authrepository;
	@Autowired
	private TitleRepository titlerepository;
	
	public UserRatingServiceImpl(UserRatingRepository repository, AuthTokenRepository arepository,
			TitleRepository titlerepository) {
		this.repository = repository;
		this.authrepository = arepository;
		this.titlerepository = titlerepository;
	}
	
	@Override
	@Transactional
	public UserRating getUserRating(String authToken, String titleId, String userId) {
		if(authrepository.validateToken(authToken, false) != null)
			return repository.getUserRating(titleId, userId);
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public UserRating addUserRating(String authToken, UserRating rating) {
		if(authrepository.validateToken(authToken, false) != null) {
			titlerepository.entEvict(rating.getTitleId());
			return repository.addUserRating(rating);
		}
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

	@Override
	@Transactional
	public UserRating updateUserRating(String authToken, UserRating rating) {
		if(authrepository.validateToken(authToken, false) != null) {
			titlerepository.entEvict(rating.getTitleId());
			return repository.updateUserRating(rating);
		}
		throw new AuthorizationException("Unauthorized access: User could not be authenticated");
	}

}
