package com.jantech.schoolproject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jantech.schoolproject.models.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {
    
    Course findByName(String name);
    Boolean existsByName(String name);

}
