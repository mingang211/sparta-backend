package com.sparta.spartacoding.lecture.controller;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.service.CourseService;
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

    /**
     * 메인 페이지 불러오는 메서드
     * 무료 강의 들만 불러온다.
     * @return courseId,courseTitle,courseDescription,coursePrice,courseImgURL
     */
    @GetMapping
    public ResponseEntity<List<CourseResponseDto>> getMain() {
        List<CourseResponseDto> courseResponseDto = courseService.getMain();
        return ResponseEntity.ok(courseResponseDto);
    }

    /**
     * 내 강의실을 불러오는 메서드
     * @param userId 로그인 후 유저아이디
     * @return courseId,courseTitle,courseTutor,remainingPeriod,coursePrice,courseImgURL
     */
    @GetMapping("/classroom/{userId}")
    public ResponseEntity<List<MyClassroomResponseDto>> getMyPage(@PathVariable Long userId) {
        List<MyClassroomResponseDto> myClassroomResponseDto = courseService.getMyPage(userId);
        return ResponseEntity.ok(myClassroomResponseDto);
    }

    /**
     * 강의영상이 있는 목록을 불러오는 메서드
     * @param lectureId 주차별 강의 아이디
     * @return lectureId, lectureTitle, lectureAllRunTimeTotal, lectureTotal
     */
    @GetMapping("/enrolleds/{lectureId}/details")
    public ResponseEntity<List<LectureResponseDto>> getDetails(@PathVariable Long lectureId) {
        List<LectureResponseDto> lectureDetailResponseDtoList = courseService.getDetails(lectureId);
        return ResponseEntity.ok(lectureDetailResponseDtoList);
    }

    /**
     * 강의 검색 기능, tag 및 분류별(인기순,최신순,무료,국비지원)로 정렬해주는 메서드
     * @param sortBy 인기순, 무료, 국비지원, 최신순
     * @param tag  새해에는 코딩, 무료, 데이터, 국비지원, AI, 앱게임 개발, 업무자동화, 노코드
     * @param query 태그,타이틀,설명중에 해당 키워드를 담고 있으면 찾아옴
     * @return courseId,courseTitle,courseTitle,courseDescription,coursePrice,courseImgURl,courseSupport,
     * courseTag,courseFree
     */
    @GetMapping("/catalog")
    public ResponseEntity<List<CourseAllResponseDto>> getCourse(
            @RequestParam(required = false) String sortBy,
            @RequestParam(required = false) String tag,
            @RequestParam(required = false) String query) {

        List<CourseAllResponseDto> courses;

        /**
         * 전체 강의에서 인기순, 무료, 국비지원, 최신순으로 코스를 정렬
         */
        if (sortBy != null && !sortBy.isEmpty()) {
            // Call service method based on sorting criteria
            if ("popular".equals(sortBy)) {
                courses = courseService.getPopularCourse();
            } else if ("free".equals(sortBy)) {
                courses = courseService.getFreeCourse();
            } else if ("government-support".equals(sortBy)) {
                courses = courseService.getGovernmentSupportCourse();
            }
            else {
                return ResponseEntity.badRequest().build();
            }
            /**
             * 무료, 새해에는 코딩, 노코드, AI, 데이터, 업무자동화, 국비지원, 앱게임 개발 중 tag와 같은 코스를 가져옴
             */
        } else if (tag != null && !tag.isEmpty()) {
            courses = courseService.getTagCourse(tag);
            /**
             * 코스의 제목,설명,태그 중 해당 키워드가 있으면 불러옴
             */
        } else if(query != null && !query.isEmpty()){
            courses = courseService.getQueryCourse(query);
        }
        /**
         * 전체 강의가 기본적으로 최신순으로 되어있음.
         */
        else {
            courses = courseService.getLatestCourse();
        }

        return ResponseEntity.ok(courses);
    }

}
