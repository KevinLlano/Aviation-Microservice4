package com.dev.trainingservice.config;

import com.dev.trainingservice.model.Course;
import com.dev.trainingservice.model.Instructor;
import com.dev.trainingservice.model.Lesson;
import com.dev.trainingservice.repository.CourseRepository;
import com.dev.trainingservice.repository.InstructorRepository;
import com.dev.trainingservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final InstructorRepository instructorRepository;
    private final CourseRepository courseRepository;
    private final LessonRepository lessonRepository;

    @Override
    public void run(String... args) throws Exception {
        if (instructorRepository.count() == 0) {
            loadSampleData();
        }
    }

    private void loadSampleData() {
        // Instructors
        Instructor instructor1 = new Instructor();
        instructor1.setFirstName("John");
        instructor1.setLastName("Smith");
        instructor1.setEmail("john.smith@aviation.com");
        instructor1.setPhone("555-0101");
        instructor1.setSpecialization("Flight Training");
        instructor1.setYearsOfExperience(15);
        instructor1.setCertificationNumber("CFI-12345");
        instructorRepository.save(instructor1);

        Instructor instructor2 = new Instructor();
        instructor2.setFirstName("Sarah");
        instructor2.setLastName("Johnson");
        instructor2.setEmail("sarah.johnson@aviation.com");
        instructor2.setPhone("555-0102");
        instructor2.setSpecialization("Aircraft Maintenance");
        instructor2.setYearsOfExperience(10);
        instructor2.setCertificationNumber("AMT-67890");
        instructorRepository.save(instructor2);

        // Courses
        Course course1 = new Course();
        course1.setTitle("Private Pilot License (PPL)");
        course1.setDescription("Complete ground school and flight training for Private Pilot License");
        course1.setCourseCode("PPL-101");
        course1.setCategory("Flight Training");
        course1.setDurationHours(60);
        course1.setLevel("Beginner");
        course1.setPrice(new BigDecimal("8500.00"));
        course1.setIsActive(true);
        course1.setInstructor(instructor1);
        courseRepository.save(course1);

        Course course2 = new Course();
        course2.setTitle("Aircraft Maintenance Basics");
        course2.setDescription("Introduction to aircraft maintenance procedures and safety");
        course2.setCourseCode("AMT-101");
        course2.setCategory("Maintenance");
        course2.setDurationHours(40);
        course2.setLevel("Beginner");
        course2.setPrice(new BigDecimal("3500.00"));
        course2.setIsActive(true);
        course2.setInstructor(instructor2);
        courseRepository.save(course2);

        // Lessons for Course
        Lesson lesson1 = new Lesson();
        lesson1.setTitle("Introduction to Aviation");
        lesson1.setDescription("Overview of aviation principles and regulations");
        lesson1.setLessonNumber(1);
        lesson1.setDurationMinutes(120);
        lesson1.setType(Lesson.LessonType.THEORY);
        lesson1.setContent("https://materials.aviation.com/ppl/intro");
        lesson1.setIsMandatory(true);
        lesson1.setCourse(course1);
        lessonRepository.save(lesson1);

        Lesson lesson2 = new Lesson();
        lesson2.setTitle("Pre-Flight Inspection");
        lesson2.setDescription("Learn proper pre-flight inspection procedures");
        lesson2.setLessonNumber(2);
        lesson2.setDurationMinutes(90);
        lesson2.setType(Lesson.LessonType.PRACTICAL);
        lesson2.setContent("https://materials.aviation.com/ppl/preflight");
        lesson2.setIsMandatory(true);
        lesson2.setCourse(course1);
        lessonRepository.save(lesson2);

        Lesson lesson3 = new Lesson();
        lesson3.setTitle("First Solo Flight");
        lesson3.setDescription("Your first solo flight experience");
        lesson3.setLessonNumber(3);
        lesson3.setDurationMinutes(60);
        lesson3.setType(Lesson.LessonType.PRACTICAL);
        lesson3.setContent("https://materials.aviation.com/ppl/solo");
        lesson3.setIsMandatory(true);
        lesson3.setCourse(course1);
        lessonRepository.save(lesson3);

        Lesson lesson4 = new Lesson();
        lesson4.setTitle("Safety Procedures");
        lesson4.setDescription("Essential safety procedures for aircraft maintenance");
        lesson4.setLessonNumber(1);
        lesson4.setDurationMinutes(90);
        lesson4.setType(Lesson.LessonType.GROUND_SCHOOL);
        lesson4.setContent("https://materials.aviation.com/amt/safety");
        lesson4.setIsMandatory(true);
        lesson4.setCourse(course2);
        lessonRepository.save(lesson4);

        System.out.println("Sample data loaded successfully!");
    }
}

