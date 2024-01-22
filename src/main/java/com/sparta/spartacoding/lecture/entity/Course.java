package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;

    @Column(nullable = false)
    private String courseTitle;

    @Column(nullable = false)
    private String courseTutor;

    @Column(nullable = false)
    private String courseDescription;

    @Column(nullable = false)
    private int coursePrice;

    @Column(nullable = false)
    private String courseImgURL;

    @Column(nullable = false)
    private boolean courseSupport;

    @Column(nullable = false)
    private String courseTag;

    @Column(nullable = false)
    private boolean courseFree;
    
    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollmentList = new ArrayList<>();

}
