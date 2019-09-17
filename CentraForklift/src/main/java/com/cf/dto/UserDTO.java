package com.cf.dto;

import java.io.Serializable;

public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 2338378953945562327L;
	private String lastName;
	private String firstName;
	private long phoneNumber;
	private String emailAddress;
	private String userRole;
	private String companyName;
	private String address;
	private String password;
	
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "UserDTO [lastName=" + lastName + ", firstName=" + firstName + ", phoneNumber=" + phoneNumber
				+ ", emailAddress=" + emailAddress + ", userRole=" + userRole + ", companyName=" + companyName
				+ ", address=" + address + ", password=" + password + "]";
	}
	
	
}
