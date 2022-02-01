package com.dc.disney.dto;

import com.dc.disney.entity.Movie;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CharacterDto {

    private String image;
    private String name;
    private Integer age;
    private BigDecimal weight;
    private String story;
    private List<Movie> movies = new ArrayList<>();

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

    public List<Movie> getMovies() {
        return movies;
    }
    public void setMovies(List<Movie> movies) {
        this.movies = movies;
    }
}
