package model;

public class CurrentAccount extends Account {
    double overdraftLimit;
    double serviceCharge;   
    String businessName;    

    public CurrentAccount(double accNo, String name, double balance, double overdraftLimit, double serviceCharge, String businessName) {
        super(accNo, name, balance, "CURRENT");
        this.overdraftLimit = overdraftLimit;
        this.serviceCharge = serviceCharge;
        this.businessName = businessName;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    double getOverdraftLimit() {
		return overdraftLimit;
	}

	void setOverdraftLimit(double overdraftLimit) {
		this.overdraftLimit = overdraftLimit;
	}

	double getServiceCharge() {
		return serviceCharge;
	}

	void setServiceCharge(double serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	String getBusinessName() {
		return businessName;
	}

	void setBusinessName(String businessName) {
		this.businessName = businessName;
	}

	public void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
        } else {
            System.out.println("Overdraft limit exceeded.");
        }
    }

    public void calculateInterest() {
        System.out.println("No interest for Current Account.");
    }

	@Override
	public String toString() {
		return "accNo=" + accNo + ","
				+ " name=" + name + 
				", balance=" + balance + 
				", accountType="+ accountType + 
				", overdraftLimit=" + overdraftLimit + 
				", serviceCharge=" + serviceCharge
				+ ", businessName=" + businessName;
	}
    
    
    
    
}

