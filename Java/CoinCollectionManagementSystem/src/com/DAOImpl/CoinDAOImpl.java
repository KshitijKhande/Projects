package com.DAOImpl;

import com.DAO.CoinDAO;
import com.model.Coin;
import com.aspect.DBConnection;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;

public class CoinDAOImpl implements CoinDAO {
    private List<Coin> coinCollection = new ArrayList<>();

    @Override
    public void addCoin(Coin coin) {
        coinCollection.add(coin);
        
        try (Connection con = DBConnection.getConnection()) { 
	        	String sql = "INSERT INTO coins(country, denomination, year, value, acquired_date) VALUES(?,?,?,?,?)"; 
	        	PreparedStatement ps = con.prepareStatement(sql); 
	        	ps.setString(1, coin.getCountry()); 
	        	ps.setString(2, coin.getDenomination()); 
	        	ps.setInt(3, coin.getYearOfMinting()); 
	        	ps.setDouble(4, coin.getCurrentValue()); 
	        	ps.setDate(5, java.sql.Date.valueOf(coin.getAcquiredDate())); 
	        	ps.executeUpdate(); 
        	} 
        catch (SQLException e) { 
        		e.printStackTrace(); 	
        	}
    }
    
    
    @Override
    public void deleteCoin(int id) {
     
        coinCollection.removeIf(c -> c.getId() == id);

        
        try (Connection con = DBConnection.getConnection()) {
            String sql = "DELETE FROM coins WHERE id = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
    @Override
    public List<Coin> getAllCoins() {
        return coinCollection;
    }

    @Override
    public List<Coin> searchByCountry(String country) {
        return coinCollection.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(country))
                .toList();
    }

    @Override
    public List<Coin> searchByYear(int year) {
        return coinCollection.stream()
                .filter(c -> c.getYearOfMinting() == year)
                .toList();


    }

    @Override
    public List<Coin> searchByValueSorted() {
        return coinCollection.stream()
                .sorted(Comparator.comparingDouble(Coin::getCurrentValue))
                .toList();
    }

    @Override
    public Coin searchByCountryAndDenomination(String country, String denomination) {
        return coinCollection.stream()
                .filter(c -> c.getCountry().equalsIgnoreCase(country) &&
                             c.getDenomination().equalsIgnoreCase(denomination))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Coin> loadAllFromDatabase() {
        coinCollection.clear();
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM coins")) {

            while (rs.next()) {
                Coin coin = new Coin(
                    rs.getInt("id"),
                    rs.getString("country"),
                    rs.getString("denomination"),
                    rs.getInt("year"),
                    rs.getDouble("value"),
                    rs.getDate("acquired_date").toLocalDate()
                );
                coinCollection.add(coin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return coinCollection;
    }

}
