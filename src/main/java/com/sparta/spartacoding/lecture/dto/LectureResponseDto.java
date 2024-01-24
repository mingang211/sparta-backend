package com.sparta.spartacoding.lecture.dto;

import com.sparta.spartacoding.lecture.entity.Lecture;
import com.sparta.spartacoding.lecture.entity.LectureContents;
import lombok.Getter;

import java.util.List;

@Getter
public class LectureResponseDto {
    private Long lectureId;
    private String lectureTitle;
    private float lectureAllRunTime;
    private int lectureTotal;
    private List<LectureContentsResponseDto> lectureContentsList;

    public LectureResponseDto(Lecture lecture){
        this.lectureId = lecture.getLectureId();
        this.lectureTitle = lecture.getLectureTitle();
        this.lectureTotal = lecture.getLectureContentsList().size();
        /**
         * lectureContentsList의 lectureRunTime들의 합을 대입해줌
         */
        this.lectureAllRunTime = lecture.getLectureContentsList().stream()
                .map(LectureContents::getLectureRunTime)
                .reduce(0.0f, Float::sum);
        /**
         * 주차별 강의의 포함되어있는 강의들을 불러옴
         */
        this.lectureContentsList = lecture.getLectureContentsList().stream()
                .map(LectureContentsResponseDto::new)
                .toList();
    }
}
