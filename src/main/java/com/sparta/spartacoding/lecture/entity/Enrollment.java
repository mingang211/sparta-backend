package com.sparta.spartacoding.lecture.entity;

import com.sparta.spartacoding.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * 유저와 강의가 다대다 연관관계여서 이 엔티티가 중간 엔티티 역할
 */

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
    @JoinColumn(name = "course_id")
    private Course course;

    public Enrollment(User user, Course course) {
        this.user = user;
        this.course = course;
        /**
         * 무료강의는 기간이 3일이고 다른 강의들은 1년으로 마감날을 정함
         */
        LocalDate currentDate = LocalDate.now();
        if (course.isCourseFree()) {
            this.dueDate = currentDate.plusDays(3);
        } else {
            this.dueDate = currentDate.plusYears(1);
        }
        /**
         * 현재날짜와 마감 날짜를 사이의 값
         */
        this.remainingPeriod = (int) ChronoUnit.DAYS.between(currentDate, this.dueDate);
        this.progressRate = 0.0f;
        this.isApplying = true;
    }
}
