package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import com.sparta.spartacoding.user.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;

    public CourseService(CourseRepository courseRepository, EnrollmentRepository enrollmentRepository) {
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
    }


    public List<CourseResponseDto> getMain() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseResponseDto::new).toList();
    }

    public List<CourseAllResponseDto> getAllPage() {
        return courseRepository.findAll().stream().map(CourseAllResponseDto::new).toList();
    }

    public List<MyClassroomResponseDto> getMyPage(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUserId(userId);

        List<MyClassroomResponseDto> myClassroomResponseDtoList = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            MyClassroomResponseDto responseDto = new MyClassroomResponseDto(enrollment, enrollment.getCourse());
            myClassroomResponseDtoList.add(responseDto);
        }
        return myClassroomResponseDtoList;

    }
}
