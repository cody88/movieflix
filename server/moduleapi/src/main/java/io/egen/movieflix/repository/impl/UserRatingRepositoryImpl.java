package io.egen.movieflix.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;


import io.egen.movieflix.entity.Title;
import io.egen.movieflix.entity.User;
import io.egen.movieflix.entity.UserRating;
import io.egen.movieflix.exception.BadRequestException;
import io.egen.movieflix.exception.UserNotFoundException;
import io.egen.movieflix.exception.UserRatingAlreadyExistsException;
import io.egen.movieflix.repository.UserRatingRepository;


@Repository
public class UserRatingRepositoryImpl implements UserRatingRepository {

	@PersistenceContext(type = PersistenceContextType.TRANSACTION)
	private EntityManager em;
	
	@Override
	@Transactional
	public UserRating getUserRating(String titleId, String userId) {
		TypedQuery<UserRating> query = em.createNamedQuery("UserRating.findRating", UserRating.class);
		query.setParameter("ptitleId", titleId);
		query.setParameter("puserId", userId);
		List<UserRating> userRating = query.getResultList();
		if (!userRating.isEmpty()) {
			return userRating.get(0);
		} else {
			throw new BadRequestException("UserRating for UserId "+userId+" not found");
		}
	}

	@Override
	@Transactional
	public UserRating addUserRating(UserRating rating) {
		User user = em.find(User.class, rating.getUser().getUserId());
		if(user == null)
			throw new UserNotFoundException("Bad UserId. Not found");
		rating.setUser(user);
		
		Title eTitle = em.find(Title.class, rating.getTitleId());
		if(eTitle == null)
			throw new BadRequestException("TitleId "+rating.getTitleId()+" Not found");
		
		// Check if rating already exists for this userId and titleId
		TypedQuery<UserRating> query3 = em.createNamedQuery("UserRating.findRating", UserRating.class);
		query3.setParameter("puserId", rating.getUser().getUserId());
		query3.setParameter("ptitleId", rating.getTitleId());
		List<UserRating> rat = query3.getResultList();
		if(rat!=null && !rat.isEmpty())
			throw new UserRatingAlreadyExistsException("UserRating with userId "+rating.getUser().getUserId()+
					" and titleId "+rating.getTitleId()+" already exists");
		
		eTitle.insertUserRating(rating);
		em.merge(eTitle);
		// em.merge() doesn't set the userRatingId of 'rating', so fetching that.
		for(UserRating urating: eTitle.getUserRating()) {
			if(urating.getTitleId().equals(rating.getTitleId()) &&
					urating.getUser().getUserId().equals(rating.getUser().getUserId())) {
				rating.setUserRatingId(urating.getUserRatingId());
				break;
			}
		}
		return rating;
	}

	@Override
	@Transactional
	public UserRating updateUserRating(UserRating rating) {
		User user = em.find(User.class, rating.getUser().getUserId());
		if(user == null)
			throw new UserNotFoundException("Bad UserId. Not found");
		rating.setUser(user);
		
		Title eTitle = em.find(Title.class, rating.getTitleId());
		if(eTitle == null)
			throw new BadRequestException("TitleId "+rating.getTitleId()+" Not found");
		
		for(UserRating urating: eTitle.getUserRating()) {
			if(urating.getTitleId().equals(rating.getTitleId()) &&
					urating.getUser().getUserId().equals(rating.getUser().getUserId())) {
				eTitle.removeUserRating(urating);
				if(rating.getStarRating() > 0) urating.setStarRating(rating.getStarRating());
				if(rating.getUserReview() != null) urating.setUserReview(rating.getUserReview());
				eTitle.insertUserRating(urating);
				break;
			}
		}
		em.merge(eTitle);
		return rating;
	}

}
