package com.dev.trainingservice.repository;

import com.dev.trainingservice.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    Optional<Course> findByCourseCode(String courseCode);

    List<Course> findByCategory(String category);

    List<Course> findByIsActiveTrue();

    List<Course> findByInstructorId(Long instructorId);

    List<Course> findByCategoryAndIsActiveTrue(String category);
}

