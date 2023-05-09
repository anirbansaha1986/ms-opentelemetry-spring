package com.learn2code.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn2code.request.CreateMentorRequest;
import com.learn2code.response.MentorResponse;
import com.learn2code.service.MentorService;
import com.learn2code.entity.Mentor;

@RestController
@RequestMapping("/api/mentor")
@RefreshScope
public class MentorController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MentorController.class);

	@Autowired
	MentorService mentorService;
	
	@PostMapping("/registerMentor")
	public MentorResponse registerMentor (@RequestBody CreateMentorRequest createMentorRequest) {
		return mentorService.createMentor(createMentorRequest);
	}
	
	@GetMapping("getMentors")
	public List<Mentor> getMentors() {
		LOGGER.info("Getting Student Mentor Details");
		return mentorService.getMentors();
	}
	
	
	@GetMapping("/getById/{id}")
	public MentorResponse getById(@PathVariable long id) {
		LOGGER.info("Getting Mentor details with id {}", id);
		return mentorService.getById(id);
	}
	
	@PutMapping("/updateMentor")
	public MentorResponse updateStudent(@RequestBody Mentor mentor) {
		LOGGER.info("Update Mentor Details with id {}",mentor.getId());
		return  mentorService.updateMentor(mentor);
	}
	
	
}
