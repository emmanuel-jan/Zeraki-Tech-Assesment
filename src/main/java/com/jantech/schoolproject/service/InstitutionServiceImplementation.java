package com.jantech.schoolproject.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.jantech.schoolproject.exception.AlreadyExistsException;
import com.jantech.schoolproject.exception.InstitutionNotFoundException;
import com.jantech.schoolproject.models.Course;
import com.jantech.schoolproject.models.Institution;
import com.jantech.schoolproject.repo.CourseRepo;
import com.jantech.schoolproject.repo.InstitutionRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InstitutionServiceImplementation implements InstitutionService {

    private final InstitutionRepo institutionRepo;
    private final CourseRepo courseRepo;

    @Override
    public Institution saveInstitution(Institution institution) {
        log.info("Saving new institution {} to the database", institution.getName());
        if(institutionRepo.existsByName(institution.getName())){
            throw new AlreadyExistsException("Institution Already Exists");
        }
        return institutionRepo.save(institution);
    }

    @Override
    public Course saveCourse(Course course) {
        log.info("Saving new course {} to the database", course.getName());
        if(courseRepo.existsByName(course.getName())){
            throw new AlreadyExistsException("Course Already Exists");
        }
        return courseRepo.save(course);
    }

    @Override
    public void addCourseToInstitution(String institutionName, String courseName) {
        log.info("Saving course {} to the institution {}", courseName, institutionName);
        Institution institution = institutionRepo.findByName(institutionName);
        Course course = courseRepo.findByName(courseName);
        institution.getCourses().add(course);
    }

    @Override
    public Institution getInstitution(String name) {
        log.info("Fetching institution {} ", name);
        return institutionRepo.findByName(name);
    }

    @Override
    public List<Institution> getInstitutions() {
        log.info("Fetching all institutions");
        return institutionRepo.findAll();
    }

    @Override
    public List<Course> getCourses() {
        log.info("Fetching all institutions");
        return courseRepo.findAll();
    }

    @Override
    public String deleteInstitution(Long id) {
        institutionRepo.deleteById(id);
        return "Institution with id " +id+ " has been deleted successfully";
    }

    @Override
    public Institution editInstitution(Institution newInstitution, Long id) {
        if(institutionRepo.existsByName(newInstitution.getName())){
            throw new AlreadyExistsException("Institution Already Exists");
        }
        return institutionRepo.findById(id)
            .map(institution -> {
                    institution.setName(newInstitution.getName());
                    institution.setCourses(newInstitution.getCourses());
                    return institutionRepo.save(institution);
            }).orElseThrow(()->new InstitutionNotFoundException(id));
    }

    @Override
    public Course editCourse(Course newCourse, Long id) {
        if(courseRepo.existsByName(newCourse.getName())){
            throw new AlreadyExistsException("Course Already Exists");
        }
        return courseRepo.findById(id)
        .map(course -> {
                course.setName(newCourse.getName());
                return courseRepo.save(course);
        }).orElseThrow(()->new AlreadyExistsException("Cannot edit course, id not found"));
    }

    @Override
    public String deleteCourse(Long id) {
        courseRepo.deleteById(id);
        return "Course with id " +id+ " has been deleted successfully";
    }
    
}
