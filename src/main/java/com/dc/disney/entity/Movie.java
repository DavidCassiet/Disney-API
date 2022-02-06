package com.dc.disney.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMovie;
    @NotBlank
    private String image;
    @NotBlank
    private String tittle;
    @DateTimeFormat
    private String creationDate;
    @Min(1)@Max(5)
    private Integer score;
    @ManyToMany(mappedBy = "movies")
    private List<CartoonCharacter> cartoonCharacters = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    private List<Genre> genres = new ArrayList<>();

    public Long getIdMovie() {
        return idMovie;
    }
    public void setIdMovie(Long idMovie) {
        this.idMovie = idMovie;
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

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<CartoonCharacter> getCartoonCharacters() {
        return cartoonCharacters;
    }
    public void setCartoonCharacters(List<CartoonCharacter> cartoonCharacters) {
        this.cartoonCharacters = cartoonCharacters;
    }

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public List<Genre> getGenres() {
        return genres;
    }
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public void addCharacter(CartoonCharacter cartoonCharacter) {
        cartoonCharacters.add(cartoonCharacter);
        cartoonCharacter.getMovies().add(this);
    }
    public void removeCharacter(CartoonCharacter cartoonCharacter) {
        cartoonCharacters.remove(cartoonCharacter);
        cartoonCharacter.getMovies().remove(null);
    }

    public void addGenre(Genre genre) {
        genres.add(genre);
        genre.getMovies().add(this);
    }
    public void removeGenre(Genre genre) {
        genres.remove(genre);
        genre.getMovies().remove(null);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "idMovie=" + idMovie +
                ", image='" + image + '\'' +
                ", tittle='" + tittle + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", score=" + score +
                ", cartoonCharacters=" + cartoonCharacters +
                ", genres=" + genres +
                '}';
    }
}
