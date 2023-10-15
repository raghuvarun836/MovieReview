package com.moviedb.domain;

import java.util.*;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
public class Movie
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();
    
	public Movie() {
		super();
	}

	public Movie(Long id, String title, String description, Date releaseDate, Set<Review> reviews) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseDate = releaseDate;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
}
