package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.entity.Course;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import com.sparta.spartacoding.lecture.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final LectureRepository lectureRepository;




    public CourseService(CourseRepository courseRepository,
                         EnrollmentRepository enrollmentRepository, LectureRepository lectureRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.lectureRepository = lectureRepository;
    }


    public List<CourseResponseDto> getMain() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseResponseDto::new).toList();
    }


    public List<MyClassroomResponseDto> getMyPage(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUser_UserId(userId);

        List<MyClassroomResponseDto> myClassroomResponseDtoList = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            MyClassroomResponseDto responseDto = new MyClassroomResponseDto(enrollment, enrollment.getCourse());
            myClassroomResponseDtoList.add(responseDto);
        }
        return myClassroomResponseDtoList;

    }


    public List<LectureResponseDto> getDetails(Long lectureId) {
        return lectureRepository.findByLectureId(lectureId).stream().map(LectureResponseDto::new).toList();
    }
    //전체 강의에서 최신순, 인기순, 무료, 국비지원 키워드별로 가져오기
    //최신순으로 가져오는건 전체 강의 가져오기 기본임
    public List<CourseAllResponseDto> getLatestCourses() {
        // 최신순으로 코스 목록을 가져오는 로직
        return courseRepository.findByOrderByCreatedAtDesc().stream().map(CourseAllResponseDto::new).toList();
    }
    //인기순으로 가져오기
    public List<CourseAllResponseDto> getPopularCourses() {
        List<Course> popularCourses = courseRepository.findPopularCourses();
        List<Course> nonApplyingCourses = courseRepository.findByNonApplyingCourses();

        // Convert the Course entities to CourseAllResponseDto
        List<CourseAllResponseDto> popularCoursesDto = popularCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());

        List<CourseAllResponseDto> nonApplyingCoursesDto = nonApplyingCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());

        // Concatenate the two lists
        List<CourseAllResponseDto> allCoursesDto = new ArrayList<>();
        allCoursesDto.addAll(popularCoursesDto);
        allCoursesDto.addAll(nonApplyingCoursesDto);

        return allCoursesDto;

    }
    // 무료 코스 가져오기
    public List<CourseAllResponseDto> getFreeCourses() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseAllResponseDto::new).toList();
    }
    // 국비지원 코스 가져오기
    public List<CourseAllResponseDto> getGovernmentSupportCourses() {
        return courseRepository.findByCourseSupportTrue().stream().map(CourseAllResponseDto::new).toList();
    }

    //태그별
    public List<CourseAllResponseDto> getTagCourses(String tag) {
        List<Course> newYearCourses = courseRepository.findByCourseTag(tag);

        return newYearCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
    }


    public List<CourseAllResponseDto> getQueryCourese(String query) {
        List<Course> matchingCourses = courseRepository.
                findByCourseTagContainingOrCourseTitleContainingOrCourseDescriptionContaining(query, query, query);

        // Convert the Course entities to CourseAllResponseDto
        return matchingCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
    }
}
