package com.learn2code.feignclients;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;

import feign.Feign;

@LoadBalancerClient(value="mentor-service")
public class MentorLoadBalConfig {
	
	@LoadBalanced
	@Bean
	public Feign.Builder feignBuilder (){
		return Feign.builder();
	}
}
