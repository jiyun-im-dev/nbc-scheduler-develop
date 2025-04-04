package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.ReplyCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ReplyResponseDto;
import com.jiyun.nbcschedulerdevelop.entity.Reply;
import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.repository.ReplyRepository;
import com.jiyun.nbcschedulerdevelop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ReplyService {

    private final ReplyRepository replyRepository;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ReplyService(ReplyRepository replyRepository, ScheduleRepository scheduleRepository) {
        this.replyRepository = replyRepository;
        this.scheduleRepository = scheduleRepository;
    }

    public ReplyResponseDto saveReply(Long scheduleId, ReplyCreateDto createDto) {
        // 댓글이 어느 일정에 달렸는지 외래키 설정
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new NoSuchElementException("일정이 존재하지 않습니다."));
        createDto.setSchedule(schedule);

        // 저장 로직 실행
        Reply savedReply = replyRepository.save(new Reply(createDto));
        return new ReplyResponseDto(savedReply);
    }

}
