package com.dc.disney.repository;

import com.dc.disney.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT movie FROM Movie movie WHERE movie.tittle LIKE %:tittle%")
    List<Movie> findByTittle(@Param("tittle") String tittle);
    @Query("SELECT movie FROM Movie movie join fetch movie.genres genre WHERE genre.id = id_genre")
    List<Movie> findByGenre(@Param("id_genre")Long idGenre);
    @Query("SELECT movie FROM Movie movie ORDER BY movie.creationDate DESC")
    List<Movie> findByOrder(String creationDate);
}