package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Lecture;
import com.sparta.spartacoding.lecture.entity.LectureContents;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String lectureTitle;
    private float lectureAllRunTimeTotal;
    private int lectureTotal;
    private List<LectureContentsResponseDto> lectureContentsList;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.lectureTitle = lecture.getLectureTitle();
        this.lectureTotal = lecture.getLectureContentsList().size();
        this.lectureAllRunTimeTotal = lecture.getLectureContentsList().stream()
                .map(LectureContents::getLectureRunTime)
                .reduce(0.0f, Float::sum);
        this.lectureContentsList = lecture.getLectureContentsList().stream()
                .map(LectureContentsResponseDto::new)
                .toList();
    }
}
