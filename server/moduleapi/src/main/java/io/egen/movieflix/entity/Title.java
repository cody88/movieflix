package io.egen.movieflix.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table
@NamedQueries({
	@NamedQuery(name="Title.findAll", query="FROM Title t"),
	@NamedQuery(name="Title.findByEmail", query="SELECT u FROM User u where u.email=:pEmail")
})
public class Title {

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
	private List<Genre> genre;
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
	private List<Language> languages;
	private String country;
	@ManyToOne(targetEntity = Award.class)
	@JoinColumn(name="AWARD_ID")
	private Award primaryAward;
	private int primaryAwardCount;
	private int otherWins;
	private int otherNominations;
	private String posterLink;
	private int metascore;
	@Column(nullable=false)
	private String type;
	
	@OneToOne(targetEntity = IMDBInfo.class)
	@JoinColumn(name="IMDB_INFO_ID")
	private IMDBInfo imdbInfo;
	
	@ElementCollection
    @CollectionTable(joinColumns=@JoinColumn(name="TITLE_ID"))
	//@JoinColumn(name="USER_RATING_ID", referencedColumnName="TITLE_ID")
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

	public List<Genre> getGenre() {
		return genre;
	}

	public void setGenre(List<Genre> genre) {
		this.genre = new ArrayList<Genre>();
		this.genre.addAll(genre);
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

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = new ArrayList<Language>();
		this.languages.addAll(languages);
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Award getPrimaryAward() {
		return primaryAward;
	}

	public void setPrimaryAward(Award primaryAward) {
		this.primaryAward = primaryAward;
	}

	public int getPrimaryAwardCount() {
		return primaryAwardCount;
	}

	public void setPrimaryAwardCount(int primaryAwardCount) {
		this.primaryAwardCount = primaryAwardCount;
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

	public IMDBInfo getImdbInfo() {
		return imdbInfo;
	}

	public void setImdbInfo(IMDBInfo imdbInfo) {
		this.imdbInfo = imdbInfo;
	}

	public List<UserRating> getUserRating() {
		return userRating;
	}

	public void setUserRating(List<UserRating> userRating) {
		this.userRating = new ArrayList<UserRating>();
		this.userRating.addAll(userRating);
	}
	
}
