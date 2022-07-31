package com.juanmatiasavila.disney.models;

import com.fasterxml.jackson.annotation.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import javax.persistence.*;
import java.util.List;


@Entity
public class Personage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personage")
    private Integer idPersonage;
    private String namePersonage;
    private String imagePersonage;
    private Integer agePersonage;
    private Integer weightPersonage;
    private String storyPersonage;
    @ManyToMany(mappedBy = "personages",
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @Fetch(FetchMode.JOIN)
    @JsonIgnore
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

    public void setStoryPersonage(String storyPersoage) {
        this.storyPersonage = storyPersoage;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public Personage addUpdatePersonage(Personage personage) {
        this.idPersonage = personage.getIdPersonage();
        this.namePersonage = personage.getNamePersonage();
        this.imagePersonage = personage.getImagePersonage();
        this.agePersonage = personage.getAgePersonage();
        this.weightPersonage = personage.getWeightPersonage();
        this.storyPersonage = personage.getStoryPersonage();
        return this;
    }
}
