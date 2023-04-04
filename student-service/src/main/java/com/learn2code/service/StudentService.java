package com.learn2code.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.learn2code.entity.Student;
import com.learn2code.feignclients.AddressFeignClient;
import com.learn2code.repository.StudentRepository;
import com.learn2code.request.CreateStudentRequest;
import com.learn2code.response.StudentResponse;


@Service
public class StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Autowired
	WebClient webClient;
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	@Autowired
	CommonService commonService;

	public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {

		Student student = new Student();
		student.setFirstName(createStudentRequest.getFirstName());
		student.setLastName(createStudentRequest.getLastName());
		student.setEmail(createStudentRequest.getEmail());
		student.setAddressId(createStudentRequest.getAddressId());
		
		student = studentRepository.save(student);
		
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setAddressResponse(commonService.getAddressByIdViaGateway(student.getAddressId()));
		//studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
		//studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
		
		return new StudentResponse(student);
	}
	
	public StudentResponse getById (long id) {
		
		Student student = studentRepository.findById(id).get();
		StudentResponse studentResponse = new StudentResponse(student);
		
		studentResponse.setAddressResponse(commonService.getAddressByIdViaGateway(student.getAddressId()));
		//studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
		//studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
		
		return studentResponse;
	}
	
	/*//name will be same that you provided in application.properties file
	@CircuitBreaker(name="addressService", fallbackMethod= "fallbackGetAddressById")
	public AddressResponse getAddressById (long addressId) {
		/*Mono<AddressResponse> addressResponse = 
				webClient.get().uri("/getById/" + addressId)
		.retrieve().bodyToMono(AddressResponse.class);
		
		return addressResponse.block();
		
		AddressResponse addressResponse = addressFeignClient.getById(addressId);
		return addressResponse;
	}
	
	public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {
		return new AddressResponse();
	}*/
}
