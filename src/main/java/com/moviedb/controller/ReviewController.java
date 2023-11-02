package com.moviedb.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.moviedb.domain.Review;
import com.moviedb.repository.ReviewRepository;

@Controller
public class ReviewController
{
	@Autowired
	private ReviewRepository reviewRepository;
}
