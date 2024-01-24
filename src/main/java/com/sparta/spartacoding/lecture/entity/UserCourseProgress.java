package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class UserCourseProgress {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long userCourseProgressId;

    @Column(nullable = false)
    private boolean isLectureComplete;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "lectureContensts_id")
    private LectureContents lectureContents;
}
