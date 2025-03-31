package com.jiyun.nbcschedulerdevelop.service;

import com.jiyun.nbcschedulerdevelop.dto.ScheduleCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleResponseDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleUpdateDto;
import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import com.jiyun.nbcschedulerdevelop.repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Autowired
    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    public ScheduleResponseDto saveSchedule(ScheduleCreateDto createDto) {
        Schedule savedSchedule = scheduleRepository.save(new Schedule(createDto));
        return new ScheduleResponseDto(savedSchedule);
    }

    public ScheduleResponseDto findScheduleById(Long id) {
        Schedule foundSchedule = scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return new ScheduleResponseDto(foundSchedule);
    }

    @Transactional
    public void updateSchedule(Long id, ScheduleUpdateDto updateDto) {
        Schedule foundSchedule = scheduleRepository.findById(id).orElseThrow(NoSuchElementException::new);
        foundSchedule.update(updateDto);
    }
}
