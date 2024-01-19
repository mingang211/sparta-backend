package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "lecture")
public class Lecture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long lectureId;

    @Column(nullable = false)
    private String lectureTitle;

    @Column(nullable = false)
    private String lectureContents;

    @Column(nullable = false)
    private String lecturePrice;

    @Column(nullable = false)
    private String lectureImgURL;

    @Column(nullable = false)
    private boolean lectureSupport;

    @Column(nullable = false)
    private String lectureTutor;

    @Column(nullable = false)
    private String lectureReview;

    @Column(nullable = false)
    private String lectureProgressRate;

    @Column(nullable = false)
    private String lectureWeek;

    @Column(nullable = false)
    private String lectureVideoURl;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
