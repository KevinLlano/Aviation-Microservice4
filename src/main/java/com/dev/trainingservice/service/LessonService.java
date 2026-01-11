package com.dev.trainingservice.service;

import com.dev.trainingservice.model.Lesson;
import com.dev.trainingservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class LessonService {

    private final LessonRepository lessonRepository;

    public List<Lesson> getAllLessons() {
        return lessonRepository.findAll();
    }

    public Optional<Lesson> getLessonById(Long id) {
        return lessonRepository.findById(id);
    }

    public List<Lesson> getLessonsByCourse(Long courseId) {
        return lessonRepository.findByCourseIdOrderByLessonNumberAsc(courseId);
    }

    public List<Lesson> getLessonsByType(Lesson.LessonType type) {
        return lessonRepository.findByType(type);
    }

    public List<Lesson> getMandatoryLessons() {
        return lessonRepository.findByIsMandatoryTrue();
    }

    public Lesson createLesson(Lesson lesson) {
        return lessonRepository.save(lesson);
    }

    public Optional<Lesson> updateLesson(Long id, Lesson lessonDetails) {
        return lessonRepository.findById(id)
                .map(lesson -> {
                    lesson.setTitle(lessonDetails.getTitle());
                    lesson.setDescription(lessonDetails.getDescription());
                    lesson.setLessonNumber(lessonDetails.getLessonNumber());
                    lesson.setDurationMinutes(lessonDetails.getDurationMinutes());
                    lesson.setType(lessonDetails.getType());
                    lesson.setContent(lessonDetails.getContent());
                    lesson.setIsMandatory(lessonDetails.getIsMandatory());
                    lesson.setCourse(lessonDetails.getCourse());
                    return lessonRepository.save(lesson);
                });
    }

    public boolean deleteLesson(Long id) {
        return lessonRepository.findById(id)
                .map(lesson -> {
                    lessonRepository.delete(lesson);
                    return true;
                })
                .orElse(false);
    }
}

