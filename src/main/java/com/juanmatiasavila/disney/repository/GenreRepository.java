package com.juanmatiasavila.disney.repository;

import com.juanmatiasavila.disney.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {

    Genre findByIdGenre(Integer idGenre);
}
