package com.learn2code.response;

import com.learn2code.entity.Mentor;

public class MentorResponse {

	private long mentorId;
	private String firstName;
	private String lastName;
	private String email;
	
	public MentorResponse(Mentor mentor) {
		this.mentorId=mentor.getId();
		this.firstName=mentor.getFirstName();
		this.lastName=mentor.getLastName();
		this.email=mentor.getEmail();
	}
	public long getMentorId() {
		return mentorId;
	}
	public void setMentorId(long mentorId) {
		this.mentorId = mentorId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
