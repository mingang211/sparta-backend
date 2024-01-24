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
    private float lectureRunTime;

    @Column(nullable = false, length = 1000)
    private String lectureVideoURL;

    @ManyToOne
    @JoinColumn(name = "Lecture_id")
    private Lecture lecture;

    public LectureContents(LectureContents lectureContents) {
        this.lectureContentsNum = lectureContents.getLectureContentsNum();
        this.lectureContentsTitle = lectureContents.getLectureContentsTitle();
        this.lectureRunTime = lectureContents.getLectureRunTime();
        this.lectureVideoURL = lectureContents.getLectureVideoURL();
        this.lecture = lectureContents.getLecture();
    }
}
