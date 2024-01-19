package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
}
