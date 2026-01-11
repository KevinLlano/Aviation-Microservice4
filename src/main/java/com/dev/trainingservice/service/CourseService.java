package com.dev.trainingservice.service;

import com.dev.trainingservice.model.Course;
import com.dev.trainingservice.repository.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class CourseService {

    private final CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Optional<Course> getCourseByCourseCode(String courseCode) {
        return courseRepository.findByCourseCode(courseCode);
    }

    public List<Course> getCoursesByCategory(String category) {
        return courseRepository.findByCategory(category);
    }

    public List<Course> getActiveCourses() {
        return courseRepository.findByIsActiveTrue();
    }

    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepository.findByInstructorId(instructorId);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    public Optional<Course> updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setTitle(courseDetails.getTitle());
                    course.setDescription(courseDetails.getDescription());
                    course.setCourseCode(courseDetails.getCourseCode());
                    course.setCategory(courseDetails.getCategory());
                    course.setDurationHours(courseDetails.getDurationHours());
                    course.setLevel(courseDetails.getLevel());
                    course.setPrice(courseDetails.getPrice());
                    course.setIsActive(courseDetails.getIsActive());
                    course.setInstructor(courseDetails.getInstructor());
                    return courseRepository.save(course);
                });
    }

    public boolean deleteCourse(Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    courseRepository.delete(course);
                    return true;
                })
                .orElse(false);
    }

    public Optional<Course> toggleCourseStatus(Long id) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setIsActive(!course.getIsActive());
                    return courseRepository.save(course);
                });
    }
}

