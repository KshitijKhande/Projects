package model;

import java.time.LocalDate;
import java.time.Period;

public class SalaryAccount extends Account {
    String employerName;
    int salaryCreditDate;       
    double linkedSavingsAccNo;  
    LocalDate lastTransactionDate; 

    public SalaryAccount(double accNo, String name, double balance, String employerName, int salaryCreditDate, double linkedSavingsAccNo) {
        super(accNo, name, balance, "SALARY");
        this.employerName = employerName;
        this.salaryCreditDate = salaryCreditDate;
        this.linkedSavingsAccNo = linkedSavingsAccNo;
        this.lastTransactionDate = LocalDate.now(); 
    }

    public void deposit(double amount) {
        balance += amount;
        lastTransactionDate = LocalDate.now(); // update activity
    }
    
    

    String getEmployerName() {
		return employerName;
	}

	void setEmployerName(String employerName) {
		this.employerName = employerName;
	}

	int getSalaryCreditDate() {
		return salaryCreditDate;
	}

	void setSalaryCreditDate(int salaryCreditDate) {
		this.salaryCreditDate = salaryCreditDate;
	}

	double getLinkedSavingsAccNo() {
		return linkedSavingsAccNo;
	}

	void setLinkedSavingsAccNo(double linkedSavingsAccNo) {
		this.linkedSavingsAccNo = linkedSavingsAccNo;
	}

	LocalDate getLastTransactionDate() {
		return lastTransactionDate;
	}

	void setLastTransactionDate(LocalDate lastTransactionDate) {
		this.lastTransactionDate = lastTransactionDate;
	}

	public void withdraw(double amount) {
        LocalDate today = LocalDate.now();
        Period diff = Period.between(lastTransactionDate, today);

        if (diff.getYears() >= 2) {
            System.out.println("Withdrawal denied: Account inactive for 2 years.");
            return;
        }

        if (balance >= amount) {
            balance -= amount;
            lastTransactionDate = LocalDate.now(); // update activity
        } else {
            System.out.println("Insufficient balance in Salary Account.");
        }
    }

    public void calculateInterest() {
        double interest = balance * 0.03;
        balance += interest;
    }

	@Override
	public String toString() {
		return "accNo=" + accNo + 
				", name=" + name + 
				", balance=" + balance + 
				", accountType="+ accountType + 
				", employerName=" + employerName + 
				", salaryCreditDate=" + salaryCreditDate
				+ ", linkedSavingsAccNo=" + linkedSavingsAccNo + 
				", lastTransactionDate=" + lastTransactionDate;
	}
    
    
}

