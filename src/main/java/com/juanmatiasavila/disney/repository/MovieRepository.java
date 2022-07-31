package com.juanmatiasavila.disney.repository;

import com.juanmatiasavila.disney.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByIdMovie(Integer idMovie);
}
