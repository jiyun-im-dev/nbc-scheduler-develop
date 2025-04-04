package com.jiyun.nbcschedulerdevelop.dto;

import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyCreateDto {

    private User user;
    private Schedule schedule;
    private String content;

}
