package com.jiyun.nbcschedulerdevelop.entity;

import com.jiyun.nbcschedulerdevelop.dto.ScheduleCreateDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.generator.EventType;
import org.hibernate.generator.Generator;

import java.sql.Timestamp;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username")
    private User user; // FK

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

}
