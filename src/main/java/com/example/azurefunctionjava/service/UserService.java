package com.example.azurefunctionjava.service;

import com.example.azurefunctionjava.dto.UserCommand;
import com.example.azurefunctionjava.dto.UserDto;
import com.example.azurefunctionjava.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public UserDto getUser(Long id) {
        return new UserDto(
                id,
                "User " + id
        );
    }

    public UserDto saveUser(UserCommand userCommand) {
        User user = new User(userCommand.name());
        return new UserDto(
                user.getId(),
                user.getName()
        );
    }

}
