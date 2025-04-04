package com.jiyun.nbcschedulerdevelop.dto;

import com.jiyun.nbcschedulerdevelop.entity.Reply;
import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.entity.User;
import jakarta.persistence.*;
import lombok.Getter;

@Getter
public class ReplyResponseDto {

    private Long id;
    private User user;
    private Schedule schedule;
    private String content;

    public ReplyResponseDto(Reply reply) {
        this.id = reply.getId();
        this.user = reply.getUser();
        this.schedule = reply.getSchedule();
        this.content = reply.getContent();
    }

}
