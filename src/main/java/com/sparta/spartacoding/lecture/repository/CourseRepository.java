package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {

 /**
  * 무료순 정렬
  * @return  courseFree가 ture인 코스 List
  */
 List<Course> findByCourseFreeTrue();

 /**
  * 최신순 정렬
  * @return 생성날짜을 기준으로하여 내림차순 정렬
  */
 List<Course> findByOrderByCreatedAtDesc();

 /**
  * 국비지원
  * @return 국비지원이 true인 코스들 리스트
  */
 List<Course> findByCourseSupportTrue();

 /**
  * 인기순
  * @return Enrollment에 isApplying이 true인 코스들을 true의 갯수를 기준으로 내림차순 정렬
  */
 @Query("SELECT c, COUNT(e) FROM Course c LEFT JOIN c.enrollmentList e WHERE e.isApplying = true GROUP BY c ORDER BY COUNT(e) DESC")
    List<Course> findPopularCourses();

 /**
  * Enrollment isApplying이 true가 아닌 강의들
  * @return Enrollment isApplying이 true가 아닌 코스들 List
  */
 @Query("SELECT c FROM Course c WHERE NOT EXISTS " +
            "(SELECT 1 FROM Enrollment e WHERE e.course = c AND e.isApplying = true)")
    List<Course> findByNonApplyingCourses();

 /**
  * 태그별 강의
  * @param str 무료, 새해에는 코딩, 노코드, AI, 데이터, 업무자동화, 국비지원, 앱게임 개발
  * @return 선택된 tag를 포함하는 코스들의 List
  */
 List<Course> findByCourseTag(String str);

 /**
  *
  * @param tag 무료, 새해에는 코딩, 노코드, AI, 데이터, 업무자동화, 국비지원, 앱게임 개발
  * @param title courseTitle
  * @param description courseDescription
  * @return tag,title,description 셋 중 하나라도 해당 키워드가 포함 되어있는 List
  */
 List<Course> findByCourseTagContainingOrCourseTitleContainingOrCourseDescriptionContaining(String tag,
                                                                                            String title,
                                                                                            String description);
}
