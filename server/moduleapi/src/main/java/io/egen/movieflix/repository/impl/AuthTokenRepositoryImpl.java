package io.egen.movieflix.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.AuthToken;
import io.egen.movieflix.repository.AuthTokenRepository;


@Repository
public class AuthTokenRepositoryImpl implements AuthTokenRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public boolean validateToken(String token) {
		TypedQuery<AuthToken> query = em.createNamedQuery("AuthToken.findToken", AuthToken.class);
		query.setParameter("ah", token);
		List<AuthToken> atoken = query.getResultList();
		if(atoken != null && !atoken.isEmpty())
			return true;
		return false;
	}

}
