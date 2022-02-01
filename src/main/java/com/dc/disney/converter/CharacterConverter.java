package com.dc.disney.converter;

import com.dc.disney.dto.CharacterDto;
import com.dc.disney.entity.CartoonCharacter;
import com.dc.disney.repository.CharacterRepository;

import org.springframework.stereotype.Component;

@Component
public class CharacterConverter  {

    private CharacterRepository characterRepository;

    public CharacterConverter(CharacterRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public CartoonCharacter dtoToEntity(CharacterDto characterDto, CartoonCharacter character) {
        character.setImage(characterDto.getImage());
        character.setName(characterDto.getName());
        character.setAge(characterDto.getAge());
        character.setWeight(characterDto.getWeight());
        character.setStory(characterDto.getStory());
        return character;
    }

    public CharacterDto entityToDto(CartoonCharacter cartoonCharacter, CharacterDto characterDto) {
        characterDto.setImage(cartoonCharacter.getImage());
        characterDto.setName(cartoonCharacter.getName());
        characterDto.setAge(cartoonCharacter.getAge());
        characterDto.setWeight(cartoonCharacter.getWeight());
        characterDto.setStory(cartoonCharacter.getStory());
        characterDto.setMovies(cartoonCharacter.getMovies());
        return characterDto;
    }
}
