package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.Course;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    boolean existsByUserAndCourse(User user, Course course);
}
