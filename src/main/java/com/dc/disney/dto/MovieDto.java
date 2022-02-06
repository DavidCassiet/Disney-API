package com.dc.disney.dto;

import com.dc.disney.entity.CartoonCharacter;
import com.dc.disney.entity.Genre;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.ArrayList;
import java.util.List;

public class MovieDto {

    private String image;
    private String tittle;
    private String creationDate;
    private Integer score;
    private List<CartoonCharacter> cartoonCharacters = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    @JsonIgnore
    private List<String> genresName = new ArrayList<>();
    @JsonIgnore
    private List<String> charactersName = new ArrayList<>();

    public String getImage() {
        return image;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getCreationDate() {
        return creationDate;
    }
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getScore() {
        return score;
    }
    public void setScore(Integer score) {
        this.score = score;
    }

    public List<CartoonCharacter> getCartoonCharacters() {
        return cartoonCharacters;
    }
    public void setCartoonCharacters(List<CartoonCharacter> cartoonCharacters) {
        this.cartoonCharacters = cartoonCharacters;
    }

    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public List<String> getGenresName() {
        return genresName;
    }
    public void setGenresName(List<String> genresName) {
        this.genresName = genresName;
    }

    public List<String> getCharactersName() {
        return charactersName;
    }
    public void setCharactersName(List<String> charactersName) {
        this.charactersName = charactersName;
    }
}
