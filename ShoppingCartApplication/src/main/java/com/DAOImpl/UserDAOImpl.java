package com.DAOImpl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.beans.User;
import com.DAO.UserDAO;

public class UserDAOImpl implements UserDAO {
    private Connection conn;

    public UserDAOImpl(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean insertUser(User user) {
        //String sql = "INSERT INTO usertable(username, password, email, mobileno,address,securityques, secquesans) VALUES(?,?,?,?,?,?,?)";
    	String sql = "INSERT INTO usertable(username, password, email, mobileno, address, securityques, secquesans, status) VALUES(?,?,?,?,?,?,?,?)";
 
    		try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getMobileno());
            ps.setString(5, user.getAddress());
            ps.setString(6, user.getSecurityques());
            ps.setString(7, user.getSecquesans());
            ps.setString(8, "active");
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    @Override
//    public User validateUser(String username, String password) {
//    	String sql = "SELECT * FROM usertable WHERE username=? AND password=? AND status='active'";
//
//        try (PreparedStatement ps = conn.prepareStatement(sql)) {
//            ps.setString(1, username);
//            ps.setString(2, password);
//            ResultSet rs = ps.executeQuery();
//            if (rs.next()) {
//                return new User(
//                		rs.getInt("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("email"),
//                    rs.getString("mobileno"),
//                    rs.getString("address"),
//                    rs.getString("securityques"),
//                    rs.getString("secquesans")
//                );
//                
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }
    
    @Override
    public User validateUser(String username, String password) {
        String sql = "SELECT * FROM usertable WHERE username=? AND password=? AND status='active'";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("mobileno"),
                    rs.getString("address"),
                    rs.getString("securityques"),
                    rs.getString("secquesans")
                );
                user.setStatus(rs.getString("status")); // ✅ set status here
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


//    @Override
//    public List<User> showAllUsers() {
//        List<User> users = new ArrayList<>();
//        String sql = "SELECT * FROM usertable";
//        try (Statement st = conn.createStatement();
//             ResultSet rs = st.executeQuery(sql)) {
//            while (rs.next()) {
//                users.add(new User(
//                		rs.getInt("id"),
//                    rs.getString("username"),
//                    rs.getString("password"),
//                    rs.getString("email"),
//                    rs.getString("mobileno"),
//                    rs.getString("address"),
//                    rs.getString("securityques"),
//                    rs.getString("secquesans")
//                ));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return users;
//    }
    
    
    @Override
    public List<User> showAllUsers() {
        List<User> users = new ArrayList<>();
        String sql = "SELECT * FROM usertable";
        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                User user = new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    rs.getString("mobileno"),
                    rs.getString("address"),
                    rs.getString("securityques"),
                    rs.getString("secquesans")
                );
                user.setStatus(rs.getString("status")); // ✅ set status here
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }


    @Override
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE usertable SET password=? WHERE username=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newPassword);
            ps.setString(2, username);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM usertable WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

	@Override
	public boolean retrieveUser(String username) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public String forgotPassword(String username, String securityQues, String secQuesAns) {
        String sql = "SELECT password FROM usertable WHERE username=? AND securityques=? AND secquesans=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, securityQues);
            ps.setString(3, secQuesAns);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getString("password"); // return password if match found
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
	
//	public User findUserByUsername(String username) {
//	    String sql = "SELECT * FROM usertable WHERE username=?";
//	    try (PreparedStatement ps = conn.prepareStatement(sql)) {
//	        ps.setString(1, username);
//	        ResultSet rs = ps.executeQuery();
//	        if (rs.next()) {
//	            return new User(
//	                rs.getInt("id"),
//	                rs.getString("username"),
//	                rs.getString("password"),
//	                rs.getString("email"),
//	                rs.getString("mobileno"),
//	                rs.getString("address"),
//	                rs.getString("securityques"),
//	                rs.getString("secquesans")
//	            );
//	        }
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }
//	    return null;
//	}

}









