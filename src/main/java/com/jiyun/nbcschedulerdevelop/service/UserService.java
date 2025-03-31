package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserResponseDto;
import com.jiyun.nbcschedulerdevelop.entity.User;
import com.jiyun.nbcschedulerdevelop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDto saveUser(UserCreateDto createDto) {
        User saved = userRepository.save(new User(createDto));
        return new UserResponseDto(saved);
    }

}
