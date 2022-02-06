package com.dc.disney.repository;

import com.dc.disney.entity.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CartoonCharacter, Long> {

    @Query("SELECT character FROM CartoonCharacter character WHERE character.name LIKE %:name%")
    List<CartoonCharacter> findByCharacterName(@Param("name")String name);
    CartoonCharacter findByName(String name);
    List<CartoonCharacter> findByAge(Integer age);
    @Query("SELECT cartoonCharacter FROM CartoonCharacter cartoonCharacter join fetch cartoonCharacter.movies movie WHERE movie.id = id_movie")
    List<CartoonCharacter> findByMovie(@Param("id_movie") Long idMovie);
}
