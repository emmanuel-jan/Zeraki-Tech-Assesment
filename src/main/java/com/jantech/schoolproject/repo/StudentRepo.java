package com.jantech.schoolproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jantech.schoolproject.models.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {
    Student findByName(String name);
}
