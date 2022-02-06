package com.dc.disney.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    private Long idCharacter;
    @NotBlank
    private String image;
    @NotBlank
    private String name;
    private Integer age;
    private BigDecimal weight;
    private String story;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Movie> movies = new ArrayList<>();

    public Long getIdCharacter() {
        return idCharacter;
    }
    public void setIdCharacter(Long idCharacter) {
        this.idCharacter = idCharacter;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        movie.getCartoonCharacters().add(this);
    }
    public void removeMovie(Movie movie) {
        movies.remove(movie);
        movie.getCartoonCharacters().remove(null);
    }

    @Override
    public String toString() {
        return "CartoonCharacter{" +
                "idCharacter=" + idCharacter +
                ", image='" + image + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", weight=" + weight +
                ", story='" + story + '\'' +
                ", movies=" + movies +
                '}';
    }
}
