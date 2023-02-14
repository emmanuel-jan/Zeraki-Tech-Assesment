package com.jantech.schoolproject.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jantech.schoolproject.models.Student;

public interface StudentService {
    Student saveStudent(Student Student);
    Student editStudent(Student student, Long id);
    void addCourseToStudent(String studentName, String courseName);
    List<Student>getStudents();
    List<Student>findStudentWithSorting(String name);
    Page<Student>findStudentWithPagination(int offset, int pageSize);
    String deleteStudent(Long id);
}
