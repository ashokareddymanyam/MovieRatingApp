package com.test.movieratingapp;

public class CustomerVM {

	private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Double customerAvgRating;
	
	private Double avgRating;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Double getCustomerAvgRating() {
		return customerAvgRating;
	}

	public void setCustomerAvgRating(Double customerAvgRating) {
		this.customerAvgRating = customerAvgRating;
	}

	public Double getAvgRating() {
		return avgRating;
	}

	public void setAvgRating(Double avgRating) {
		this.avgRating = avgRating;
	}
	
}
