package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.config.PasswordEncoder;
import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserUpdateDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User {

    @Id
    private String username; // PK

    @Length(min = 2, max = 10)
    private String name;

    @Email
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")
    private String email;

    @Size(min = 8, max = 60) // Bcrypt 암호화 사용
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

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(password);
    }

}
