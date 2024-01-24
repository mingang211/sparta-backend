package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.EnrollmentResponseDto;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<EnrollmentResponseDto> createEnrollment (@PathVariable Long userId, @PathVariable Long courseId){
        EnrollmentResponseDto enrollmentResponseDto = enrollmentService.createEnrollment(userId, courseId);
        return ResponseEntity.ok(enrollmentResponseDto);
    }

}
