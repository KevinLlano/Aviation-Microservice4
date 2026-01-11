package com.dev.trainingservice.repository;

import com.dev.trainingservice.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {

    List<Lesson> findByCourseId(Long courseId);

    List<Lesson> findByCourseIdOrderByLessonNumberAsc(Long courseId);

    List<Lesson> findByType(Lesson.LessonType type);

    List<Lesson> findByIsMandatoryTrue();

    List<Lesson> findByCourseIdAndType(Long courseId, Lesson.LessonType type);
}

