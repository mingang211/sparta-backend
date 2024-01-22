package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }


    @GetMapping("/")
    public ResponseEntity<List<CourseResponseDto>> getMain() {
        List<CourseResponseDto> courseResponseDto = courseService.getMain();
        return ResponseEntity.ok(courseResponseDto);
    }

    @GetMapping("/catalog")
    public ResponseEntity<List<CourseAllResponseDto>> getAllPage() {
        List<CourseAllResponseDto> courseAllResponseDto = courseService.getAllPage();
        return ResponseEntity.ok(courseAllResponseDto);
    }

    @GetMapping("/classroom/{userId}")
    public ResponseEntity<List<MyClassroomResponseDto>> getMyPage(@PathVariable Long userId) {
        List<MyClassroomResponseDto> myClassroomResponseDto = courseService.getMyPage(userId);
        return ResponseEntity.ok(myClassroomResponseDto);
    }

    @GetMapping("/enrolleds/{lectureId}/details")
    public ResponseEntity<List<LectureResponseDto>> getDetails(@PathVariable Long lectureId) {
        List<LectureResponseDto> lectureDetailResponseDtoList = courseService.getDetails(lectureId);
        return ResponseEntity.ok(lectureDetailResponseDtoList);
    }


}
