package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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

//    @GetMapping("/catalog")
//    public ResponseEntity<List<CourseAllResponseDto>> getAllPage() {
//        List<CourseAllResponseDto> courseAllResponseDto = courseService.getAllPage();
//        return ResponseEntity.ok(courseAllResponseDto);
//    }

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

    @GetMapping("/catalog")
    public ResponseEntity<List<CourseAllResponseDto>> getCourses(
            @RequestParam(required = false) String sortBy) {

        List<CourseAllResponseDto> courses;

        // 정렬 기준에 따라 서비스 메서드 호출
//        if ("popular".equals(sortBy)) {
//            courses = courseService.getPopularCourses();
//        } else
        if ("free".equals(sortBy)) {
            courses = courseService.getFreeCourses();
        } else if ("government-support".equals(sortBy)) {
            courses = courseService.getGovernmentSupportCourses();
        } else {
            // 정렬 기준이 지정되지 않은 경우 최신순으로 전체 목록을 반환
            courses = courseService.getLatestCourses();
        }

        return ResponseEntity.ok(courses);
    }


}
