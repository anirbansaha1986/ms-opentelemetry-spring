package com.learn2code.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.learn2code.entity.Student;
import com.learn2code.request.CreateStudentRequest;
import com.learn2code.response.StudentResponse;
import com.learn2code.service.StudentService;

@RestController
@EnableFeignClients("com.learn2code.feignclients")
@RequestMapping("/api/student")
@CrossOrigin
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.createStudent(createStudentRequest);
	}
	
	@PostMapping(value = "/registerStudent", consumes = {"application/json"})
	public StudentResponse registerStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		LOGGER.info("Registering Student Details");
		return studentService.createStudent(createStudentRequest);
	}
	
	
	@GetMapping("getStudents")
	public List<StudentResponse> getStudents() {
		LOGGER.info("Getting Student Details");
		return studentService.getStudents();
	}
	
	@GetMapping("getStudentById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		LOGGER.info("Fetching Details for student with id {}", id);
		return studentService.getById(id);
	}
	
	
	@DeleteMapping("/deleteStudent")
	public ResponseEntity<Void> deleteStudent(@RequestParam Integer id) {
		LOGGER.info("Deleting Student with id {}", id);
		return studentService.deleteStudent(id);
	}
	
	@PutMapping("/updateStudent")
	public StudentResponse updateStudent(@RequestBody Student student) {
		LOGGER.info("Updating Student with id {}", student.getId());
		return  studentService.updateStudent(student);
	}
	
}
