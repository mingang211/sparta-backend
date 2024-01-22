package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.LectureContents;
import lombok.Getter;

@Getter
public class LectureContentsResponseDto {
    private Long lectureContentsId;
    private String lectureContentsTitle;
    private String lectureContentsNum;
    private float lectureRunTime;
    private String lectureVideoUrl;

    public LectureContentsResponseDto(LectureContents lectureContents) {
        this.lectureContentsId = lectureContents.getLectureContentsId();
        this.lectureContentsTitle = lectureContents.getLectureContentsTitle();
        this.lectureContentsNum = lectureContents.getLectureContentsNum();
        this.lectureRunTime = lectureContents.getLectureRunTime();
        this.lectureVideoUrl = lectureContents.getLectureVideoURL();
    }
}
