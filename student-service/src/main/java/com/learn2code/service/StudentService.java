package com.learn2code.service;

import java.util.List;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learn2code.controller.StudentController;
import com.learn2code.entity.Student;
import com.learn2code.feignclients.MentorFeignClient;
import com.learn2code.repository.StudentRepository;
import com.learn2code.request.CreateStudentRequest;
import com.learn2code.response.MentorResponse;
import com.learn2code.response.StudentResponse;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WebClient webClient;
	
	@Autowired
	MentorFeignClient mentorFeignClient;
	
	@Autowired
	CommonService commonService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setMentorId(createStudentRequest.getMentorId());
		
		student = studentRepository.save(student);
		
		LOGGER.info("Student Created");
		
		StudentResponse studentResponse = new StudentResponse(student);
		
		MentorResponse mentorResponse = commonService.getMentorByIdViaGateway(student.getMentorId());
		
		studentResponse.setMentorId(mentorResponse.getMentorId());
		studentResponse.setMentorName(mentorResponse.getFirstName() + " " + mentorResponse.getLastName());
		
		return new StudentResponse(student);
	}
	
	
	public StudentResponse getById (long id) {
		
		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		
		MentorResponse mentorResponse = commonService.getMentorByIdViaGateway(student.getMentorId());
		
		studentResponse.setMentorId(mentorResponse.getMentorId());
		studentResponse.setMentorName(mentorResponse.getFirstName() + " " + mentorResponse.getLastName());
		
		LOGGER.info("Student details fetched");
		
		return studentResponse;
	}


	public List<StudentResponse> getStudents() {
		
		List<Student> students = (List<Student>) studentRepository.findAll();
		List<StudentResponse> studentResponses = new ArrayList<StudentResponse>();
		
		students.forEach((student) -> {
			StudentResponse studentResponse = new StudentResponse(student);
			MentorResponse mentorResponse = commonService.getMentorByIdViaGateway(student.getMentorId());
			
			LOGGER.info("Student mentor details {}", mentorResponse.toString());
			
			studentResponse.setMentorId(mentorResponse.getMentorId());
			studentResponse.setMentorName(mentorResponse.getFirstName() + " " + mentorResponse.getLastName());
			
			studentResponses.add(studentResponse);
			
		});
		
		return studentResponses;
	}


	public void deleteStudent(long id) {
		
		Student student = studentRepository.findById(id).get();
		studentRepository.delete(student);
		
		LOGGER.info("Student details deleted");
	}


	public StudentResponse updateStudent(Student student) {
		
		Student studentOld = studentRepository.findById(student.getId()).get();
		studentOld.setFirstName(student.getFirstName());
		studentOld.setLastName(student.getLastName());
		studentOld.setEmail(student.getEmail());
		studentOld.setMentorId(student.getMentorId());
		
		student = studentRepository.save(studentOld);
		
		StudentResponse studentResponse = new StudentResponse(student);
		
		MentorResponse mentorResponse = commonService.getMentorByIdViaGateway(student.getMentorId());
		
		studentResponse.setMentorId(mentorResponse.getMentorId());
		studentResponse.setMentorName(mentorResponse.getFirstName() + " " + mentorResponse.getLastName());
		
		return studentResponse;
	}
	
}
