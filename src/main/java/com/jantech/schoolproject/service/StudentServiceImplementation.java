package com.jantech.schoolproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.jantech.schoolproject.exception.AlreadyExistsException;
import com.jantech.schoolproject.models.Course;
import com.jantech.schoolproject.models.Student;
import com.jantech.schoolproject.repo.CourseRepo;
import com.jantech.schoolproject.repo.StudentRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class StudentServiceImplementation implements StudentService {

    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Override
    public Student saveStudent(Student student) {
        log.info("Saving new student {} to the database", student.getName());
        return studentRepo.save(student);
    }

    @Override
    public void addCourseToStudent(String studentName, String courseName) {
        log.info("Saving course {} to the student {}", courseName, studentName);
        Student student = studentRepo.findByName(studentName);
        Course course = courseRepo.findByName(courseName);
        student.getCourses().add(course);
    }

    @Override
    public List<Student> getStudents() {
        log.info("Fetching all students");
        return studentRepo.findAll();
    }

    @Override
    public String deleteStudent(Long id) {
        studentRepo.deleteById(id);
        return "Student with id " +id+ " has been deleted successfully";
    }

    @Override
    public Student editStudent(Student newStudent, Long id) {
        return studentRepo.findById(id)
        .map(student -> {
                student.setName(newStudent.getName());
                student.setCourses(newStudent.getCourses());
                student.setInstitution(newStudent.getInstitution());
                return studentRepo.save(student);
        }).orElseThrow(()->new AlreadyExistsException("Cannot edit student, id not found"));
    }

    @Override
    public List<Student> findStudentWithSorting(String name) {
        return studentRepo.findAll(Sort.by(name));
    }

    @Override
    public Page<Student> findStudentWithPagination(int offset, int pageSize) {
        Page<Student> students = studentRepo.findAll(PageRequest.of(offset, pageSize));
        return students;
    }
    
}
