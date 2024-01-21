package com.sparta.spartacoding.lecture.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

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
    private String lectureAllRunTime;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.PERSIST, orphanRemoval = true)
    private List<LectureContents>  lectureContentsList= new ArrayList<>();


}
