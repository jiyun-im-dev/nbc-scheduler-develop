package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.dto.ReplyCreateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 연관 관계의 주인은 Reply
    @ManyToOne
    @JoinColumn(name = "username", nullable = false) // username 이라는 외래키 컬럼으로 매핑됨
    private User user; // FK (작성자)

    // 연관 관계의 주인은 Reply
    @ManyToOne
    @JoinColumn(name = "schedule", nullable = false) // schedule 이라는 외래키 컬럼으로 매핑됨
    private Schedule schedule; // FK (일정)

    private String content;

    public Reply(ReplyCreateDto createDto) {
        this.user = createDto.getUser();
        this.schedule = createDto.getSchedule();
        this.content = createDto.getContent();
    }

}
