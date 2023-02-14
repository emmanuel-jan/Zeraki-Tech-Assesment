package com.jantech.schoolproject.service;

import java.util.List;

import com.jantech.schoolproject.models.Course;
import com.jantech.schoolproject.models.Institution;

public interface InstitutionService {
    Institution saveInstitution(Institution institution);
    Institution editInstitution(Institution institution, Long id);
    Course editCourse(Course course, Long id);
    String deleteInstitution(Long id);
    String deleteCourse(Long id);
    Course saveCourse(Course course);
    void addCourseToInstitution(String institutionName, String courseName);
    Institution getInstitution(String name);
    List<Institution>getInstitutions();
    List<Course>getCourses();
}
