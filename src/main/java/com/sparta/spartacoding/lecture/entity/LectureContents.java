package com.sparta.spartacoding.lecture.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lectureContents")
public class LectureContents {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureContentsId;

    @Column(nullable = false)
    private String lectureContentsNum;

    @Column(nullable = false)
    private String lectureContentsTitle;

    @Column(nullable = false)
    private String lectureRunTime;


    @Column(nullable = false)
    private String lectureVideoURL;

    @ManyToOne
    @JoinColumn(name = "Lecture_id")
    private Lecture lecture;
}
