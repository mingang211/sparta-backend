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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
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

    // 전체강의 조회가 최신순으로 정렬되어있음 01/22
    // 이 메서드는 Test용 수정해야함
//    public List<CourseAllResponseDto> getAllPage() {
//        return courseRepository.findAll().stream().map(CourseAllResponseDto::new).toList();
//    }

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

    public List<CourseAllResponseDto> getLatestCourses() {
        // 최신순으로 코스 목록을 가져오는 로직
        return courseRepository.findByOrderByCreatedAtDesc().stream().map(CourseAllResponseDto::new).toList();
    }



//    public List<CourseAllResponseDto> getPopularCourses() {
//
//    }

    public List<CourseAllResponseDto> getFreeCourses() {
        // 무료 코스 목록을 가져오는 로직
        return courseRepository.findByCourseFreeTrue().stream().map(CourseAllResponseDto::new).toList();
    }

    public List<CourseAllResponseDto> getGovernmentSupportCourses() {
        // 국비지원이 있는 코스 목록을 가져오는 로직
        return courseRepository.findByCourseSupportTrue().stream().map(CourseAllResponseDto::new).toList();
    }

}
