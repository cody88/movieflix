package io.egen.movieflix.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({
	@NamedQuery(name="Title.findAll", query="FROM Title t"),
	@NamedQuery(name="Title.findOne", query="FROM Title t WHERE t.titleId=:tId"),
	@NamedQuery(name="Title.findTopImdb", query="FROM Title t WHERE t.type=:tp ORDER BY t.imdbRating DESC"),
	@NamedQuery(name="Title.filterType", query="FROM Title t WHERE t.type=:tp"),
	@NamedQuery(name="Title.filterYear", query="FROM Title t WHERE t.year=:tp"),
	/*@NamedQuery(name="Title.filterGenre", query="SELECT t FROM Title t, Genre g WHERE"
											+ "t.genre.genreId=g.genreId AND g.genreName=:tp"),*/
	@NamedQuery(name="Title.sortImdbRatingasc", query="FROM Title t ORDER BY t.imdbRating"),
	@NamedQuery(name="Title.sortImdbRatingdesc", query="FROM Title t ORDER BY t.imdbRating DESC"),
	@NamedQuery(name="Title.sortYearasc", query="FROM Title t ORDER BY t.year"),
	@NamedQuery(name="Title.sortYeardesc", query="FROM Title t ORDER BY t.year DESC"),
	@NamedQuery(name="Title.sortImdbVotesasc", query="FROM Title t ORDER BY t.imdbVotes"),
	@NamedQuery(name="Title.sortImdbVotesdesc", query="FROM Title t ORDER BY t.imdbVotes DESC")
})
public class Title implements Serializable {

	private static final long serialVersionUID = 1689732318309064572L;

	@Id
	@GenericGenerator(name="movieflixUUID", strategy="uuid2")
	@GeneratedValue(generator="movieflixUUID")
	@Column(name="TITLE_ID")
	private String titleId;
	
	private String titleName;
	private int year;
	private String rated;
	private Date releaseDate;
	private int runtimeInMinutes;
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	private List<String> genre;
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	private List<String> directors;
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	private List<String> writers;
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	private List<String> actors;
	private String plot;
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	private List<String> languages;
	private String country;
	
	private String primaryAward;
	private int primaryAwardCount;
	private boolean primaryAwardWon;
	private int otherWins;
	private int otherNominations;
	
	private String posterLink;
	private int metascore;
	@Column(nullable=false)
	private String type;
	
	private String imdbId;
	private double imdbRating;
	private int imdbVotes;
	
	//@ElementCollection
    //@CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	//@JoinColumn(name="UserRating", referencedColumnName="TITLE_ID")
	@OneToMany(targetEntity = UserRating.class, cascade = CascadeType.ALL)
	private List<UserRating> userRating;

	
	public String getTitleId() {
		return titleId;
	}

	public void setTitleId(String titleId) {
		this.titleId = titleId;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getRated() {
		return rated;
	}

	public void setRated(String rated) {
		this.rated = rated;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public int getRuntimeInMinutes() {
		return runtimeInMinutes;
	}

	public void setRuntimeInMinutes(int runtimeInMinutes) {
		this.runtimeInMinutes = runtimeInMinutes;
	}

	public List<String> getDirectors() {
		return directors;
	}

	public void setDirectors(List<String> directors) {
		this.directors = new ArrayList<String>();
		this.directors.addAll(directors);
	}

	public List<String> getWriters() {
		return writers;
	}

	public void setWriters(List<String> writers) {
		this.writers = new ArrayList<String>();
		this.writers.addAll(writers);
	}

	public List<String> getActors() {
		return actors;
	}

	public void setActors(List<String> actors) {
		this.actors = new ArrayList<String>();
		this.actors.addAll(actors);
	}

	public String getPlot() {
		return plot;
	}

	public void setPlot(String plot) {
		this.plot = plot;
	}

	public List<String> getGenre() {
		return genre;
	}

	public void setGenre(List<String> genre) {
		this.genre = new ArrayList<String>();
		this.genre.addAll(genre);
	}

	public List<String> getLanguages() {
		return languages;
	}

	public void setLanguages(List<String> languages) {
		this.languages = new ArrayList<String>();
		this.languages.addAll(languages);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPrimaryAward() {
		return primaryAward;
	}

	public void setPrimaryAward(String primaryAward) {
		this.primaryAward = primaryAward;
	}

	public int getPrimaryAwardCount() {
		return primaryAwardCount;
	}

	public void setPrimaryAwardCount(int primaryAwardCount) {
		this.primaryAwardCount = primaryAwardCount;
	}

	public boolean isPrimaryAwardWon() {
		return primaryAwardWon;
	}

	public void setPrimaryAwardWon(boolean primaryAwardWon) {
		this.primaryAwardWon = primaryAwardWon;
	}

	public int getOtherWins() {
		return otherWins;
	}

	public void setOtherWins(int otherWins) {
		this.otherWins = otherWins;
	}

	public int getOtherNominations() {
		return otherNominations;
	}

	public void setOtherNominations(int otherNominations) {
		this.otherNominations = otherNominations;
	}

	public String getPosterLink() {
		return posterLink;
	}

	public void setPosterLink(String posterLink) {
		this.posterLink = posterLink;
	}

	public int getMetascore() {
		return metascore;
	}

	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getImdbId() {
		return imdbId;
	}

	public void setImdbId(String imdbId) {
		this.imdbId = imdbId;
	}

	public double getImdbRating() {
		return imdbRating;
	}

	public void setImdbRating(double imdbRating) {
		this.imdbRating = imdbRating;
	}

	public int getImdbVotes() {
		return imdbVotes;
	}

	public void setImdbVotes(int imdbVotes) {
		this.imdbVotes = imdbVotes;
	}

	public List<UserRating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<UserRating> userRating) {
		this.userRating = new ArrayList<UserRating>();
		this.userRating.addAll(userRating);
	}
	
	public void insertUserRating(UserRating rating) {
		if(this.userRating == null)
			this.userRating = new ArrayList<UserRating>();
		this.userRating.add(rating);
	}
	
	public int removeUserRating(UserRating rating) {
		if(this.userRating == null)
			return 0;
		for(UserRating rat: this.userRating) {
			if(rat.getUserRatingId().equals(rating.getUserRatingId())) {
				this.userRating.remove(rat);
				return 1;
			}
		}
		return 0;
	}
}
