package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Getter
@NoArgsConstructor
public class Enrollment extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long enrollmentId;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private int remainingPeriod;

    @Column(nullable = false)
    private float progressRate;

    @Column(nullable = false)
    private boolean isApplying;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "coures_id")
    private Course course;

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
        LocalDate currentDate = LocalDate.now();
        if (course.isCourseFree()) {
            this.dueDate = currentDate.plusDays(3);
        } else {
            this.dueDate = currentDate.plusYears(1);
        }
        this.remainingPeriod = (int) ChronoUnit.DAYS.between(currentDate, this.dueDate);
        this.progressRate = 0.0f;
        this.isApplying = true;
    }
}
