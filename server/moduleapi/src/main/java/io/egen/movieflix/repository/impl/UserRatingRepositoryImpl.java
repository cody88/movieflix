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
import io.egen.movieflix.repository.UserRatingRepository;


@Repository
public class UserRatingRepositoryImpl implements UserRatingRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
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
			return null;
		}
	}

	@Override
	public UserRating addUserRating(UserRating rating) {
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("puserId", rating.getUser().getUserId());
		List<User> user = query.getResultList();
		if(user==null || user.isEmpty())
			throw new UserNotFoundException("Bad UserId. Not found");
		rating.setUser(user.get(0));
		
		TypedQuery<Title> query2 = em.createNamedQuery("Title.findOne", Title.class);
		query2.setParameter("tId", rating.getTitleId());
		List<Title> title = query2.getResultList();
		if(title==null || title.isEmpty())
			throw new BadRequestException("TitleId "+rating.getTitleId()+" Not found");
		
		Title eTitle = title.get(0);
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
	public UserRating updateUserRating(UserRating rating) {
		TypedQuery<User> query = em.createNamedQuery("User.findById", User.class);
		query.setParameter("puserId", rating.getUser().getUserId());
		List<User> user = query.getResultList();
		if(user==null || user.isEmpty())
			throw new UserNotFoundException("Bad UserId. Not found");
		rating.setUser(user.get(0));
		
		TypedQuery<Title> query2 = em.createNamedQuery("Title.findOne", Title.class);
		query2.setParameter("tId", rating.getTitleId());
		List<Title> title = query2.getResultList();
		if(title==null || title.isEmpty())
			throw new BadRequestException("TitleId "+rating.getTitleId()+" Not found");
		Title eTitle = title.get(0);
		
		for(UserRating urating: eTitle.getUserRating()) {
			if(urating.getTitleId().equals(rating.getTitleId()) &&
					urating.getUser().getUserId().equals(rating.getUser().getUserId())) {
				urating.setStarRating(rating.getStarRating());
				urating.setUserReview(rating.getUserReview());
				break;
			}
		}
		em.merge(eTitle);
		return rating;
	}

}
