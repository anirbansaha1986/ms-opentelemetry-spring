package com.learn2code.app;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
@ComponentScan({"com.learn2code.controller", "com.learn2code.service"})
@EntityScan("com.learn2code.entity")
@EnableJpaRepositories("com.learn2code.repository")
@EnableFeignClients("com.learn2code.feignclients")
@EnableEurekaClient
public class StudentServiceApplication {

	@Value("${mentor.service.url}")
	private String mentorServiceUrl;
	
	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
	
	@Bean
	public WebClient webClient () {
		WebClient webClient = WebClient.builder()
				.baseUrl(mentorServiceUrl).build();
		return webClient;
	}

}
