package com.dc.disney.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
public class CartoonCharacter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String image;
    @NotBlank
    private String name;
    private Integer age;
    private BigDecimal weight;
    private String story;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<MovieOrSerie> movieOrSeries = new ArrayList<>();

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }
    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getWeight() {
        return weight;
    }
    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getStory() {
        return story;
    }
    public void setStory(String story) {
        this.story = story;
    }

    public List<MovieOrSerie> getMovieOrSeries() {
        return movieOrSeries;
    }
    public void setMovieOrSeries(List<MovieOrSerie> movieOrSeries) {
        this.movieOrSeries = movieOrSeries;
    }

    private void addMovieOrSerie(MovieOrSerie movieOrSerie) {
        movieOrSeries.add(movieOrSerie);
        movieOrSerie.getCartoonCharacters().add(this);
    }
    private void removeMovieOrSerie(MovieOrSerie movieOrSerie) {
        movieOrSeries.remove(movieOrSerie);
        movieOrSerie.getCartoonCharacters().remove(null);
    }

    @Override
    public String toString() {
        return "CartoonCharacter{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", story='" + story + '\'' +
                ", movieOrSeries=" + movieOrSeries +
                '}';
    }
}
