package com.learn2code.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn2code.feignclients.APIGatewayFeignClient;
import com.learn2code.feignclients.AddressFeignClient;
import com.learn2code.response.AddressResponse;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class CommonService {
	
	Logger logger = LoggerFactory.getLogger(CommonService.class);
	long count = 1;
	
	@Autowired
	AddressFeignClient addressFeignClient;
	
	@Autowired
	APIGatewayFeignClient apiGatewayFeignClient;

	//name will be same that you provided in application.properties file
		@CircuitBreaker(name="addressService", fallbackMethod= "fallbackGetAddressById")
		public AddressResponse getAddressById (long addressId) {
			logger.info("count = " + count);
			count++;
			AddressResponse addressResponse = addressFeignClient.getById(addressId);
			return addressResponse;
		}
		
		public AddressResponse fallbackGetAddressById (long addressId, Throwable th) {
			logger.error("Error = " + th);
			return new AddressResponse();
		}
		
		@CircuitBreaker(name="addressService", fallbackMethod= "fallbackGetAddressById")
		public AddressResponse getAddressByIdViaGateway (long addressId) {
			logger.info("count = " + count);
			count++;
			AddressResponse addressResponse = apiGatewayFeignClient.getById(addressId);
			return addressResponse;
		}
}
