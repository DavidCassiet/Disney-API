package com.dc.disney.entity;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MovieOrSerie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String image;
    @NotBlank
    private String tittle;
    @DateTimeFormat
    private String creationDate;
    private Integer score;
    @ManyToMany(mappedBy = "movieOrSeries")
    private List<CartoonCharacter> cartoonCharacters = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Genre> genres = new ArrayList<>();

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

    private void addGenre(Genre genre) {
        genres.add(genre);
        genre.getMovieOrSeries().add(this);
    }
    private void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovieOrSeries().remove(null);
    }

    @Override
    public String toString() {
        return "MovieOrSerie{" +
                "id=" + id +
                ", image='" + image + '\'' +
                ", tittle='" + tittle + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", score=" + score +
                ", cartoonCharacters=" + cartoonCharacters +
                ", genres=" + genres +
                '}';
    }
}
