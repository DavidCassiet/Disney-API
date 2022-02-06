package com.dc.disney.service;

import com.dc.disney.converter.CharacterConverter;
import com.dc.disney.dto.CharacterDto;
import com.dc.disney.entity.CartoonCharacter;
import com.dc.disney.repository.CharacterRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
        characterConverter.dtoToEntity(characterDto, character);
        characterRepository.save(character);
    }

    public void editCharacter(Long idCharacter, CharacterDto characterDto) {
        characterRepository.findById(idCharacter)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        CartoonCharacter actualCharacter  = characterRepository.findById(idCharacter).get();
        characterConverter.dtoToEntity(characterDto, actualCharacter);
        characterRepository.save(actualCharacter);
    }

    public void deleteCharacter(Long idCharacter) {
        characterRepository.findById(idCharacter)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        characterRepository.deleteById(idCharacter);
    }

    public CharacterDto getCharacterDetails(Long idCharacter) {
        CartoonCharacter actualCharacter  = characterRepository.findById(idCharacter)
                .orElseThrow(() -> new EntityNotFoundException("Character not found"));
        CharacterDto characterDto = new CharacterDto();
        characterConverter.entityToDto(actualCharacter, characterDto);
        return characterDto;
    }

    public List<Map> getAllCharacters(String name, Integer age, Long idMovie) {
        List<CartoonCharacter> allCharacters = new ArrayList<>();
        if (Objects.isNull(name) && Objects.isNull(age) && Objects.isNull(idMovie)) {
            allCharacters = characterRepository.findAll();
        } else if (Objects.nonNull(name)) {
            allCharacters = characterRepository.findByCharacterName(name);
        } else if (Objects.nonNull(age)) {
            allCharacters = characterRepository.findByAge(age);
        } else if (Objects.nonNull(idMovie)) {
            allCharacters = characterRepository.findByMovie(idMovie);
        }
        List<Map> allCharactersMap = allCharacters.stream()
                .map(character -> {
                    CharacterDto characterDto = new CharacterDto();
                    characterDto.setImage(character.getImage());
                    characterDto.setName(character.getName());

                    Map characterMap = new HashMap();
                    characterMap.put("image", characterDto.getImage());
                    characterMap.put("name", characterDto.getName());
                    return characterMap;
                }).collect(Collectors.toList());
        return allCharactersMap;
    }
}
