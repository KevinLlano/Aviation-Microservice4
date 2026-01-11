package com.dev.trainingservice.service;

import com.dev.trainingservice.model.Instructor;
import com.dev.trainingservice.repository.InstructorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InstructorService {

    private final InstructorRepository instructorRepository;

    public List<Instructor> getAllInstructors() {
        return instructorRepository.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepository.findById(id);
    }

    public Optional<Instructor> getInstructorByEmail(String email) {
        return instructorRepository.findByEmail(email);
    }

    public List<Instructor> getInstructorsBySpecialization(String specialization) {
        return instructorRepository.findBySpecialization(specialization);
    }

    public Instructor createInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Optional<Instructor> updateInstructor(Long id, Instructor instructorDetails) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructor.setFirstName(instructorDetails.getFirstName());
                    instructor.setLastName(instructorDetails.getLastName());
                    instructor.setEmail(instructorDetails.getEmail());
                    instructor.setPhone(instructorDetails.getPhone());
                    instructor.setSpecialization(instructorDetails.getSpecialization());
                    instructor.setYearsOfExperience(instructorDetails.getYearsOfExperience());
                    instructor.setCertificationNumber(instructorDetails.getCertificationNumber());
                    return instructorRepository.save(instructor);
                });
    }

    public boolean deleteInstructor(Long id) {
        return instructorRepository.findById(id)
                .map(instructor -> {
                    instructorRepository.delete(instructor);
                    return true;
                })
                .orElse(false);
    }

    public List<Instructor> getExperiencedInstructors(Integer minYears) {
        return instructorRepository.findByYearsOfExperienceGreaterThanEqual(minYears);
    }
}

