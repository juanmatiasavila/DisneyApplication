package com.juanmatiasavila.disney.models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Date;
import java.util.List;


@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    private Integer idMovie;
    private String imageMovie;
    private String titleMovie;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date releaseDate;
    @Min(message = "Min Value = 1", value = 1)
    @Max(message = "Max Value = 5", value = 5)
    private Integer ratingMovie;
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_genre")
    @JsonIgnore
    private Genre genres;
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "personage_movie", joinColumns = @JoinColumn(name = "movie"),
            inverseJoinColumns = @JoinColumn(name = "personage"))
    @JsonIgnore
    private List<Personage> personages;

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public List<Personage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<Personage> personages) {
        this.personages = personages;
    }
    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getRatingMovie() {
        return ratingMovie;
    }

    public void setRatingMovie(Integer ratingMovie) {
        this.ratingMovie = ratingMovie;
    }

    public Genre getGenres() {
        return genres;
    }

    public void setGenres(Genre genres) {
        this.genres = genres;
    }

    public Movie addUpdateMovie(Movie movie) {
        this.imageMovie = movie.getImageMovie();
        this.titleMovie = movie.getTitleMovie();
        this.releaseDate = movie.getReleaseDate();
        this.ratingMovie = movie.getRatingMovie();
        return this;
    }
}
