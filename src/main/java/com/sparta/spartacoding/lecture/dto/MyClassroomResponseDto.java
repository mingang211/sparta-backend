package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Course;
import com.sparta.spartacoding.lecture.entity.Enrollment;
import com.sparta.spartacoding.user.User;
import lombok.Getter;

@Getter
public class MyClassroomResponseDto {
    private Long courseId;
    private String courseTitle;
    private String courseTutor;
    private String courseImgURL;
    private int remainingPeriod;
    private float progressRate;

    public MyClassroomResponseDto(Enrollment enrollment, Course course) {
        this.courseId = course.getCourseId();
        this.courseTitle = course.getCourseTitle();
        this.courseTutor = course.getCourseTutor();
        this.courseImgURL = course.getCourseImgURL();
        this.remainingPeriod = enrollment.getRemainingPeriod();
        this.progressRate = enrollment.getProgressRate();
    }
}
