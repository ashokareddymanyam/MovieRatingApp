package com.test.movieratingapp;

import java.util.OptionalLong;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDataRepository extends JpaRepository<CustomerData, Long>{

	@Query("select data.id from CustomerData data where data.firstName =:firstName and data.lastName =:lastName")
	Long getIDUsingFirstLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
	
}
