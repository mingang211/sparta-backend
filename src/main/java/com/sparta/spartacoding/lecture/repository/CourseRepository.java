package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByCourseFreeTrue();

    List<Course> findByOrderByCreatedAtDesc();

    List<Course> findByCourseSupportTrue();

}
