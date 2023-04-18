package com.learn2code.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn2code.response.MentorResponse;

//Either you can provide complete URL or you can add base url and append path value
@FeignClient(url = "${mentor.service.URI}", value = "mentor-service", path="/api/mentor")
public interface MentorFeignClient {

	@GetMapping("/getById/{id}")
	public MentorResponse getById(@PathVariable long id);
}
