package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
