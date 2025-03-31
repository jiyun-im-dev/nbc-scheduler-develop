package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserResponseDto;
import com.jiyun.nbcschedulerdevelop.entity.User;
import com.jiyun.nbcschedulerdevelop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
