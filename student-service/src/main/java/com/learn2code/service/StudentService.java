package com.learn2code.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learn2code.entity.Student;
import com.learn2code.feignclients.MentorFeignClient;
import com.learn2code.repository.StudentRepository;
import com.learn2code.request.CreateStudentRequest;
import com.learn2code.response.StudentResponse;
import com.learn2code.response.MentorResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import reactor.core.publisher.Mono;


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

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setMentorId(createStudentRequest.getMentorId());
		
		student = studentRepository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setMentorResponse(commonService.getMentorByIdViaGateway(student.getMentorId()));
		//studentResponse.setMentorResponse(commonService.getMentorById(student.getMentorId()));
		//studentResponse.setMentorResponse(mentorFeignClient.getById(student.getMentorId()));
		
		return new StudentResponse(student);
	}
	
	
	public StudentResponse getById (long id) {
		
		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setMentorResponse(commonService.getMentorByIdViaGateway(student.getMentorId()));
		//studentResponse.setMentorResponse(commonService.getMentorById(student.getMentorId()));
		//studentResponse.setMentorResponse(mentorFeignClient.getById(student.getMentorId()));
		
		return studentResponse;
	}


	public List<Student> getStudents() {
		// TODO Auto-generated method stub
		return (List<Student>) studentRepository.findAll();
	}


	public void deleteStudent(long id) {
		// TODO Auto-generated method stub
		Student student = studentRepository.findById(id).get();
		studentRepository.delete(student);
	}


	public StudentResponse updateStudent(CreateStudentRequest createStudentRequest) {
		// TODO Auto-generated method stub
		return null;
	}
	
	//name will be same that you provided in application.properties file
	@CircuitBreaker(name="mentorService", fallbackMethod= "fallbackGetMentorById")
	public MentorResponse getMentorById (long mentorId) {
		Mono<MentorResponse> mentorResponse = 
				webClient.get().uri("/getById/" + mentorId)
		.retrieve().bodyToMono(MentorResponse.class);
		
		return mentorResponse.block();
		
		/*MentorResponse mentorResponse = mentorFeignClient.getById(mentorId);
		return mentorResponse;*/
	}
	
	public MentorResponse fallbackGetMentorById (long mentorId, Throwable th) {
		return new MentorResponse();
	}
}
