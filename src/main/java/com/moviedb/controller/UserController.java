package com.moviedb.controller;

import java.util.*;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.moviedb.domain.Movie;
import com.moviedb.domain.Review;
import com.moviedb.domain.User;
import com.moviedb.repository.MovieRepository;
import com.moviedb.repository.ReviewRepository;
import com.moviedb.repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    
    @GetMapping("/userLogin")
    public String showLoginForm(Model model) {
        // Add any required model attributes for the login form
        return "login_signup"; // This should match the Thymeleaf template name
    }

    @PostMapping("/userLogin")
    public String login(@RequestParam("email") String email, @RequestParam("password") String password, Model model, HttpSession session) {
        // Retrieve the user from the repository based on the email
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            // Successful login, store the userId in the session
            session.setAttribute("userId", user.getId());

            // Redirect to user dashboard or another page
            return "redirect:/userDashboard";
        } else {
            // Login failed, display an error message
            model.addAttribute("loginError", "Email or password incorrect");
            return "login_signup";
        }
    }


    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        // Add a new User object to the model for the signup form
    	model.addAttribute("user", new User());
        return "login_signup"; // This should match the Thymeleaf template name
    }

    @PostMapping("/signup")
    public String signup(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        // Check if there are any validation errors
        if (bindingResult.hasErrors()) {
            // Display the validation errors on the page
        	model.addAttribute("signupError", "Please correct the following errors:");
            model.addAttribute("emailError", bindingResult.getFieldError("email"));
            model.addAttribute("passwordError", bindingResult.getFieldError("password"));
            return "login_signup";
        }

        // Check if the password and confirmPassword match
        if (!user.getPassword().equals(user.getConfirmPassword())) {
            model.addAttribute("signupError", "Passwords did not match");
            return "login_signup";
        }

        // Check if the email is already registered
        User existingUser = userRepository.findByEmail(user.getEmail());
        if (existingUser != null) {
            model.addAttribute("signupError", "Email already registered");
            return "login_signup";
        }

        // Save the new user to the repository
        userRepository.save(user);

        model.addAttribute("signupSuccess", "You have successfully signed up!");
        
        // Successful registration, redirect to the login page
        return "redirect:/login";
    }

    @GetMapping("/userDashboard")
    public String userDashboard(Model model) {
        List<Movie> movies = movieRepository.findAll();
//        printing Movies for verifying
//        for(Movie m : movies) {
//        	System.out.println(m.getId()+" "+m.getTitle()+" "+m.getDescription());
//        }
        model.addAttribute("movies", movies);
        return "userDashboard";
    }

    @PostMapping("/submitReview")
    public String submitReview(
        @RequestParam("movieId") Long movieId,
        @RequestParam("rating") int rating,
        @RequestParam("comment") String comment,
        HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");

        // Check if a review already exists for this movie and user
        Review existingReview = reviewRepository.findByMovieIdAndUserId(movieId, userId);

        if (existingReview != null) {
            // Review already exists, update it
            existingReview.setRating(rating);
            existingReview.setComment(comment);
            reviewRepository.save(existingReview);
        } else {
            // Create a new Review object
            Review newReview = new Review();
            newReview.setMovie(movieRepository.findById(movieId).orElse(null));
            newReview.setUser(userRepository.findById(userId).orElse(null));
            newReview.setRating(rating);
            newReview.setComment(comment);

            // Save the new review to the database
            reviewRepository.save(newReview);
        }

        return "redirect:/userDashboard";
    }
    
}