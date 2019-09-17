package com.cf.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2338378953945562327L;

	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "PHONE_NUMBER")
	private long phoneNumber;
	
	@Id
    @Column(name = "EMAIL_ADDRESS")
	private String emailAddress;
	
	@Column(name = "USER_ROLE")
	private String userRole;
	
	@Column(name = "COMPANY_NAME")
	private String companyName;
	
	@Column(name = "ADDRESS")
	private String address;
	
	@Column(name = "USER_PASSWORD")
	private String password;
	
	/*
	 * @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	 * 
	 * @JoinColumn(name="USER_ID") private Set<PurchaseOrder> purchaseOrders = new
	 * HashSet<PurchaseOrder>();
	 */
	 
	
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

	/*
	 * public Set<PurchaseOrder> getPurchaseOrders() { return purchaseOrders; }
	 * public void setPurchaseOrders(Set<PurchaseOrder> purchaseOrders) {
	 * this.purchaseOrders = purchaseOrders; }
	 */
	/*
	 * @Override public String toString() { return "User [lastName=" + lastName +
	 * ", firstName=" + firstName + ", phoneNumber=" + phoneNumber +
	 * ", emailAddress=" + emailAddress + ", userRole=" + userRole +
	 * ", companyName=" + companyName + ", address=" + address + ", password=" +
	 * password + "]"; }
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}
	
	
	
}
