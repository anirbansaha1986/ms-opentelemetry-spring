package com.learn2code.exception;

@SuppressWarnings("serial")
public class MentorNotFoundException extends RuntimeException {
    public MentorNotFoundException(String mentorNotFound) {
        super(mentorNotFound);
    }
}
