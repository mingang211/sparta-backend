package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.EnrollmentResponseDto;
import com.sparta.spartacoding.lecture.entity.Course;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import com.sparta.spartacoding.lecture.repository.UserRepository;
import com.sparta.spartacoding.user.User;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentService(EnrollmentRepository enrollmentRepository, UserRepository userRepository, CourseRepository courseRepository) {
        this.enrollmentRepository = enrollmentRepository;
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    public EnrollmentResponseDto createEnrollment(Long userId, Long courseId) {
        User user = userRepository.findById(userId).orElseThrow(
                ()-> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
        Course course = courseRepository.findById(courseId).orElseThrow(
                ()-> new IllegalArgumentException("해당 강의가 존재하지 않습니다."));

        if(enrollmentRepository.existsByUserAndCourse(user, course)){
            throw new IllegalArgumentException("이 강의는 이미 등록되었습니다.");
        }

        Enrollment enrollment = new Enrollment(user, course);

        enrollmentRepository.save(enrollment);

        return new EnrollmentResponseDto("강의가 등록되었습니다.");
    }
}
