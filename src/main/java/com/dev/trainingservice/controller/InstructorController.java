package com.dev.trainingservice.controller;

import com.dev.trainingservice.model.Instructor;
import com.dev.trainingservice.service.InstructorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/instructors")
@RequiredArgsConstructor
public class InstructorController {

    private final InstructorService instructorService;

    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        return ResponseEntity.ok(instructorService.getAllInstructors());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        return instructorService.getInstructorById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Instructor> getInstructorByEmail(@PathVariable String email) {
        return instructorService.getInstructorByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/specialization/{specialization}")
    public ResponseEntity<List<Instructor>> getInstructorsBySpecialization(@PathVariable String specialization) {
        return ResponseEntity.ok(instructorService.getInstructorsBySpecialization(specialization));
    }

    @GetMapping("/experienced")
    public ResponseEntity<List<Instructor>> getExperiencedInstructors(@RequestParam(defaultValue = "5") Integer minYears) {
        return ResponseEntity.ok(instructorService.getExperiencedInstructors(minYears));
    }

    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        Instructor created = instructorService.createInstructor(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id, @RequestBody Instructor instructor) {
        return instructorService.updateInstructor(id, instructor)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        return instructorService.deleteInstructor(id)
                ? ResponseEntity.noContent().build()
                : ResponseEntity.notFound().build();
    }
}

