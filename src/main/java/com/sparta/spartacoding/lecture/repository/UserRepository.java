package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
