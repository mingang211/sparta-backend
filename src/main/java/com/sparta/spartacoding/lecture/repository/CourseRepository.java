package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
   //무료 강의
    List<Course> findByCourseFreeTrue();
    // 최신순
    List<Course> findByOrderByCreatedAtDesc();
    // 국비지원
    List<Course> findByCourseSupportTrue();
    // 인기순
    @Query("SELECT c, COUNT(e) FROM Course c LEFT JOIN c.enrollmentList e WHERE e.isApplying = true GROUP BY c ORDER BY COUNT(e) DESC")
    List<Course> findPopularCourses();
    //인기순이 아닌 강의
    @Query("SELECT c FROM Course c WHERE NOT EXISTS " +
            "(SELECT 1 FROM Enrollment e WHERE e.course = c AND e.isApplying = true)")
    List<Course> findByNonApplyingCourses();
    // tag별 강의
    List<Course> findByCourseTag(String str);
    // keyword로 찾아오기
    List<Course> findByCourseTagContainingOrCourseTitleContainingOrCourseDescriptionContaining(String tag, String title, String description);
}
