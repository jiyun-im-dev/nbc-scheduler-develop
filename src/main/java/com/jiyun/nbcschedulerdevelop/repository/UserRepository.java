package com.jiyun.nbcschedulerdevelop.repository;

import com.jiyun.nbcschedulerdevelop.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    void deleteByUsername(String username);
    
    Optional<User> findByUsernameAndPassword(String username, String password);

}