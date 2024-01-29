package com.example.rasha.repository;

import com.example.rasha.model.OMDBMovie;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface OMDBMovieRepository extends JpaRepository<OMDBMovie, Long> {

}
