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
import io.egen.movieflix.repository.UserRepository;


@Repository
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createNamedQuery("User.findAll", User.class);
		return query.getResultList();
	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createNamedQuery("User.findByEmail", User.class);
		query.setParameter("pEmail", email);
		List<User> users = query.getResultList();
		if (!users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

	@Override
	public User findOne(String id) {
		return em.find(User.class, id);
	}

	@Override
	@Transactional
	public User create(User user) {
		em.persist(user);
		return user;
	}

	@Override
	@Transactional
	public User update(User user) {
		em.merge(user);
		TypedQuery<UserRating> query = em.createNamedQuery("UserRating.findAllRatingByUserId", UserRating.class);
		query.setParameter("puserId", user.getUserId());
		List<UserRating> ratings = query.getResultList();
		for(UserRating rat: ratings) {
			Title title = em.find(Title.class, rat.getTitleId());
			title.removeUserRating(rat);
			rat.setUser(user);
			title.insertUserRating(rat);
			em.merge(title);
		}
		return user;
	}

	@Override
	@Transactional
	public void delete(User user) {
		em.remove(user);
	}
	
}