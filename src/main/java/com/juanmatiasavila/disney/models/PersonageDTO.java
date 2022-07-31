package com.juanmatiasavila.disney.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.List;


public class PersonageDTO implements Serializable {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer idPersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String namePersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String imagePersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer agePersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer weightPersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String storyPersonage;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Movie> movies;

    public Integer getIdPersonage() {
        return idPersonage;
    }

    public void setIdPersonage(Integer idPersonage) {
        this.idPersonage = idPersonage;
    }

    public String getNamePersonage() {
        return namePersonage;
    }

    public void setNamePersonage(String namePersonage) {
        this.namePersonage = namePersonage;
    }

    public String getImagePersonage() {
        return imagePersonage;
    }

    public void setImagePersonage(String imagePersonage) {
        this.imagePersonage = imagePersonage;
    }

    public Integer getAgePersonage() {
        return agePersonage;
    }

    public void setAgePersonage(Integer agePersonage) {
        this.agePersonage = agePersonage;
    }

    public Integer getWeightPersonage() {
        return weightPersonage;
    }

    public void setWeightPersonage(Integer weightPersonage) {
        this.weightPersonage = weightPersonage;
    }

    public String getStoryPersonage() {
        return storyPersonage;
    }

    public void setStoryPersonage(String storyPersonage) {
        this.storyPersonage = storyPersonage;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
