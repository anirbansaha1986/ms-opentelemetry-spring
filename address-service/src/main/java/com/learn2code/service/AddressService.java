package com.learn2code.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn2code.entity.Address;
import com.learn2code.exception.AddressNotFoundException;
import com.learn2code.repository.AddressRepository;
import com.learn2code.request.CreateAddressRequest;
import com.learn2code.response.AddressResponse;

@Service
public class AddressService {
	
	Logger LOGGER = LoggerFactory.getLogger(AddressService.class);
	
	@Autowired
	AddressRepository addressRepository;
	
	public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
		
		Address address = new Address();
		address.setStreet(createAddressRequest.getStreet());
		address.setCity(createAddressRequest.getCity());
		
		addressRepository.save(address);
		
		return new AddressResponse(address);
		
	}
	
	public AddressResponse getById (long id) {
		
		LOGGER.info("Inside getById "+ id);
		
		Address address = addressRepository.findById(id).get();
		
		if(address.getId() > 0){
	        LOGGER.error("Price Not Found for Address Id {}", id);
	        throw new AddressNotFoundException("Address Not Found");
	    }
		
		return new AddressResponse(address);
	}
	

}
