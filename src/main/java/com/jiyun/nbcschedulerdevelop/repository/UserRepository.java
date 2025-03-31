package com.jiyun.nbcschedulerdevelop.repository;

import com.jiyun.nbcschedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}