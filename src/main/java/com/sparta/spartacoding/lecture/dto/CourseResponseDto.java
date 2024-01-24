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
    private String courseFree;

    public CourseResponseDto (Course course) {
        this.courseId = course.getCourseId();
        this.courseTitle = course.getCourseTitle();
        this.courseDescription = course.getCourseDescription();
        this.coursePrice = course.getCoursePrice();
        this.courseImgURL = course.getCourseImgURL();
        /**
         * front에서 true면 무료로, false면 ""빈 값으로 받고 싶어함.
         */
        if(course.isCourseFree() == true){
            this.courseFree = "무료";
        }else {
            this.courseFree = "";
        }
    }
}
