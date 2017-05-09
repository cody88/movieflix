package io.egen.movieflix.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.AuthToken;
import io.egen.movieflix.entity.User;
import io.egen.movieflix.repository.AuthTokenRepository;


@Repository
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public User validateToken(String token, boolean checkForAdmin) {
		TypedQuery<AuthToken> query = em.createNamedQuery("AuthToken.findToken", AuthToken.class);
		query.setParameter("ah", token);
		List<AuthToken> atoken = query.getResultList();
		if(atoken != null && !atoken.isEmpty()) {
			if((!checkForAdmin) || atoken.get(0).getRole().equals("admin"))
				return atoken.get(0).getUser();
		}
		return null;
	}
	
	@Override
	@Transactional
	public AuthToken addNewToken(User user, String role) {
		AuthToken newToken = new AuthToken();
		newToken.setUser(user);
		newToken.setRole(role);
		em.persist(newToken);
		return newToken;
	}

	@Override
	@Transactional
	public int removeToken(String userId) {
		TypedQuery<AuthToken> query = em.createNamedQuery("AuthToken.findByUserId", AuthToken.class);
		query.setParameter("userId", userId);
		List<AuthToken> atoken = query.getResultList();
		em.remove(atoken.get(0));
		return 2;
	}

}
