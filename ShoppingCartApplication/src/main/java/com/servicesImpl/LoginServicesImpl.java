package com.servicesImpl;

import com.beans.User;
import com.DAO.UserDAO;
import com.DAOImpl.UserDAOImpl;
import com.services.LoginServices;

public class LoginServicesImpl implements LoginServices {
    private UserDAO userDAO;

    public LoginServicesImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public boolean signUp(int id ,String username, String password, String email,String mobileno,String address, String securityques, String secquesans) {
        User user = new User(id,username, password, email,mobileno,address, securityques, secquesans);
        return userDAO.insertUser(user);
    }

    @Override
    public User signIn(String username, String password) {
        return userDAO.validateUser(username, password);
    }

    @Override
    public String forgotPassword(String username, String securityques, String secquesans) {
        return userDAO.forgotPassword(username, securityques, secquesans);
    }

	@Override
	public boolean updatePassword(String username, String password) {
		return userDAO.updatePassword(username, password);
	}

	
}

