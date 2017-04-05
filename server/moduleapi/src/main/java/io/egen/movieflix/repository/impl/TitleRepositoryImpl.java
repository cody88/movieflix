package io.egen.movieflix.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.Title;
import io.egen.movieflix.repository.TitleRepository;


@Repository
public class TitleRepositoryImpl implements TitleRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	@Transactional
	public List<Title> getCatalog(int fromCount) {
		TypedQuery<Title> query = em.createNamedQuery("Title.findAll", Title.class);
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return query.getResultList();
	}

	@Override
	public List<Title> getTopIMDB(String type, int fromCount) {
		TypedQuery<Title> query = em.createNamedQuery("Title.findTopImdb", Title.class);
		query.setParameter("tp", type);
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return query.getResultList();
	}

	@Override
	public List<Title> getFiltered(String field, String value, int fromCount) {
		TypedQuery<Title> query = null;
		if(field.equals("type")) {
			query = em.createNamedQuery("Title.filterType", Title.class);
			query.setParameter("tp", value);
		}
		else if(field.equals("year")) {
			query = em.createNamedQuery("Title.filterYear", Title.class);
			query.setParameter("tp", Integer.parseInt(value));
		}
		else if(field.equals("genre")) {
			query = em.createNamedQuery("Title.filterGenre", Title.class);
			query.setParameter("tp", value);
		}
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return query.getResultList();
	}

	@Override
	public List<Title> getSorted(String sortOrder, String field, int fromCount) {
		TypedQuery<Title> query = null;
		if(field.equals("imdbrating"))
			query = em.createNamedQuery("Title.sortImdbRating", Title.class);
		else if(field.equals("year"))
			query = em.createNamedQuery("Title.sortImdbYear", Title.class);
		else if(field.equals("imdbvotes"))
			query = em.createNamedQuery("Title.sortImdbVotes", Title.class);
		query.setParameter("order", sortOrder.toUpperCase());
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return query.getResultList();
	}

}
