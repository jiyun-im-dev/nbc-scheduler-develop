package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String username; // PK

    private String name;

    @Email
    private String email;

    private String password;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Schedule> schedules = new ArrayList<>();

    public User(UserCreateDto createDto) {
        this.username = createDto.getUsername();
        this.name = createDto.getName();
        this.email = createDto.getEmail();
        this.password = createDto.getPassword();
    }

    public void update(UserUpdateDto updateDto) {
        this.name = updateDto.getName();
        this.email = updateDto.getEmail();
        this.password = updateDto.getPassword();
    }

}
