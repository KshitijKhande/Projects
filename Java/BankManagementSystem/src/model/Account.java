package model;

public abstract class Account {
    double accNo;
    String name;
    double balance;
    String accountType;  
          

    public Account(double accNo, String name, double balance, String accountType) {
        this.accNo = accNo;
        this.name = name;
        this.balance = balance;
        this.accountType = accountType;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);
    public abstract void calculateInterest();

    
    
    String getAccountType() {
		return accountType;
	}

	void setAccountType(String accountType) {
		this.accountType = accountType;
	}

//	String getStatus() {
//		return status;
//	}
//
//	void setStatus(String status) {
//		this.status = status;
//	}

	public void setAccNo(double accNo) {
		this.accNo = accNo;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getAccNo() { 
		return accNo; 
	}
	
    public String getName() {
    	return name; 
    }
    
   
    public double getBalance() { 
    	return balance; 
    }

	@Override
	public String toString() {
		return "accNo=" + accNo + 
				", name=" + name + 
				", balance=" + balance +
				", accountType=" + accountType;
	}
    
    
}

