package com.moviedb.domain;

import jakarta.persistence.*;
import java.util.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;
    @Transient
    private String confirmPassword;

    // Add more user-related fields as needed (e.g., roles)

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Review> reviews;

    // Constructors, getters, and setters

    public User() {
		super();
	}

	public User(Long id, String name, String email, String password, String confirmPassword, Set<Review> reviews) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.reviews = reviews;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Set<Review> getReviews() {
		return reviews;
	}

	public void setReviews(Set<Review> reviews) {
		this.reviews = reviews;
	}
    
}
