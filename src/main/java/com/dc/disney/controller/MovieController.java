package com.dc.disney.controller;

import com.dc.disney.dto.MovieDto;
import com.dc.disney.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/movies")
public class MovieController {

    private MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping
    public ResponseEntity<?> createMovie(@RequestBody @Valid MovieDto movieDto) {
        movieService.createMovie(movieDto);
        return new ResponseEntity<>("Successfully created movie", HttpStatus.CREATED);
    }

    @PutMapping("/{idMovie}")
    public ResponseEntity<?> editMovie(@PathVariable("idMovie") Long idMovie,
                                       @RequestBody @Valid MovieDto movieDto) {
        movieService.editMovie(idMovie, movieDto);
        return new ResponseEntity<>("Successfully edited movie",HttpStatus.OK);
    }

    @DeleteMapping("/{idMovie}")
    public ResponseEntity<?> deleteMovie(@PathVariable("idMovie") Long idMovie) {
        movieService.deleteMovie(idMovie);
        return new ResponseEntity<>("Successfully deleted movie",HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getAllMovies(@RequestParam(name = "tittle", required = false) String tittle,
                                          @RequestParam(name = "idGenre", required = false) Long idGenre,
                                          @RequestParam(name = "creationDate", required = false) String creationDate) {
        return new ResponseEntity<>(movieService.getAllMovies(tittle, idGenre, creationDate), HttpStatus.OK);
    }

    @GetMapping("/details/{idMovie}")
    public ResponseEntity<?> getMovieDetails(@PathVariable("idMovie") Long idMovie) {
        return new ResponseEntity<>(movieService.getMovieDetails(idMovie), HttpStatus.OK);
    }
}
