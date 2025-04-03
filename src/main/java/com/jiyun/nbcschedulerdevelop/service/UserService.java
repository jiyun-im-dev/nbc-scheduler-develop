package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.config.PasswordEncoder;
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
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponseDto saveUser(UserCreateDto createDto) {
        User savedUser = userRepository.save(new User(createDto));
        return new UserResponseDto(savedUser);
    }

    // 암호화된 비밀번호로 회원 가입
    public UserResponseDto register(UserCreateDto createDto) {
        User user = new User(createDto);
        user.encodePassword(passwordEncoder);
        userRepository.save(user);
        return new UserResponseDto(user);
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
        // 사용자 존재하는지 검사
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new LoginException("사용자가 존재하지 않습니다."));

        // 사용자가 입력한 비밀번호(plain text)가 DB 에 저장된 암호화된 비밀번호와 일치하는지 검사
        if (passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            return new LoginResponseDto(user.getUsername());
        } else {
            throw new LoginException("비밀번호가 일치하지 않습니다.");
        }
    }

}
