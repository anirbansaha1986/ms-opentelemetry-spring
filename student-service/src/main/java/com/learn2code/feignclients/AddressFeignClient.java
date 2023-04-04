package com.learn2code.feignclients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.learn2code.response.AddressResponse;

//Either you can provide complete URL or you can add base url and append path value
@FeignClient(value = "address-service", path="/api/address")
public interface AddressFeignClient {

	@GetMapping("/getById/{id}")
	public AddressResponse getById(@PathVariable long id);
}
