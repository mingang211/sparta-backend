package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.service.CourseService;
import com.sparta.spartacoding.lecture.service.EnrollmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/")
    public ResponseEntity<List<CourseResponseDto>> getMain () {
        List<CourseResponseDto> courseResponseDto =  courseService.getMain();
        return ResponseEntity.ok(courseResponseDto);
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<CourseAllResponseDto>> getAllPage () {
        List<CourseAllResponseDto> courseAllResponseDto =  courseService.getAllPage();
        return ResponseEntity.ok(courseAllResponseDto);
    }


}
