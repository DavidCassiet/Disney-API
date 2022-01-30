package com.dc.disney.service;

import com.dc.disney.converter.CharacterConverter;
import com.dc.disney.dto.CharacterDto;
import com.dc.disney.entity.CartoonCharacter;
import com.dc.disney.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;

@Service
@Validated
public class CharacterService {

    private CharacterRepository characterRepository;
    private CharacterConverter characterConverter;

    public CharacterService(CharacterRepository characterRepository, CharacterConverter characterConverter) {
        this.characterRepository = characterRepository;
        this.characterConverter = characterConverter;
    }

    public void createCharacter(CharacterDto characterDto) {
        CartoonCharacter character = new CartoonCharacter();
        characterConverter.convert(characterDto, character);
        characterRepository.save(character);
    }

    public void editCharacter(Long idCharacter, CharacterDto characterDto) {
        characterRepository.findById(idCharacter)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        CartoonCharacter actualCharacter  = characterRepository.findById(idCharacter).get();
        characterConverter.convert(characterDto, actualCharacter);
        characterRepository.save(actualCharacter);
    }

    public void deleteCharacter(Long idCharacter) {
        characterRepository.findById(idCharacter)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        characterRepository.deleteById(idCharacter);
    }
}
