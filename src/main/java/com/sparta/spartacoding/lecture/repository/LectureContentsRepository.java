package com.sparta.spartacoding.lecture.repository;

import com.sparta.spartacoding.lecture.entity.LectureContents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureContentsRepository extends JpaRepository<LectureContents, Long> {
}
