package model;

public class SavingsAccount extends Account {
    double interestRate;
    int withdrawalLimit;
    double minBalance;        
    int monthlyWithdrawals;   

    public SavingsAccount(double accNo, String name, double balance, double interestRate, int withdrawalLimit, double minBalance) {
        super(accNo, name, balance, "SAVINGS");
        this.interestRate = interestRate;
        this.withdrawalLimit = withdrawalLimit;
        this.minBalance = minBalance;
        this.monthlyWithdrawals = 0;
    }

    
    double getInterestRate() {
		return interestRate;
	}


	void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}


	int getWithdrawalLimit() {
		return withdrawalLimit;
	}


	void setWithdrawalLimit(int withdrawalLimit) {
		this.withdrawalLimit = withdrawalLimit;
	}


	double getMinBalance() {
		return minBalance;
	}


	void setMinBalance(double minBalance) {
		this.minBalance = minBalance;
	}


	int getMonthlyWithdrawals() {
		return monthlyWithdrawals;
	}


	void setMonthlyWithdrawals(int monthlyWithdrawals) {
		this.monthlyWithdrawals = monthlyWithdrawals;
	}


	public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (monthlyWithdrawals < withdrawalLimit && balance - amount >= minBalance) {
            balance -= amount;
            monthlyWithdrawals++;
        } else {
            System.out.println("Withdrawal limit reached or minimum balance not maintained.");
        }
    }

    public void calculateInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }


	@Override
	public String toString() {
		return "accNo=" + accNo + 
				", name=" + name + 
				", balance=" + balance + 
				", accountType="+ accountType +
				", interestRate=" + interestRate + 
				", withdrawalLimit=" + withdrawalLimit
				+ ", minBalance=" + minBalance + 
				", monthlyWithdrawals=" + monthlyWithdrawals;
	}
    
    
}

