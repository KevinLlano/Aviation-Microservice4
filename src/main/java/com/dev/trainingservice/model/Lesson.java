package com.dev.trainingservice.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDateTime;

@Entity
@Table(name = "lessons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 2000)
    private String description;

    @Column(name = "lesson_number")
    private Integer lessonNumber;

    @Column(name = "duration_minutes")
    private Integer durationMinutes;

    @Enumerated(EnumType.STRING)
    private LessonType type; // THEORY, PRACTICAL, SIMULATOR, ASSESSMENT

    private String content;

    @Column(name = "is_mandatory")
    private Boolean isMandatory = true;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Many lessons belong to one course
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id", nullable = false)
    @JsonBackReference
    private Course course;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public enum LessonType {
        THEORY, PRACTICAL, SIMULATOR, ASSESSMENT, GROUND_SCHOOL
    }
}

