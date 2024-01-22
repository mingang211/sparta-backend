package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Lecture;
import com.sparta.spartacoding.lecture.entity.LectureContents;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String lectureTitle;
    private String lectureAllRunTime;
    private int lectureTotal;
    private List<LectureContentsResponseDto> lectureContentsList;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.lectureTitle = lecture.getLectureTitle();
        this.lectureAllRunTime = lecture.getLectureAllRunTime();
        this.lectureTotal = lecture.getLectureContentsList().size();
        this.lectureContentsList = lecture.getLectureContentsList().stream()
                .map(LectureContentsResponseDto::new)
                .toList();
    }
}
