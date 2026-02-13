package model;

import java.time.LocalDate;

public class Transaction {
    public double transAmount;      
    public String transType;        
    public LocalDate transDate;     
    double accNo;             

    
    public Transaction(double transAmount, String transType, double accNo) {
        this.transAmount = transAmount;
        this.transType = transType;
        this.accNo = accNo;
        this.transDate = LocalDate.now(); 
    }

    double getTransAmount() {
		return transAmount;
	}



	void setTransAmount(double transAmount) {
		this.transAmount = transAmount;
	}



	String getTransType() {
		return transType;
	}



	void setTransType(String transType) {
		this.transType = transType;
	}



	LocalDate getTransDate() {
		return transDate;
	}



	void setTransDate(LocalDate transDate) {
		this.transDate = transDate;
	}



	double getAccNo() {
		return accNo;
	}



	void setAccNo(double accNo) {
		this.accNo = accNo;
	}



	public String toString() {
        return "Date: " + transDate +
               ", Type: " + transType +
               ", Amount: " + transAmount +
               ", Account: " + accNo;
    }
}
