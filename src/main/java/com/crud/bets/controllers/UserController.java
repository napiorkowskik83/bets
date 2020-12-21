package com.crud.bets.controllers;

import com.crud.bets.domain.LogInFeedback;
import com.crud.bets.domain.SignUpFeedback;
import com.crud.bets.domain.UserDto;
import com.crud.bets.domain.UserDtoList;
import com.crud.bets.mapper.UserMapper;
import com.crud.bets.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService service;
    private final UserMapper mapper;

    @Autowired
    public UserController(UserService service, UserMapper mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping(value = "/users/{login}/{password}")
    public LogInFeedback logUserIn(@PathVariable String login, @PathVariable String password) {
        return service.logUserIn(login, password);
    }


    @PostMapping(value = "/users")
    public SignUpFeedback signUserUp(@RequestBody UserDto user) {
        return service.signUserUp(mapper.mapToUser(user));
    }

    @GetMapping(value = "/users/{userId}")
    public UserDto getUserById(@PathVariable Long userId) throws UserNotFoundException {
        return mapper.mapToUserDto(service.getUserById(userId));
    }

    @GetMapping(value = "/users")
    public UserDtoList getAllUsers() {
        return new UserDtoList(mapper.mapToUserDtoList(service.getAllUsers()));
    }

    @PutMapping(value = "/users")
    public UserDto updateUser(@RequestBody UserDto user) throws UserNotFoundException {
        return mapper.mapToUserDto(service.updateUser(mapper.mapToUser(user)));
    }

    @PutMapping(value = "/users/{userId}")
    public UserDto updateUserPassword(@PathVariable Long userId, @RequestBody String newPassword) throws UserNotFoundException {
        return mapper.mapToUserDto(service.updateUserPassword(userId, newPassword));
    }

    @GetMapping(value = "/users/check/{login}")
    public Boolean checkIfUserExists(@PathVariable String login) {
        return service.checkIfUserExists(login);
    }

    @DeleteMapping(value = "/users/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        service.deleteUser(userId);
    }
}
