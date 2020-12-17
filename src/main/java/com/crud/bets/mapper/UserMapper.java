package com.crud.bets.mapper;

import com.crud.bets.domain.User;
import com.crud.bets.domain.UserDto;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto){
        return new User(userDto.getId(), userDto.getUsername(), userDto.getEmail(),
                userDto.getPassword(),
                userDto.getRole(), userDto.getCreated(), userDto.getBalance());
    }

    public List<User> mapToUserList(List<UserDto> userDtoList){
        return userDtoList.stream()
                .map(userDto -> mapToUser(userDto))
                .collect(Collectors.toList());
    }

    public UserDto mapToUserDto(User user){
        return new UserDto(user.getId(), user.getUsername(), user.getEmail(), user.getPassword(),
                user.getRole(), user.getCreated(), user.getBalance());
    }

    public List<UserDto> mapToUserDtoList(List<User> userList){
        return userList.stream()
                .map(user -> mapToUserDto(user))
                .collect(Collectors.toList());
    }
}
