package com.dc.disney.service;

import com.dc.disney.converter.MovieConverter;
import com.dc.disney.dto.MovieDto;
import com.dc.disney.entity.CartoonCharacter;
import com.dc.disney.entity.Genre;
import com.dc.disney.entity.Movie;
import com.dc.disney.repository.CharacterRepository;
import com.dc.disney.repository.GenreRepository;
import com.dc.disney.repository.MovieRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private MovieRepository movieRepository;
    private MovieConverter movieConverter;
    private CharacterRepository characterRepository;
    private GenreRepository genreRepository;

    public MovieService(MovieRepository movieRepository, MovieConverter movieConverter,
                        CharacterRepository characterRepository, GenreRepository genreRepository) {
        this.movieRepository = movieRepository;
        this.movieConverter = movieConverter;
        this.characterRepository = characterRepository;
        this.genreRepository = genreRepository;
    }

    public void createMovie(MovieDto movieDto) {
        Movie movie = new Movie();
        movieConverter.dtoToEntity(movieDto, movie);
        List<Genre> genres = genreRepository.findAll();
        List<CartoonCharacter> characters = characterRepository.findAll();
        for (String genreName : movieDto.getGenresName()) {
            if (genres.stream().noneMatch(g -> Objects.equals(g.getName(), genreName))) {
                Genre genre = new Genre();
                genre.setName(genreName);
                genreRepository.save(genre);
                movie.addGenre(genre);
            } else {
                Genre actualGenre = genreRepository.findByName(genreName);
                movie.addGenre(actualGenre);
            }
        }
        for (String characterName : movieDto.getCharactersName()) {
            if (characters.stream().anyMatch(c -> Objects.equals(c.getName(), characterName))) {
                CartoonCharacter actualCharacter = characterRepository.findByName(characterName);
                movie.addCharacter(actualCharacter);
            } else {
                System.out.println("Character not found");
            }
        }
        movieRepository.save(movie);
    }

    public void editMovie(Long idMovie, MovieDto movieDto) {
        movieRepository.findById(idMovie)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        Movie actualMovie  = movieRepository.findById(idMovie).get();
        movieConverter.dtoToEntity(movieDto, actualMovie);
        movieRepository.save(actualMovie);
    }

    public void deleteMovie(Long idMovie) {
        movieRepository.findById(idMovie)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        movieRepository.deleteById(idMovie);
    }

    public MovieDto getMovieDetails(Long idMovie) {
        Movie actualMovie  = movieRepository.findById(idMovie)
                .orElseThrow(() -> new EntityNotFoundException("Movie not found"));
        MovieDto movieDto = new MovieDto();
        movieConverter.entityToDto(actualMovie, movieDto);
        return movieDto;
    }

    public List<Map> getAllMovies(String tittle, Long idGenre, String creationDate) {
        List<Movie> allMovies = new ArrayList<>();
        if (Objects.isNull(tittle) && Objects.isNull(idGenre) && Objects.isNull(creationDate)) {
            allMovies = movieRepository.findAll();
        } else if (Objects.nonNull(tittle)) {
            allMovies = movieRepository.findByTittle(tittle);
        } else if (Objects.nonNull(idGenre)) {
            allMovies = movieRepository.findByGenre(idGenre);
        } else {
            allMovies = movieRepository.findByOrder(creationDate);
        }
        List<Map> allMoviesMap = allMovies.stream()
                .map(movie -> {
                    MovieDto movieDto = new MovieDto();
                    movieDto.setImage(movie.getImage());
                    movieDto.setTittle(movie.getTittle());
                    movieDto.setCreationDate(movie.getCreationDate());

                    Map movieMap = new HashMap();
                    movieMap.put("image", movieDto.getImage());
                    movieMap.put("tittle", movieDto.getTittle());
                    movieMap.put("creationDate", movieDto.getCreationDate());
                    return movieMap;
                }).collect(Collectors.toList());
        return allMoviesMap;
    }
}
