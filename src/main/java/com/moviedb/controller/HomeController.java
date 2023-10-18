package com.moviedb.controller;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.moviedb.domain.Movie;
import com.moviedb.repository.MovieRepository;

@Controller
public class HomeController {

	@Autowired
    private MovieRepository movieRepo;

	@GetMapping("/")
    public String index(Model model) {
        List<Movie> recentlyReleasedMovies = movieRepo.findTop10ByOrderByReleaseDateDesc();
        model.addAttribute("recentlyReleasedMovies", recentlyReleasedMovies);
        return "index";
    }
}