package com.test.movieratingapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rest")
public class HomeController {
	
@Autowired
private HomeService homeService;

@PostMapping("/customer/{id}/rate/{rating}")
public MovieData addRating(@PathVariable("id") Long id, @RequestParam("movie") String movie, @PathVariable("rating") Double rating) {
	
	System.out.println("Id" + id + "movie" + movie +"Rating"+ rating);
	MovieData addRatingToMovie = homeService.addRatingToMovie(id,movie,rating);
	System.out.println(addRatingToMovie);
	return addRatingToMovie;
}	

@GetMapping("/maxratedmovie")
public String getHighestRatedMovie() {
	return homeService.findHighestRatedMovie();
}

@GetMapping("/maxratedcustomer")
public CustomerVM getHighestRatedCustomer() {
	CustomerVM findHighestRatedCustomer = homeService.findHighestRatedCustomer();
	return findHighestRatedCustomer;
	
}
	
	
}
