package com.dc.disney.converter;

import com.dc.disney.dto.MovieDto;
import com.dc.disney.entity.Movie;
import com.dc.disney.repository.MovieRepository;
import org.springframework.stereotype.Component;

@Component
public class MovieConverter {

    private MovieRepository movieRepository;

    public MovieConverter(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie dtoToEntity(MovieDto movieDto, Movie movie) {
        movie.setImage(movieDto.getImage());
        movie.setTittle(movieDto.getTittle());
        movie.setCreationDate(movieDto.getCreationDate());
        movie.setScore(movieDto.getScore());
        return movie;
    }

    public MovieDto entityToDto(Movie movie, MovieDto movieDto) {
        movieDto.setImage(movie.getImage());
        movieDto.setTittle(movie.getTittle());
        movieDto.setCreationDate(movie.getCreationDate());
        movieDto.setScore(movie.getScore());
        movieDto.setCartoonCharacters(movie.getCartoonCharacters());
        movieDto.setGenres(movie.getGenres());
        return movieDto;
    }
}
