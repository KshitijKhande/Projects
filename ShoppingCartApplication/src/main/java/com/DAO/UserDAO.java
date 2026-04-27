package com.DAO;

import java.util.List;

import com.beans.User;

public interface UserDAO {
	public boolean insertUser(User user);
	
	public boolean retrieveUser(String username);
	
	List<User> showAllUsers();
	
	User validateUser(String username, String password); 
	
	// login validation List<User> showAllUsers();
	boolean updatePassword(String username, String newPassword);
	
	public String forgotPassword(String username,String securityquestion,String secquesans);
	
	boolean deleteUser(int userId);


}
