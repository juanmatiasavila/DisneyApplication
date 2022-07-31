package com.juanmatiasavila.disney.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class MovieDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idMovie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imageMovie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String titleMovie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date relaseDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Min(message = "Min Value = 1", value = 1)
    @Max(message = "Max Value = 5", value = 5)
    private Integer ratingMovie;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonIgnore
    private Genre genres;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Personage> personages;

    public Genre getGenres() {
        return genres;
    }

    public void setGenres(Genre genres) {
        this.genres = genres;
    }

    public Integer getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(Integer idMovie) {
        this.idMovie = idMovie;
    }

    public String getImageMovie() {
        return imageMovie;
    }

    public void setImageMovie(String imageMovie) {
        this.imageMovie = imageMovie;
    }

    public String getTitleMovie() {
        return titleMovie;
    }

    public void setTitleMovie(String titleMovie) {
        this.titleMovie = titleMovie;
    }

    public Date getRelaseDate() {
        return relaseDate;
    }

    public void setRelaseDate(Date relaseDate) {
        this.relaseDate = relaseDate;
    }

    public Integer getRatingMovie() {
        return ratingMovie;
    }

    public void setRatingMovie(Integer ratingMovie) {
        this.ratingMovie = ratingMovie;
    }


    public List<Personage> getPersonages() {
        return personages;
    }

    public void setPersonages(List<Personage> personages) {
        this.personages = personages;
    }
}
