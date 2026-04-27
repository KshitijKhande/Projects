package com.services;

import com.beans.User;

public interface LoginServices  {
	
	public boolean signUp(int id,String username,String password,String email,String mobileno,String address,String securityques,String secquesans);
	
	User signIn(String username,String password);
	
	public String forgotPassword(String username,String securityques,String secquesans);
	
	public boolean updatePassword(String username,String password);

	
}
