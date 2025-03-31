package com.jiyun.nbcschedulerdevelop.dto;

import com.jiyun.nbcschedulerdevelop.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {

    private String username;
    private String name;
    private String email;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
        this.name = user.getName();
        this.email = user.getEmail();
    }
}
