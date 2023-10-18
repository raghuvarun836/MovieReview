package com.moviedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.moviedb.domain.Admin;
import com.moviedb.domain.Movie;
import com.moviedb.repository.AdminRepository;
import com.moviedb.repository.MovieRepository;

@Controller
public class AdminController
{
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
    private MovieRepository movieRepository;
	
	@GetMapping("/adminLogin")
    public String showLoginForm(Model model) {
        // Add any required model attributes for the login form
        return "adminLogin"; // This should match the Thymeleaf template name
    }
	
	@PostMapping("/adminLogin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        // Retrieve the user from the repository based on the email
        Admin admin = adminRepository.findByEmail(email);

        if (admin != null && admin.getPassword().equals(password)) {
            // Successful login redirect to admin dashboard
            return "adminDashboard";
        } else {
            // Login failed, display an error message
            model.addAttribute("loginError", "Email or password incorrect");
            return "adminLogin";
        }
    }
	
	@GetMapping("/addMovie")
    public String showAddMovieForm() {
        return "addMovie";
    }

    @PostMapping("/addMovie")
    public String addMovie(@ModelAttribute Movie movie) {
    	movieRepository.save(movie);
    	return "adminDashboard";
    }
}
