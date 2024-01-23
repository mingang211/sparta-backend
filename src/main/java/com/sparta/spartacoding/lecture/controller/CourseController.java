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
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String query) {

        List<CourseAllResponseDto> courses;

        // Check if sortBy parameter is present
        if (sortBy != null && !sortBy.isEmpty()) {
            // Call service method based on sorting criteria
            if ("popular".equals(sortBy)) {
                courses = courseService.getPopularCourses();
            } else if ("free".equals(sortBy)) {
                courses = courseService.getFreeCourses();
            } else if ("government-support".equals(sortBy)) {
                courses = courseService.getGovernmentSupportCourses();
            } else {
                // If an invalid sortBy parameter is provided, return an error or handle it accordingly
                return ResponseEntity.badRequest().build();
            }
        } else if (tag != null && !tag.isEmpty()) {
            // Check if tag parameter is present
            // Call service method based on tagging criteria
            courses = courseService.getTagCourses(tag);
        } else if(query != null && !query.isEmpty()){
            courses = courseService.getQueryCourese(query);
        }
        else {
            // If no sorting or tagging criteria is specified, returns the entire list in latest order.
            courses = courseService.getLatestCourses();
        }

        return ResponseEntity.ok(courses);
    }

}
