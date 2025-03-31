package com.jiyun.nbcschedulerdevelop.dto;

import com.jiyun.nbcschedulerdevelop.entity.User;
import lombok.Getter;

@Getter
public class ScheduleCreateDto {

    private String title;

    private String content;

    private User user; // FK

}
