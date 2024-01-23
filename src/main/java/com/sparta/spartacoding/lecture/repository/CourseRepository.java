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

    @Query("SELECT c, COUNT(e) FROM Course c LEFT JOIN c.enrollmentList e WHERE e.isApplying = true GROUP BY c ORDER BY COUNT(e) DESC")
    List<Course> findPopularCourses();

    @Query("SELECT c FROM Course c WHERE NOT EXISTS " +
            "(SELECT 1 FROM Enrollment e WHERE e.course = c AND e.isApplying = true)")
    List<Course> findByNonApplyingCourses();

    List<Course> findByCourseTag(String str);
}
