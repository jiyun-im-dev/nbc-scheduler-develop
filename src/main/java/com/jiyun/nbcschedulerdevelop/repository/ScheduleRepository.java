package com.jiyun.nbcschedulerdevelop.repository;

import com.jiyun.nbcschedulerdevelop.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}