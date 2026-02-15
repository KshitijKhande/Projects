package com.model;

import java.time.LocalDate;

public class Coin {
    private int id;
    private String country;
    private String denomination;
    private int yearOfMinting;
    private double currentValue;
    private LocalDate acquiredDate;
    
    public Coin(int id, String country, String denomination, int yearOfMinting, double currentValue, LocalDate acquiredDate) {
        this.id = id;
        this.country = country;
        this.denomination = denomination;
        this.yearOfMinting = yearOfMinting;
        this.currentValue = currentValue;
        this.acquiredDate = acquiredDate;
    }

    public int getId() { 
    		return id; 
    	}
    public String getCountry() { 
    		return country; 
    	}
    public String getDenomination() { 
    		return denomination; 
    	}
    public int getYearOfMinting() { 
    		return yearOfMinting; 
    	}
    public double getCurrentValue() { 
    		return currentValue; 
    	}
    public LocalDate getAcquiredDate() { 
    		return acquiredDate; 
    	}
    
    
    @Override
    public String toString() {
        return id + " | " + country + " | " + denomination + " | " + yearOfMinting +
               " | Value: " + currentValue + " | Acquired: " + acquiredDate;
    }
}
