package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.entity.Lecture;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import com.sparta.spartacoding.lecture.repository.LectureContentsRepository;
import com.sparta.spartacoding.lecture.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public List<CourseAllResponseDto> getAllPage() {
        return courseRepository.findAll().stream().map(CourseAllResponseDto::new).toList();
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
}
