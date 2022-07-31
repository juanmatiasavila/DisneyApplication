package com.juanmatiasavila.disney.service;

import com.juanmatiasavila.disney.models.Movie;
import com.juanmatiasavila.disney.models.MovieDTO;
import com.juanmatiasavila.disney.repository.MovieRepository;
import com.juanmatiasavila.disney.repository.PersonageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    @Autowired
    PersonageRepository personageRepository;
    @Autowired
    PersonageService personageService;

    public List<MovieDTO> findAll() {
        List<MovieDTO> movieDTOList = movieRepository.findAll().stream()
                .map(movie -> convertToDTO(Optional.of(movie)).get()).collect(Collectors.toList());
        return movieDTOList;
    }
    public Boolean deleteMovie(Integer id){
        return movieRepository.findById(id).map(movie -> {movieRepository.delete(movie);
        return true;}).orElse(false);
    }
    public Boolean validatorMovie(Integer id) {
        Boolean validatorId = movieRepository.findById(id).isPresent();
        return validatorId;
    }
    public Boolean validatorPersonage(Integer id) {
        Boolean validatorId = personageRepository.findById(id).isPresent();
        return validatorId;
    }
    public List<MovieDTO> findByGenre(Integer genres, List<MovieDTO> movieDTOList) {
        if (genres == null) {
            return movieDTOList;
        } else {
            List<MovieDTO> movieGenreDTO = movieDTOList.stream().filter(movieDTOList1 -> movieDTOList1.getGenres().getIdGenre() == genres).collect(Collectors.toList());
            return movieGenreDTO;
        }
    }
    public List<MovieDTO> findByName(String name, List<MovieDTO> movieDTOList) {
        if (name == null) {
            return movieDTOList;
        } else {
            List<MovieDTO> nameMovieDTO = movieDTOList.stream().filter(movieDTO1 -> movieDTO1.getTitleMovie().equals(name)).collect(Collectors.toList());
            return nameMovieDTO;
        }
    }
    public List<MovieDTO> orderList(String order, List<MovieDTO> movieDTOList) {
        if (order == null) {
            return movieDTOList;
        }
        if (order.toUpperCase().equals("ASC")) {
            List<MovieDTO> nameMovieDTO = movieDTOList.stream().sorted(Comparator.comparingInt(MovieDTO::getIdMovie)).collect(Collectors.toList());
            return nameMovieDTO;
        }
        if (order.toUpperCase().equals("DESC")) {
            List<MovieDTO> nameMovieDTO = movieDTOList.stream().sorted(Comparator.comparingInt(MovieDTO::getIdMovie).reversed()).collect(Collectors.toList());
            return nameMovieDTO;
        } else {
            return movieDTOList;
        }
    }

    public List<MovieDTO> findByRequest(String name, Integer genre, String order) {
        List<MovieDTO> returnDTO = orderList(order, findByName(name, findByGenre(genre, findAll())));
        return returnDTO;
    }

    public MovieDTO addMovieWithPersonage(MovieDTO movieDTO) {
       Movie movie = movieRepository.save(convertToModel(Optional.of(movieDTO)).get());
        return convertToDTO(Optional.of(movie)).get();
    }

    public MovieDTO deletePersonageFromMovie(Integer idMovie, Integer idPersonage) {
        Movie movie = movieRepository.findByIdMovie(idMovie);
        movie.getPersonages().remove(personageRepository.findByIdPersonage(idPersonage));
        movieRepository.save(movie);
        return convertToDTO(Optional.of(movie)).get();
    }

    public MovieDTO addPersonageFromMovie(Integer idMovie, Integer idPersonage) {
        Movie movie = movieRepository.findByIdMovie(idMovie);
        movie.getPersonages().add(personageRepository.findByIdPersonage(idPersonage));
        movieRepository.save(movie);
        return convertToDTO(Optional.of(movie)).get();
    }

    public MovieDTO update(MovieDTO movieDTO, Integer id) {
        Movie updateMovie = movieRepository.findById(id)
                .map(movie1 -> movie1.addUpdateMovie(convertToModel(Optional.of(movieDTO)).get())).get();
        movieRepository.save(updateMovie);
        return convertToDTO(Optional.of(updateMovie)).get();
    }

    public Optional<MovieDTO> convertToDTO(Optional<Movie> movie) {
        Optional<MovieDTO> movieDTO = Optional.of(new MovieDTO());
        movieDTO.get().setIdMovie(movie.get().getIdMovie());
        movieDTO.get().setTitleMovie(movie.get().getTitleMovie());
        movieDTO.get().setPersonages(movie.get().getPersonages());
        movieDTO.get().setImageMovie(movie.get().getImageMovie());
        movieDTO.get().setRatingMovie(movie.get().getRatingMovie());
        movieDTO.get().setRelaseDate(movie.get().getReleaseDate());
        movieDTO.get().setGenres(movie.get().getGenres());
        return movieDTO;
    }

    public Optional<Movie> convertToModel(Optional<MovieDTO> movieDTO) {
        Optional<Movie> movie = Optional.of(new Movie());
        movie.get().setIdMovie(movieDTO.get().getIdMovie());
        movie.get().setTitleMovie(movieDTO.get().getTitleMovie());
        movie.get().setPersonages(movieDTO.get().getPersonages());
        movie.get().setImageMovie(movieDTO.get().getImageMovie());
        movie.get().setRatingMovie(movieDTO.get().getRatingMovie());
        movie.get().setReleaseDate(movieDTO.get().getRelaseDate());
        movie.get().setGenres(movieDTO.get().getGenres());
        return movie;
    }

    public List<MovieDTO> shortDTO(List<MovieDTO> movieDTOList) {
        List<MovieDTO> shortListDTO = movieDTOList.stream().
                map(movieDTO -> {
                    MovieDTO movieDTO1 = new MovieDTO();
                    movieDTO1.setImageMovie(movieDTO.getImageMovie());
                    movieDTO1.setTitleMovie(movieDTO.getTitleMovie());
                    movieDTO1.setRelaseDate(movieDTO.getRelaseDate());
                    return movieDTO1;
                }).collect(Collectors.toList());
        return shortListDTO;
    }
}
