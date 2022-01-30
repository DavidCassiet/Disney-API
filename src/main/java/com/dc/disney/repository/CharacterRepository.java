package com.dc.disney.repository;

import com.dc.disney.entity.CartoonCharacter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterRepository extends JpaRepository<CartoonCharacter, Long> {
}