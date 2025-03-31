package com.jiyun.nbcschedulerdevelop.controller;

import com.jiyun.nbcschedulerdevelop.dto.ScheduleCreateDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleResponseDto;
import com.jiyun.nbcschedulerdevelop.dto.ScheduleUpdateDto;
import com.jiyun.nbcschedulerdevelop.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @Autowired
    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/new")
    public ResponseEntity<ScheduleResponseDto> saveSchedule(@RequestBody ScheduleCreateDto createDto) {
        ScheduleResponseDto responseDto = scheduleService.saveSchedule(createDto);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {
        ScheduleResponseDto responseDto = scheduleService.findScheduleById(id);
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updateSchedule(
            @PathVariable Long id,
            @RequestBody ScheduleUpdateDto updateDto
    ) {
        scheduleService.updateSchedule(id, updateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
