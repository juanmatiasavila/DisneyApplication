package com.juanmatiasavila.disney.service;

import com.juanmatiasavila.disney.models.Genre;
import com.juanmatiasavila.disney.models.GenreDTO;
import com.juanmatiasavila.disney.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GenreService {

    @Autowired
    GenreRepository genreRepository;

    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    public Optional<Genre> findById(Integer IdGenre){
        return genreRepository.findById(IdGenre);
    }

    public GenreDTO addGenre(GenreDTO genreDTO){
    return convertToDTO(Optional.of(genreRepository.save(convertToModel(genreDTO))));
    }

    public GenreDTO updateGenre(Genre genre, Integer id){
      Genre updateGenre = genreRepository.findByIdGenre(id);
      updateGenre.setIdGenre(genre.getIdGenre());
      updateGenre.setImageGenre(genre.getImageGenre());
      updateGenre.setNameGenre(genre.getNameGenre());
      updateGenre.setMovies(genre.getMovies());
      genreRepository.save(updateGenre);
        return convertToDTO(Optional.of(updateGenre));
    }

    public Boolean deteleGenre(Integer id){
        return genreRepository.findById(id).map(genre -> {genreRepository.delete(genre);
        return true;}).orElse(false);
    }

    public GenreDTO convertToDTO(Optional<Genre> genre) {
        GenreDTO genreDTO = new GenreDTO();
        genreDTO.setIdGenre(genre.get().getIdGenre());
        genreDTO.setNameGenre(genre.get().getNameGenre());
        genreDTO.setMovies(genre.get().getMovies());
        genreDTO.setImageGenre(genre.get().getImageGenre());
        return genreDTO;
    }
    public Genre convertToModel(GenreDTO genreDTO) {
        Genre genre = new Genre();
        genre.setIdGenre(genreDTO.getIdGenre());
        genre.setNameGenre(genreDTO.getNameGenre());
        genre.setMovies(genreDTO.getMovies());
        genre.setImageGenre(genreDTO.getImageGenre());
        return genre;
    }
}

