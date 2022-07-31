package com.juanmatiasavila.disney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.List;

public class GenreDTO implements Serializable {

    private Integer idGenre;
    private String nameGenre;
    private String imageGenre;
    private List<Movie> movies;

    public Integer getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(Integer idGenre) {
        this.idGenre = idGenre;
    }

    public String getNameGenre() {
        return nameGenre;
    }

    public void setNameGenre(String nameGenre) {
        this.nameGenre = nameGenre;
    }

    public String getImageGenre() {
        return imageGenre;
    }

    public void setImageGenre(String imageGenre) {
        this.imageGenre = imageGenre;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
