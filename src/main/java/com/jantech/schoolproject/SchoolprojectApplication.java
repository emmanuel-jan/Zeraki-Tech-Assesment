package com.jantech.schoolproject;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.jantech.schoolproject.models.Course;
import com.jantech.schoolproject.models.Student;
import com.jantech.schoolproject.models.Institution;
import com.jantech.schoolproject.service.InstitutionService;
import com.jantech.schoolproject.service.StudentService;

@SpringBootApplication
public class SchoolprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchoolprojectApplication.class, args);
	}

	// Adding some dummy data to the database
	@Bean
	CommandLineRunner run(InstitutionService institutionService, StudentService studentService){
		return args -> {
			institutionService.saveCourse(new Course(null, "COMPUTER SCIENCE"));
			institutionService.saveCourse(new Course(null, "MEDICINE"));
			institutionService.saveCourse(new Course(null, "LAW"));
			institutionService.saveCourse(new Course(null, "ENGINEERING"));
			institutionService.saveCourse(new Course(null, "ARCHITECTURE"));
			institutionService.saveCourse(new Course(null, "NURSING"));

			institutionService.saveInstitution(new Institution(null, "Maseno University", new ArrayList<>()));
			institutionService.saveInstitution(new Institution(null, "Havard University", new ArrayList<>()));
			institutionService.saveInstitution(new Institution(null, "Oxford University", new ArrayList<>()));
			institutionService.saveInstitution(new Institution(null, "Nairobi University", new ArrayList<>()));
			institutionService.saveInstitution(new Institution(null, "Makerere University", new ArrayList<>()));

			institutionService.addCourseToInstitution("Makerere University", "MEDICINE");
			institutionService.addCourseToInstitution("Oxford University", "MEDICINE");
			institutionService.addCourseToInstitution("Oxford University", "LAW");
			institutionService.addCourseToInstitution("Oxford University", "ENGINEERING");
			institutionService.addCourseToInstitution("Nairobi University", "NURSING");
			institutionService.addCourseToInstitution("Maseno University", "COMPUTER SCIENCE");

			studentService.saveStudent(new Student(null, "John Doe", null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Tom Hardy", null,new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Henry Cavil", null,new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Klaus Mickaelson",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Emmanuel Jan",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Clark Kent",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Bruce Wyne",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Jay Garic",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Iris West",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Dr Wells",null, new ArrayList<>()));
			studentService.saveStudent(new Student(null, "Cisco Ramon",null, new ArrayList<>()));

			studentService.addCourseToStudent("John Doe", "MEDICINE");
			studentService.addCourseToStudent("Tom Hardy", "MEDICINE");
			studentService.addCourseToStudent("Henry Cavil", "LAW");
			studentService.addCourseToStudent("Henry Cavil", "ENGINEERING");
			studentService.addCourseToStudent("Klaus Mickaelson", "NURSING");
			studentService.addCourseToStudent("Emmanuel Jan", "COMPUTER SCIENCE");
		};
	}

}
