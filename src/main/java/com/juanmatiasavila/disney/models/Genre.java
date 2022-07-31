package com.juanmatiasavila.disney.models;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.List;

@Entity
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGenre;
    private String nameGenre;
    private String imageGenre;
    @OneToMany(mappedBy = "genres", cascade = {CascadeType.MERGE})
    @JsonIgnore
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
