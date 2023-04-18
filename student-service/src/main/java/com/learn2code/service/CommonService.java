package com.learn2code.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn2code.feignclients.APIGatewayFeignClient;
import com.learn2code.feignclients.MentorFeignClient;
import com.learn2code.response.MentorResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	
	Logger logger = LoggerFactory.getLogger(CommonService.class);
	long count = 1;
	
	@Autowired
	MentorFeignClient mentorFeignClient;
	
	@Autowired
	APIGatewayFeignClient apiGatewayFeignClient;

	//name will be same that you provided in application.properties file
		@CircuitBreaker(name="mentorService", fallbackMethod= "fallbackGetMentorById")
		public MentorResponse getMentorById (long mentorId) {
			logger.info("count = " + count);
			count++;
			MentorResponse mentorResponse = mentorFeignClient.getById(mentorId);
			return mentorResponse;
		}
		
		public MentorResponse fallbackGetMentorById (long mentorId, Throwable th) {
			logger.error("Error = " + th);
			return new MentorResponse();
		}
		
		@CircuitBreaker(name="mentorService", fallbackMethod= "fallbackGetMentorById")
		public MentorResponse getMentorByIdViaGateway (long mentorId) {
			logger.info("count = " + count);
			count++;
			MentorResponse mentorResponse = mentorFeignClient.getById(mentorId);
			return mentorResponse;
		}
}
