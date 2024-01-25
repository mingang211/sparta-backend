package com.sparta.spartacoding.lecture.service;

import com.sparta.spartacoding.lecture.dto.CourseAllResponseDto;
import com.sparta.spartacoding.lecture.dto.CourseResponseDto;
import com.sparta.spartacoding.lecture.dto.LectureResponseDto;
import com.sparta.spartacoding.lecture.dto.MyClassroomResponseDto;
import com.sparta.spartacoding.lecture.entity.Course;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.lecture.repository.CourseRepository;
import com.sparta.spartacoding.lecture.repository.EnrollmentRepository;
import com.sparta.spartacoding.lecture.repository.LectureRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * 메인 페이지에 보여줄 코스를 가져오는 메서드
     * @return courseId,courseTitle,courseDescription,coursePrice,courseImgURL,courseFree
     */
    public List<CourseResponseDto> getMain() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseResponseDto::new).toList();
    }

    /**
     * 강의를 등록해 내 강의실에 있는 코스들을 불러오는 메서드
     * @param userId 회원가입시 후 부여되는 useid
     * @return courseId,courseTitle,courseTutor,courseImgURL,remainingPeriod,progressRate
     */
    public List<MyClassroomResponseDto> getMyPage(Long userId) {
        List<Enrollment> enrollments = enrollmentRepository.findByUser_UserId(userId);

        List<MyClassroomResponseDto> myClassroomResponseDtoList = new ArrayList<>();

        for(Enrollment enrollment: enrollments){
            MyClassroomResponseDto responseDto = new MyClassroomResponseDto(enrollment, enrollment.getCourse());
            myClassroomResponseDtoList.add(responseDto);
        }
        return myClassroomResponseDtoList;

    }

    /**
     * 주차별 강의들을 구성하는 강의영상 URL에 있는 세부 강의를 불러오는 메서드
     * @param lectureId 주차별 강의에 id
     * @return lectureId,lectureTitle,lectureAllRunTime,lectureTotal
     */
    public List<LectureResponseDto> getDetails(Long lectureId) {
        return lectureRepository.findByLectureId(lectureId).stream().map(LectureResponseDto::new).toList();
    }

    /**
     * 전체 강의에서 최신순, 인기순, 무료, 국비지원 키워드별로 가져오기
    *최신순으로 가져오는건 전체 강의 가져오기 기본임
     * @return courseId,courseTitle,courseDescription,coursePrice,courseTag,courseFree
     */
    public List<CourseAllResponseDto> getLatestCourse() {
        // 최신순으로 코스 목록을 가져오는 로직
        return courseRepository.findByOrderByCreatedAtDesc().stream().map(CourseAllResponseDto::new).toList();
    }

    /**
     * 인기순으로 가져오기
     * @return 인기순List +인기순List를 제외한 List
     */
    public List<CourseAllResponseDto> getPopularCourse() {
        /**
         * Enrollment에 isApplying이 true인 코스들 중 true가 가장많은 것부터 내림차순
         */
        List<Course> popularCourses = courseRepository.findPopularCourses();
        /**
         * 위에 있는 코스들을 제외한 코스들
         */
        List<Course> nonApplyingCourses = courseRepository.findByNonApplyingCourses();

        List<CourseAllResponseDto> popularCoursesDto = popularCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
        List<CourseAllResponseDto> nonApplyingCoursesDto = nonApplyingCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());

        /**
         * 위에서 구한 두 코스를 인기순을 먼저 가져오고 그 다음 제외한 코스드들을 가져온다.
         */
        List<CourseAllResponseDto> allCoursesDto = new ArrayList<>();
        allCoursesDto.addAll(popularCoursesDto);
        allCoursesDto.addAll(nonApplyingCoursesDto);

        return allCoursesDto;

    }

    /** courseId,courseTitle,courseDescription,coursePrice,courseTag,courseFree
     * 무료 코스
     * @return
     */
    public List<CourseAllResponseDto> getFreeCourse() {
        return courseRepository.findByCourseFreeTrue().stream().map(CourseAllResponseDto::new).toList();
    }

    /**
     * 국비지원
     * @return courseId,courseTitle,courseDescription,coursePrice,courseTag,courseFree
     */
    public List<CourseAllResponseDto> getGovernmentSupportCourse() {
        return courseRepository.findByCourseSupportTrue().stream().map(CourseAllResponseDto::new).toList();
    }

    /**
     * 태그별
     * @param tag 무료, 새해에는 코딩, 노코드, AI, 데이터, 업무자동화, 국비지원, 앱게임 개발
     * @return courseId,courseTitle,courseDescription,coursePrice,courseTag,courseFree
     */
    public List<CourseAllResponseDto> getTagCourse(String tag) {
        List<Course> course = courseRepository.findByCourseTag(tag);

        return course.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
    }


    public List<CourseAllResponseDto> getQueryCourse(String query) {
        List<Course> matchingCourses = courseRepository.
                findByCourseTagContainingOrCourseTitleContainingOrCourseDescriptionContaining(query, query, query);

        // Convert the Course entities to CourseAllResponseDto
        return matchingCourses.stream()
                .map(CourseAllResponseDto::new)
                .collect(Collectors.toList());
    }
}
