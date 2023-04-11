package com.learn2code.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/student")
public class StudentController {
	
	@Autowired
	StudentService studentService;
	
	@PostMapping("/create")
	public StudentResponse createStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.createStudent(createStudentRequest);
	}
	
	@PostMapping("registerStudent")
	public StudentResponse registerStudent (@RequestBody CreateStudentRequest createStudentRequest) {
		return studentService.createStudent(createStudentRequest);
	}
	
	
	@GetMapping("getStudents")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@GetMapping("getStudentById/{id}")
	public StudentResponse getById (@PathVariable long id) {
		return studentService.getById(id);
	}
	
	@DeleteMapping("/deleteStudent")
	public void deleteStudent(@RequestParam Integer id) {
		studentService.deleteStudent(id);
	}
	
	@PutMapping("/updateStudent")
	public StudentResponse updateStudent(@RequestBody CreateStudentRequest createStudentRequest) {
		return  studentService.updateStudent(createStudentRequest);
	}
	
}
