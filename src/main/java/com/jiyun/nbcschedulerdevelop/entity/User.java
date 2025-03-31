package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import jakarta.persistence.*;
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

}
