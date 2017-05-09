package io.egen.movieflix.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import io.egen.movieflix.entity.Title;
import io.egen.movieflix.entity.User;
import io.egen.movieflix.entity.UserRating;
import io.egen.movieflix.exception.TitleAlreadyExistsException;
import io.egen.movieflix.exception.UserNotFoundException;
import io.egen.movieflix.repository.TitleRepository;


@Repository
public class TitleRepositoryImpl implements TitleRepository {

	@PersistenceContext(type = PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@Override
	public void entEvict(String titleId) {
		Title eTitle = em.find(Title.class, titleId);
		em.unwrap(Session.class).evict(eTitle);
	}
	
	@Override
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
		Query query = null;
		if(field.equals("type")) {
			query = em.createNamedQuery("Title.filterType", Title.class);
			query.setParameter("tp", value);
		}
		else if(field.equals("year")) {
			query = em.createNamedQuery("Title.filterYear", Title.class);
			query.setParameter("tp", Integer.parseInt(value));
		}
		else if(field.equals("genre")) {
			query = em.createNativeQuery("SELECT t.* FROM Title t, Title_Genre tg, Genre g "
										+ "WHERE t.TITLE_ID=tg.TITLE_ID AND tg.genre_genreId=g.genreId "
										+ "AND g.genreName=:tp");
			query.setParameter("tp", value);
		}
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return (List<Title>)query.getResultList();
	}

	@Override
	public List<Title> getSorted(String sortOrder, String field, int fromCount) {
		TypedQuery<Title> query = null;
		if(field.equals("imdbrating"))
			query = em.createNamedQuery("Title.sortImdbRating"+sortOrder.toLowerCase(), Title.class);
		else if(field.equals("year"))
			query = em.createNamedQuery("Title.sortYear"+sortOrder.toLowerCase(), Title.class);
		else if(field.equals("imdbvotes"))
			query = em.createNamedQuery("Title.sortImdbVotes"+sortOrder.toLowerCase(), Title.class);
		query.setFirstResult(fromCount);
		query.setMaxResults(25);
		return query.getResultList();
	}

	@Override
	public Title getDetails(String titleId) {
		TypedQuery<Title> query = em.createNamedQuery("Title.findOne", Title.class);
		query.setParameter("tId", titleId);
		return query.getResultList().get(0);
	}

	@Override
	@Transactional
	public int rateTitle(String userId, String titleId, int starRating) {
		TypedQuery<Title> query = em.createQuery("FROM Title t where t.titleId=:tId", Title.class);
		query.setParameter("tId", titleId);
		List<Title> titles = query.getResultList();
		if(titles == null || titles.isEmpty())
			return -1;
		Title title = titles.get(0);
		UserRating usrRate = null;
		for(UserRating rate: title.getUserRating()) {
			if (rate.getUser().getUserId().equals(userId)) {
				usrRate = rate;
				break;
			}
		}
		if(usrRate == null) {
			TypedQuery<User> query2 = em.createNamedQuery("User.findById", User.class);
			query2.setParameter("puserId", userId);
			List<User> usr = query2.getResultList();
			if(usr == null || usr.isEmpty())
				throw new UserNotFoundException("Bad userId. User not found");
			
			UserRating newRating = new UserRating();
			newRating.setUser(usr.get(0));
			newRating.setTitleId(titleId);
			newRating.setStarRating(starRating);
			//em.persist(newRating);
			title.insertUserRating(newRating);
			em.merge(title);
			return 1;
		}
		else {
			usrRate.setStarRating(starRating);
			em.merge(usrRate);
			return 1;
		}
	}

	@Override
	@Transactional
	public int reviewTitle(String userId, String titleId, String userReview) {
		TypedQuery<Title> query = em.createQuery("FROM Title t where t.titleId=:tId", Title.class);
		query.setParameter("tId", titleId);
		List<Title> titles = query.getResultList();
		if(titles == null || titles.isEmpty())
			return -1;
		Title title = titles.get(0);
		UserRating usrRate = null;
		for(UserRating rate: title.getUserRating()) {
			if (rate.getUser().getUserId().equals(userId)) {
				usrRate = rate;
				break;
			}
		}
		if(usrRate == null) {
			TypedQuery<User> query2 = em.createNamedQuery("User.findById", User.class);
			query2.setParameter("puserId", userId);
			List<User> usr = query2.getResultList();
			if(usr == null || usr.isEmpty())
				throw new UserNotFoundException("Bad userId. User not found");
			
			UserRating newRating = new UserRating();
			newRating.setUser(usr.get(0));
			newRating.setTitleId(titleId);
			newRating.setUserReview(userReview);
			//em.persist(newRating);
			title.insertUserRating(newRating);
			em.merge(title);
			return 1;
		}
		else {
			usrRate.setUserReview(userReview);
			em.merge(usrRate);
			return 1;
		}
	}

	@Override
	@Transactional
	public int addNewTitle(Title title) {
		TypedQuery<Title> query = em.createQuery("FROM Title t where t.titleId=:tId", Title.class);
		query.setParameter("tId", title.getTitleId());
		List<Title> titles = query.getResultList();
		if(titles == null || !titles.isEmpty()) { System.out.println("TRepo: titles is not null,"+titles.size()+","+title.getTitleId());
			throw new TitleAlreadyExistsException("TitleId already exists");}
		em.persist(title);
		return 1;
	}

	@Override
	@Transactional
	public int deleteTitle(String titleId) {
		TypedQuery<Title> query = em.createQuery("FROM Title t where t.titleId=:tId", Title.class);
		query.setParameter("tId", titleId);
		List<Title> titles = query.getResultList();
		if(titles == null || titles.isEmpty())
			return -1;
		Title title = titles.get(0);
		em.remove(title);
		return 1;
	}

}
