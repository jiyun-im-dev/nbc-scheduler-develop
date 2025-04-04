package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.config.PasswordEncoder;
import com.jiyun.nbcschedulerdevelop.dto.UserCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.UserUpdateDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    // 연관 관계의 주인은 Schedule
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Schedule 엔터티의 user 필드를 통해 관계를 설정함
    private List<Schedule> schedules = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL) // Reply 엔터티의 user 필드를 통해 관계를 설정함
    private List<Reply> replies = new ArrayList<>();

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
