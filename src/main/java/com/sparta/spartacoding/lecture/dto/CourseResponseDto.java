package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Course;
import lombok.Getter;

@Getter
public class CourseResponseDto {

    private Long courseId;
    private String courseTitle;
    private String courseDescription;
    private int coursePrice;
    private String courseImgURL;
    private boolean courseFree;

    public CourseResponseDto (Course course) {
        this.courseId = course.getCourseId();
        this.courseTitle = course.getCourseTitle();
        this.courseDescription = course.getCourseDescription();
        this.coursePrice = course.getCoursePrice();
        this.courseFree = course.isCourseFree();
        this.courseImgURL = course.getCourseImgURL();
    }
}
