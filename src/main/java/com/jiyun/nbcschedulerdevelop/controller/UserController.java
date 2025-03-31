package com.jiyun.nbcschedulerdevelop.controller;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserResponseDto;
import com.jiyun.nbcschedulerdevelop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> saveUser(@RequestBody UserCreateDto createDto) {
        UserResponseDto responseDto = userService.saveUser(createDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
