package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    Optional<Lecture> findByLectureId(Long lectureId);
}
