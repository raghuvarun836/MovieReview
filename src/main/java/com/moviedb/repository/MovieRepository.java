package com.moviedb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.moviedb.domain.Movie;
import com.moviedb.domain.Review;

import jakarta.transaction.Transactional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{

	List<Movie> findTop5ByOrderByReleaseDateDesc();

}