package com.moviedb.domain;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;

    @Lob
    private byte[] image;

    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;

    @OneToMany(mappedBy = "movie", cascade = CascadeType.ALL)
    private Set<Review> reviews = new HashSet<>();

    public Movie() {
        super();
    }

    public Movie(Long id, String title, String description, byte[] image, Date releaseDate, Set<Review> reviews) {
        super();
        this.id = id;
        this.title = title;
        this.description = description;
        this.image = image;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
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

    // Average
    public Double calculateAverageRating() {
        if (reviews.isEmpty()) {
            return 0.0; // Default average rating if there are no reviews
        }

        double sum = 0.0;
        for (Review review : reviews) {
            sum += review.getRating();
        }

        return sum / reviews.size();
    }

    // Check if the current user has reviewed the movie
    public boolean isUserReviewed(Long userId) {
        for (Review review : reviews) {
            if (review.getUser().getId().equals(userId)) {
                return true;
            }
        }
        return false;
    }

    // Get the user's rating for the movie
    public Integer getUserReviewRating(Long userId) {
        for (Review review : reviews) {
            if (review.getUser().getId().equals(userId)) {
                return review.getRating();
            }
        }
        return null; // Return null if the user hasn't reviewed the movie
    }

    // Get the user's review comment for the movie
    public String getUserReviewComment(Long userId) {
        for (Review review : reviews) {
            if (review.getUser().getId().equals(userId)) {
                return review.getComment();
            }
        }
        return null; // Return null if the user hasn't reviewed the movie
    }

    // Get the user's review ID for the movie
    public Long getUserReviewId(Long userId) {
        for (Review review : reviews) {
            if (review.getUser().getId().equals(userId)) {
                return review.getId();
            }
        }
        return null; // Return null if the user hasn't reviewed the movie
    }
}
