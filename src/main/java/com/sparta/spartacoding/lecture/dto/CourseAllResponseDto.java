package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Course;
import lombok.Getter;

@Getter
public class CourseAllResponseDto {
    private Long courseId;
    private String courseTitle;
    private String courseDescription;
    private int coursePrice;
    private String courseImgURL;
    private boolean courseSupport;
    private String courseTag;
    private boolean courseFree;

    public CourseAllResponseDto (Course course) {
        this.courseId = course.getCourseId();
        this.courseTitle = course.getCourseTitle();
        this.courseDescription = course.getCourseDescription();
        this.coursePrice = course.getCoursePrice();
        this.courseFree = course.isCourseFree();
        this.courseSupport = course.isCourseSupport();
        this.courseTag = course.getCourseTag();
        this.courseImgURL = course.getCourseImgURL();
    }

}
