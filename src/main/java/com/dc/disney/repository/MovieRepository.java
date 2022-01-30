package com.dc.disney.repository;

import com.dc.disney.entity.MovieOrSerie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<MovieOrSerie, Long> {
}
