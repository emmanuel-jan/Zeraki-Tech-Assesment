package com.jantech.schoolproject.api;

import java.net.URI;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jantech.schoolproject.models.Student;
import com.jantech.schoolproject.service.StudentService;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StudentResource {

    private final StudentService studentService;

    @GetMapping("/students")
    public ResponseEntity<List<Student>>getStudents(){
        return ResponseEntity.ok().body(studentService.getStudents());
    }

    @GetMapping("/students/{name}")
    public ResponseEntity<List<Student>>getStudentsWithSorting(@PathVariable String name){
        return ResponseEntity.ok().body(studentService.findStudentWithSorting(name));
    }

    @GetMapping("/students/pagination/{offset}/{pageSize}")
    public ResponseEntity<Page<Student>>getStudentsWithPagination(@PathVariable int offset ,@PathVariable int pageSize ){
        Page<Student> studentsWithPagination =  studentService.findStudentWithPagination(offset, pageSize);
        return ResponseEntity.ok().body(studentsWithPagination);
    }

    @PostMapping("/student/save")
    public ResponseEntity<Student>saveStudent(@RequestBody Student student){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/student/save").toString());
        return ResponseEntity.created(uri).body(studentService.saveStudent(student));
    }

    @PostMapping("/course/addtostudent")
    public ResponseEntity<?>addCourseToStudent(@RequestBody CourseToStudentForm form){
        studentService.addCourseToStudent(form.getStudentname(), form.getCourseName());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/course/addtostudent").toString());
        return ResponseEntity.created(uri).body("Course added to Student successfully");
    }

    @PutMapping("/student/{id}")
    public ResponseEntity<Student>editStudentById(@RequestBody Student student, @PathVariable Long id){
        return ResponseEntity.ok().body(studentService.editStudent(student, id));
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity<String>deleteStudentById(@PathVariable Long id){
        return ResponseEntity.ok().body(studentService.deleteStudent(id));
    }
    
}

@Data
class CourseToStudentForm {
    private String studentname;
    private String courseName;
}
