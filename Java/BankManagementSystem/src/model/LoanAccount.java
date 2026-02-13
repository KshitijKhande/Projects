package model;

public class LoanAccount extends Account {
    double loanAmount;
    double interestRate;
    int tenureMonths;          
    int remainingInstallments; 

    public LoanAccount(double accNo, String name, double balance, double loanAmount, double interestRate, int tenureMonths) {
        super(accNo, name, balance, "LOAN");
        this.loanAmount = loanAmount;
        this.interestRate = interestRate;
        this.tenureMonths = tenureMonths;
        this.remainingInstallments = tenureMonths;
    }
    
    

    double getLoanAmount() {
		return loanAmount;
	}

    void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

    double getInterestRate() {
		return interestRate;
	}

    void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}

    int getTenureMonths() {
		return tenureMonths;
	}

    void setTenureMonths(int tenureMonths) {
		this.tenureMonths = tenureMonths;
	}

	int getRemainingInstallments() {
		return remainingInstallments;
	}

	void setRemainingInstallments(int remainingInstallments) {
		this.remainingInstallments = remainingInstallments;
	}



	public void deposit(double amount) {
        balance -= amount; // repayment reduces outstanding loan
        if (remainingInstallments > 0) remainingInstallments--;
    }

    public void withdraw(double amount) {
        System.out.println("Withdrawals not allowed from Loan Account.");
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
				", loanAmount=" + loanAmount + 
				", interestRate=" + interestRate + 
				", tenureMonths="+ tenureMonths + 
				", remainingInstallments=" + remainingInstallments;
	}
    
    
}

