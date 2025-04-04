package com.jiyun.nbcschedulerdevelop.controller;

import com.jiyun.nbcschedulerdevelop.dto.ReplyCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ReplyResponseDto;
import com.jiyun.nbcschedulerdevelop.service.ReplyService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReplyController {

    private final ReplyService replyService;

    @Autowired
    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }

    @PostMapping("/{scheduleId}")
    public ResponseEntity<ReplyResponseDto> saveReply(@PathVariable Long scheduleId, ReplyCreateDto createDto) {
        ReplyResponseDto responseDto = replyService.saveReply(scheduleId, createDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
