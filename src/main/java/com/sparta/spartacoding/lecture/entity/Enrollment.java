package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrollmentId;

    @Column(nullable = false)
    private int dueDate;

    @Column(nullable = false)
    private int remainingPeriod;

    @Column(nullable = false)
    private float ProgressRate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coures_id")
    private Course course;
}
