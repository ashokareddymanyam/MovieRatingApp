package com.test.movieratingapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDataRepository extends JpaRepository<MovieData, String>{

}
