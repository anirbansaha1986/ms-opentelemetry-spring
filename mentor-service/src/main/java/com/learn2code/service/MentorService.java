package com.learn2code.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn2code.entity.Mentor;
import com.learn2code.exception.MentorNotFoundException;
import com.learn2code.repository.MentorRepository;
import com.learn2code.request.CreateMentorRequest;
import com.learn2code.response.MentorResponse;

@Service
public class MentorService {
	
	Logger LOGGER = LoggerFactory.getLogger(MentorService.class);
	
	@Autowired
	MentorRepository mentorRepository;
	
	public MentorResponse createMentor(CreateMentorRequest createMentorRequest) {
		
		Mentor mentor = new Mentor();
		mentor.setFirstName(createMentorRequest.getFirstName());
		mentor.setLastName(createMentorRequest.getLastName());
		mentor.setEmail(createMentorRequest.getEmail());
		
		mentorRepository.save(mentor);
		
		LOGGER.info("Mentor Created");
		
		return new MentorResponse(mentor);
		
	}
	
	public MentorResponse getById (long id) {
		
		LOGGER.info("Inside getById "+ id);
		
		Mentor mentor = mentorRepository.findById(id).get();
		
		if(mentor == null){
	        LOGGER.error("Mentor Not Found with Mentor Id {}", id);
	        throw new MentorNotFoundException("Mentor Not Found");
	    }
		
		return new MentorResponse(mentor);
	}

	public List<Mentor> getMentors() {
		LOGGER.info("Get lists of Mentors");
		return (List<Mentor>)mentorRepository.findAll();
	}
	
}
