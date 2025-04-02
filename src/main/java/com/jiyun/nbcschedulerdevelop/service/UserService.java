package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.*;
import com.jiyun.nbcschedulerdevelop.entity.User;
import com.jiyun.nbcschedulerdevelop.exception.LoginException;
import com.jiyun.nbcschedulerdevelop.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto saveUser(UserCreateDto createDto) {
        User savedUser = userRepository.save(new User(createDto));
        return new UserResponseDto(savedUser);
    }

    public UserResponseDto findUserByUsername(String username) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        return new UserResponseDto(foundUser);
    }

    @Transactional
    public void updateUser(String username, UserUpdateDto updateDto) {
        User foundUser = userRepository.findByUsername(username).orElseThrow(NoSuchElementException::new);
        foundUser.update(updateDto);
    }

    @Transactional
    public void deleteUser(String username) throws NoSuchElementException {
        userRepository.deleteByUsername(username);
    }

    public LoginResponseDto login(@Valid LoginRequestDto requestDto) {
        User user = userRepository.findByUsernameAndPassword(requestDto.getUsername(), requestDto.getPassword())
                .orElseThrow(LoginException::new);
        return new LoginResponseDto(user.getUsername());
    }

}
