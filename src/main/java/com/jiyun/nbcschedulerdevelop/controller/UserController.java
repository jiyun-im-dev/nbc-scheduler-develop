package com.jiyun.nbcschedulerdevelop.controller;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserResponseDto;
import com.jiyun.nbcschedulerdevelop.dto.UserUpdateDto;
import com.jiyun.nbcschedulerdevelop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

}
