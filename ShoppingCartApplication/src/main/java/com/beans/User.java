package com.beans;

import java.util.Objects;

public class User {
	private int id;
	private String username;
	private String password;
	private String email;
	private String mobileno;
	private String securityques;
	private String secquesans;
	private String address;
	private String status;
	
	public User() {
		
	}
	public User(int id,String username, String password, String email, String mobileno,String address, String securityques,
			String secquesans) {
		super();
		this.id=id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.mobileno = mobileno;
		this.address=address;
		this.securityques = securityques;
		this.secquesans = secquesans;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id=id;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileno() {
		return mobileno;
	}

	public void setMobileno(String mobileno) {
		this.mobileno = mobileno;
	}

	public String getSecurityques() {
		return securityques;
	}

	public void setSecurityques(String securityques) {
		this.securityques = securityques;
	}

	public String getSecquesans() {
		return secquesans;
	}

	public void setSecquesans(String secquesans) {
		this.secquesans = secquesans;
	}

	@Override
	public int hashCode() {
		return Objects.hash(email, mobileno, password, secquesans, securityques, username);
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
		return Objects.equals(email, other.email) && Objects.equals(mobileno, other.mobileno)
				&& Objects.equals(password, other.password) && Objects.equals(secquesans, other.secquesans)
				&& Objects.equals(securityques, other.securityques) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", email=" + email + ", mobileno=" + mobileno
				+ ", securityques=" + securityques + ", secquesans=" + secquesans + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getMobileno()=" + getMobileno()
				+ ", getSecurityques()=" + getSecurityques() + ", getSecquesans()=" + getSecquesans() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
}
