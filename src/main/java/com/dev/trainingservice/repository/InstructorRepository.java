package com.dev.trainingservice.repository;

import com.dev.trainingservice.model.Instructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {

    Optional<Instructor> findByEmail(String email);

    List<Instructor> findBySpecialization(String specialization);

    Optional<Instructor> findByCertificationNumber(String certificationNumber);

    List<Instructor> findByYearsOfExperienceGreaterThanEqual(Integer years);
}

