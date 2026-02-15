package com.aspect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.io.FileInputStream;

public class DBConnection {
    private static String url;
    private static String user;
    private static String password;
    private static String driver;

    static {
        try {
        		
        		FileInputStream fis;
    			fis = new FileInputStream(".//resources//dbconfig.properties");
    			Properties prop=new Properties();
    			prop.load(fis);
        		
            url = prop.getProperty("url");
            user = prop.getProperty("username");
            password = prop.getProperty("password");
            driver = prop.getProperty("classname");
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
}
