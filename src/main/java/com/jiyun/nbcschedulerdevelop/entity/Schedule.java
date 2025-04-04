package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.dto.ScheduleCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleUpdateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Automatically created by database

    private String title;

    private String content;

    // 연관 관계의 주인은 Schedule
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username") // username 이라는 외래키 컬럼으로 매핑됨
    private User user; // FK (작성자)


    @Generated(GenerationTime.INSERT)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt; // Both date and time

    @Generated(GenerationTime.UPDATE)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime updatedAt; // Both date and time

    public Schedule(ScheduleCreateDto createDto) {
        this.title = createDto.getTitle();
        this.content = createDto.getContent();
        this.user = createDto.getUser();
    }

    public void update(ScheduleUpdateDto updateDto) {
        this.title = updateDto.getTitle();
        this.content = updateDto.getContent();
    }

}
