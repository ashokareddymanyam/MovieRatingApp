package com.test.movieratingapp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class HomeService {
	
	@Autowired
	private CustomerDataRepository customerDataRepository;
	
	@Autowired
	private MovieDataRepository movieDataRepo;

	/**
	 * Add Rating to the Movie
	 * 
	 * @param id
	 * @param movie
	 * @param rating
	 */
	@Transactional
	public MovieData addRatingToMovie(Long id, String movieName, Double rating) {
		
		Optional<CustomerData> CustomerOpt = customerDataRepository.findById(id);
		Customer customer = new Customer();
		if(CustomerOpt.isPresent()) {
			CustomerData dbCustomer = CustomerOpt.get();
			MovieData movieData = new MovieData();
			movieData.setMovieName(movieName);
			Set<Customer> customerSet = movieData.getCustomer();
			customer.setCustomerId(id);
			customer.setFirstName(dbCustomer.getFirstName());
			customer.setLastName(dbCustomer.getLastName());
			customer.setRating(rating);
			customerSet.add(customer);
			return movieDataRepo.save(movieData);
		}
		
		return null;
		
	}

	/**
	 * Find Max Avg Rated Movie
	 * 
	 * @return String
	 */
	public String findHighestRatedMovie() {
		
		List<MovieData> movieData = movieDataRepo.findAll();
		Map<Double,String> movieMap = new HashMap<>(); 
		
		movieData.forEach(i ->{
			Set<Customer> customerSet = i.getCustomer();
			double movieAVG = customerSet.stream().mapToDouble(Customer::getRating).average().orElse(Double.NaN);
			movieMap.put(movieAVG,i.getMovieName());
		});
		
		String movie = Collections.max(movieMap.entrySet(), Comparator.comparingDouble(Map.Entry::getKey)).getValue();
		System.out.println(movie);
		return movie;
	}

	/**
	 * Find highest Avg customer and overall customer avg rating 
	 * 
	 * @return CustomerVM
	 */
	public CustomerVM findHighestRatedCustomer() {
		
		List<MovieData> movieData = movieDataRepo.findAll();
		Map<String,List<Double>> custwithAllRatingMap = new HashMap<>(); 
		Map<String,Double> avgMap = new HashMap<>(); 
		List<Double> overAllCustomerRatingList = new ArrayList<>();
		movieData.forEach(i ->{
			Set<Customer> customerSet = i.getCustomer();
			customerSet.stream().forEach(cus->{
				StringBuilder firstLastCustName = new StringBuilder();
				firstLastCustName.append(cus.getFirstName());
				firstLastCustName.append("|");
				firstLastCustName.append(cus.getLastName());
				String custName = firstLastCustName.toString();
				if(custwithAllRatingMap.containsKey(custName)) {
					List<Double> ratingList = custwithAllRatingMap.get(custName);
					ratingList.add(cus.getRating());
				} else {
					List<Double> ratingList = new ArrayList<>();
					ratingList.add(cus.getRating());
					custwithAllRatingMap.put(custName, ratingList);
				}
			});
		});
		
		custwithAllRatingMap.entrySet().forEach(avg->{
			List<Double> avgList = avg.getValue();
			overAllCustomerRatingList.addAll(avgList);
			OptionalDouble maxAvgOfCust = avgList.stream().mapToDouble(a -> a).average();
			if(maxAvgOfCust.isPresent()) {
				avgMap.put(avg.getKey(), maxAvgOfCust.getAsDouble());
			}
		});
		
		String customerName = Collections.max(avgMap.entrySet(), Comparator.comparingDouble(Map.Entry::getValue)).getKey();
		Double maxAvgRating = avgMap.get(customerName);
		
		OptionalDouble overAllCustAvg = overAllCustomerRatingList.stream().mapToDouble(a -> a).average();
		
		CustomerVM custVM = new CustomerVM();
		String[] split = customerName.split("\\|");
		custVM.setId(fetchCustomerId(split[0],split[1]));
		custVM.setFirstName(split[0]);
		custVM.setLastName(split[1]);
		custVM.setCustomerAvgRating(maxAvgRating);
		custVM.setAvgRating(overAllCustAvg.getAsDouble());
		
		return custVM;
		
	}

	/**
	 * Fetch customer Id
	 * 
	 * @param firstName
	 * @param lastName
	 * @return Long
	 */
	private Long fetchCustomerId(String firstName, String lastName) {
		Long idUsingFirstLastName = customerDataRepository.getIDUsingFirstLastName(firstName,lastName);
		if(null != idUsingFirstLastName) {
			return idUsingFirstLastName;
		}
		return null;
	}
	
	

}
