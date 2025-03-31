package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.ScheduleCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleResponseDto;
import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto saveSchedule(ScheduleCreateDto createDto) {
        Schedule saved = scheduleRepository.save(new Schedule(createDto));
        return new ScheduleResponseDto(saved);
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule found = scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new ScheduleResponseDto(found);
    }

}
