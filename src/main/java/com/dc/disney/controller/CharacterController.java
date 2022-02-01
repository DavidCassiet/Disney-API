package com.dc.disney.controller;

import com.dc.disney.dto.CharacterDto;
import com.dc.disney.service.CharacterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/characters")
public class CharacterController {

    private CharacterService characterService;

    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @PostMapping
    public ResponseEntity<?> crateCharacter(@RequestBody @Valid CharacterDto characterDto) {
        characterService.createCharacter(characterDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/{idCharacter}")
    public ResponseEntity<?> editCharacter(@PathVariable("idCharacter") Long idCharacter,
                                           @RequestBody @Valid CharacterDto characterDto) {
        characterService.editCharacter(idCharacter, characterDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{idCharacter}")
    public ResponseEntity<?> deleteCharacter(@PathVariable("idCharacter") Long idCharacter) {
        characterService.deleteCharacter(idCharacter);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

    @GetMapping
    public ResponseEntity<?> getAllCharacters(@RequestParam(name = "name", required = false) String name,
                                              @RequestParam(name = "age", required = false) Integer age,
                                              @RequestParam(name = "movies", required = false) Long idMovie) {
        return new ResponseEntity<>(characterService.getAllCharacters(name, age, idMovie), HttpStatus.OK);
    }

    @GetMapping("/details/{idCharacter}")
    public ResponseEntity<?> getCharacterDetails(@PathVariable("idCharacter") Long idCharacter) {
        return new ResponseEntity<>(characterService.getCharacterDetails(idCharacter), HttpStatus.OK);
    }
}
