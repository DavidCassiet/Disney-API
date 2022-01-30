package com.dc.disney.service;

import com.dc.disney.dto.UserDto;
import com.dc.disney.entity.User;
import com.dc.disney.repository.UserRepository;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    private UserRepository userRepository;
    private Converter<UserDto, User> userConverter;

    public UserService(UserRepository userRepository, Converter<UserDto, User> userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public void createUser(UserDto userDto) {
        User user = userConverter.convert(userDto);
        userRepository.save(user);
    }
}
