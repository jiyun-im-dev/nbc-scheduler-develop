package com.jiyun.nbcschedulerdevelop.controller;

import com.jiyun.nbcschedulerdevelop.dto.*;
import com.jiyun.nbcschedulerdevelop.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserCreateDto createDto) {
        UserResponseDto responseDto = userService.register(createDto); // 암호화된 비밀번호로 회원 가입
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/users/{username}")
    public ResponseEntity<UserResponseDto> findUserById(@PathVariable String username) {
        UserResponseDto responseDto = userService.findUserByUsername(username);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/users/{username}")
    public ResponseEntity<Void> updateUser(
            @PathVariable String username,
            @RequestBody UserUpdateDto updateDto
    ) {
        userService.updateUser(username, updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{username}")
    public ResponseEntity<Void> deleteUser(@PathVariable String username) {
        userService.deleteUser(username);
        return new ResponseEntity<>(HttpStatus.SEE_OTHER);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody LoginRequestDto requestDto,
            HttpServletResponse response
    ) {
        // 로그인 유저 조회
        LoginResponseDto responseDto = userService.login(requestDto);

        // 쿠키에 username 값을 추가
        Cookie cookie = new Cookie("username", String.valueOf(responseDto.getUsername()));

        response.addCookie(cookie);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
