package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.EnrollmentResponseDto;
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

    /**
     * 내 강의실에 코스를 등록하는 메서드
     * @param userId 로그인 후 유저아이디
     * @param courseId 등록하려는 코스아이디
     * @return 등록 여부를 리턴 해줌
     */
    @PostMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<EnrollmentResponseDto> createEnrollment (@PathVariable Long userId, @PathVariable Long courseId){
        EnrollmentResponseDto enrollmentResponseDto = enrollmentService.createEnrollment(userId, courseId);
        return ResponseEntity.ok(enrollmentResponseDto);
    }

}
