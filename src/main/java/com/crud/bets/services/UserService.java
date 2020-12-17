package com.crud.bets.services;

import com.crud.bets.controllers.UserNotFoundException;
import com.crud.bets.domain.*;
import com.crud.bets.mapper.UserMapper;
import com.crud.bets.repositories.LogInAttemptRepository;
import com.crud.bets.repositories.UserBalanceChangeRepository;
import com.crud.bets.repositories.UserDataChangeRepository;
import com.crud.bets.repositories.UserRepository;
import org.jasypt.util.password.StrongPasswordEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDataChangeRepository dataChangeRepository;
    private final UserBalanceChangeRepository balanceChangeRepository;
    private final LogInAttemptRepository logInAttemptRepository;
    private final UserMapper mapper;
    private final StrongPasswordEncryptor encryptor;
    private final BetsReviewer betsReviewer;

    @Autowired
    public UserService(UserRepository userRepository, UserDataChangeRepository dataChangeRepository,
                       UserBalanceChangeRepository balanceChangeRepository, LogInAttemptRepository logInAttemptRepository,
                       UserMapper mapper, StrongPasswordEncryptor encryptor, BetsReviewer betsReviewer) {
        this.userRepository = userRepository;
        this.dataChangeRepository = dataChangeRepository;
        this.balanceChangeRepository = balanceChangeRepository;
        this.logInAttemptRepository = logInAttemptRepository;
        this.mapper = mapper;
        this.encryptor = encryptor;
        this.betsReviewer = betsReviewer;
    }

    public LogInFeedback logUserIn(String login, String password) {

        if (userRepository.findByUsername(login).isPresent()) {
            User user = userRepository.findByUsername(login).get();
            if (encryptor.checkPassword(password, user.getPassword())) {
                betsReviewer.updateBetsStatus(user.getId());
                user = userRepository.findByUsername(login).get();
                logInAttemptRepository.save(new LogInAttempt(user, login, true));
                return new LogInFeedback(mapper.mapToUserDto(user), "User is logged in.");
            } else {
                logInAttemptRepository.save(new LogInAttempt(user, login, false));
                return new LogInFeedback(null, "Wrong user password!");
            }
        } else if (userRepository.findByEmail(login).isPresent()) {
            User user = userRepository.findByEmail(login).get();
            if (encryptor.checkPassword(password, user.getPassword())) {
                betsReviewer.updateBetsStatus(user.getId());
                user = userRepository.findByEmail(login).get();
                logInAttemptRepository.save(new LogInAttempt(user, login, true));
                return new LogInFeedback(mapper.mapToUserDto(user), "User is logged in.");
            } else {
                logInAttemptRepository.save(new LogInAttempt(user, login, false));
                return new LogInFeedback(null, "Wrong user password!");
            }
        } else {
            return new LogInFeedback(null, "User with pointed login does not exist!");
        }
    }

    public SignUpFeedback signUserUp(User user) {
        String username = user.getUsername();
        String email = user.getEmail();
        if (userRepository.findByUsername(username).isPresent()) {
            return new SignUpFeedback(null, "User with pointed username already exists!");
        } else if (userRepository.findByEmail(email).isPresent()) {
            return new SignUpFeedback(null, "User with pointed email already exists!");
        } else {
            user.setPassword(encryptor.encryptPassword(user.getPassword()));
            if (user.getUsername().equals("Admin")){
                user.setRole(Role.ADMIN);
            }
            UserDto createdUser = mapper.mapToUserDto(userRepository.save(user));
            return new SignUpFeedback(createdUser, "Sign Up successful");
        }
    }

    public User updateUser(User updatedUser) throws UserNotFoundException{
        User oldUser = userRepository.findById(updatedUser.getId()).orElseThrow(UserNotFoundException::new);
        if(!updatedUser.getUsername().equals(oldUser.getUsername())){
            String oldUsername = oldUser.getUsername();
            dataChangeRepository.save(
                    new UserDataChange(updatedUser, ChangedValue.USERNAME, oldUsername, updatedUser.getUsername()));
        } else if(!updatedUser.getEmail().equals(oldUser.getEmail())){
            String oldEmail = oldUser.getEmail();
            dataChangeRepository.save(
                    new UserDataChange(updatedUser, ChangedValue.EMAIL, oldEmail, updatedUser.getEmail()));
        }
        else if(!updatedUser.getBalance().equals(oldUser.getBalance())){
            BigDecimal oldBalance = oldUser.getBalance();
            balanceChangeRepository.save(new UserBalanceChange(updatedUser, oldBalance, updatedUser.getBalance()));
        }
        return userRepository.save(updatedUser);
    }

    public User updateUserPassword(Long userId, String newPassword) throws UserNotFoundException {
        User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
        String oldPassword = user.getPassword();
        user.setPassword(encryptor.encryptPassword(newPassword));
        dataChangeRepository.save(new UserDataChange(user, ChangedValue.PASSWORD, oldPassword, user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserById(Long userId) throws UserNotFoundException {
        return userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public Boolean checkIfUserExists(String login){
        if(userRepository.findByUsername(login).isPresent()){
            return true;
        }else if (userRepository.findByEmail(login).isPresent()){
            return true;
        }
        return false;
    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }
}
