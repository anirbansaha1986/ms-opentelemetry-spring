package com.learn2code.apigateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import io.opentelemetry.extension.annotations.WithSpan;

import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter{
	
	Logger logger = LoggerFactory.getLogger(CustomFilter.class);

	@Override
	@WithSpan
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		ServerHttpRequest request = exchange.getRequest();
		
		if (request.getURI().toString().contains("/api/student")) {
			// TODO: Put details
		}
		
		//Fetching Authorization info from request header
		logger.info("Authorization = " + request.getHeaders().getFirst("Authorization"));
		
		//It will route the object to appropriate microservices
		//Add post filter
		return chain.filter(exchange).then(Mono.fromRunnable(() -> {
			ServerHttpResponse response = exchange.getResponse();
			logger.info("Post Filter = " + response.getStatusCode());
		}));
	}
	

}
