package com.moviedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.moviedb.domain.Movie;
import com.moviedb.repository.MovieRepository;
import com.moviedb.repository.ReviewRepository;

@Controller
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @GetMapping("/addMovie")
    public String showAddMovieForm() {
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute Movie movie) {
        try {
            movieRepository.save(movie);
            return "addMovie";
        } catch (Exception e) {
            // Log the exception for debugging
            e.printStackTrace();
            // Handle the exception or return an error view
            return "error"; // Create an "error.html" template for displaying errors
        }
    }

}