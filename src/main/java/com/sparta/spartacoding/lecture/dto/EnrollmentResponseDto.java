package com.sparta.spartacoding.lecture.dto;

import lombok.Getter;

@Getter
public class EnrollmentResponseDto {
    private String message;

    public EnrollmentResponseDto(String message){
        this.message = message;
    }
}
