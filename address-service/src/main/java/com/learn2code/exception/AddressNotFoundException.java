package com.learn2code.exception;

@SuppressWarnings("serial")
public class AddressNotFoundException extends RuntimeException {
    public AddressNotFoundException(String addressNotFound) {
        super(addressNotFound);
    }
}
