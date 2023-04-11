package com.learn2code.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn2code.response.MentorResponse;

@FeignClient(value = "api-gateway")
public interface APIGatewayFeignClient {

	@GetMapping("/mentor-service/api/mentor/getById/{id}")
	public MentorResponse getById(@PathVariable long id);
}
