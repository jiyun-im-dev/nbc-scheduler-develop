package com.jiyun.nbcschedulerdevelop.dto;

import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.entity.User;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {

    private Long id;

    private String title;

    private String content;

    private User user;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public ScheduleResponseDto(Schedule schedule) {
        this.id = schedule.getId();
        this.title = schedule.getTitle();
        this.content = schedule.getContent();
        this.user = schedule.getUser();
        this.createdAt = schedule.getCreatedAt();
        this.updatedAt = schedule.getUpdatedAt();
    }

}
