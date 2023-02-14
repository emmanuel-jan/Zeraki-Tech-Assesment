package com.jantech.schoolproject.api;

import java.net.URI;
import java.util.List;

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

import com.jantech.schoolproject.models.Course;
import com.jantech.schoolproject.models.Institution;
import com.jantech.schoolproject.service.InstitutionService;

import lombok.RequiredArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class InstitutionResource {
    
    private final InstitutionService institutionService;

    @GetMapping("/institutions")
    public ResponseEntity<List<Institution>>getInstitutions(){
        return ResponseEntity.ok().body(institutionService.getInstitutions());
    }

    @GetMapping("/courses")
    public ResponseEntity<List<Course>>getCourses(){
        return ResponseEntity.ok().body(institutionService.getCourses());
    }

    @GetMapping("/institution/{name}")
    public ResponseEntity<Institution>getInstitutionByName(@PathVariable String name){
        return ResponseEntity.ok().body(institutionService.getInstitution(name));
    }

    @DeleteMapping("/institution/{id}")
    public ResponseEntity<String>deleteInstitutionById(@PathVariable Long id){
        return ResponseEntity.ok().body(institutionService.deleteInstitution(id));
    }

    @DeleteMapping("/course/{id}")
    public ResponseEntity<String>deleteCourseById(@PathVariable Long id){
        return ResponseEntity.ok().body(institutionService.deleteCourse(id));
    }

    @PutMapping("/institution/{id}")
    public ResponseEntity<Institution>editInstitutionById(@RequestBody Institution institution, @PathVariable Long id){
        return ResponseEntity.ok().body(institutionService.editInstitution(institution, id));
    }

    @PutMapping("/course/{id}")
    public ResponseEntity<Course>editCourseById(@RequestBody Course course, @PathVariable Long id){
        return ResponseEntity.ok().body(institutionService.editCourse(course, id));
    }

    @PostMapping("/institution/save")
    public ResponseEntity<Institution>saveInstitution(@RequestBody Institution institution){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/institution/save").toString());
        return ResponseEntity.created(uri).body(institutionService.saveInstitution(institution));
    }

    @PostMapping("/course/save")
    public ResponseEntity<Course>saveCourse(@RequestBody Course course){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/course/save").toString());
        return ResponseEntity.created(uri).body(institutionService.saveCourse(course));
    }

    @PostMapping("/course/addtoinstitution")
    public ResponseEntity<?>addCourseToInstitution(@RequestBody CourseToInstitutionForm form){
        institutionService.addCourseToInstitution(form.getInstitutionname(), form.getCourseName());
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/course/addtoinstitution").toString());
        return ResponseEntity.created(uri).body("Course added to institution successfully");
    }
}

@Data
class CourseToInstitutionForm {
    private String institutionname;
    private String courseName;
}
