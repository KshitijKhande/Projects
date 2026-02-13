package model;

public class BankModel {
	
    public Account[] accounts = new Account[100];
    public Transaction[] transactions = new Transaction[100];
    public int accountCount = 0;
    public int transactionCount = 0;

    
    public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

	public Transaction[] getTransactions() {
		return transactions;
	}

	public void setTransactions(Transaction[] transactions) {
		this.transactions = transactions;
	}

	public int getAccountCount() {
		return accountCount;
	}

	public void setAccountCount(int accountCount) {
		this.accountCount = accountCount;
	}

	public int getTransactionCount() {
		return transactionCount;
	}

	public void setTransactionCount(int transactionCount) {
		this.transactionCount = transactionCount;
	}

	public void addAccount(Account acc) {
        if (accountCount < accounts.length) {
            accounts[accountCount++] = acc;
        }
    }

    public void addTransaction(Transaction t) {
        if (transactionCount < transactions.length) {
            transactions[transactionCount++] = t;
        }
    }

    public Account getAccount(double accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i].getAccNo() == accNo) {
                return accounts[i];
            }
        }
        return null;
    }

    SavingsAccount getSavingsAccount(double accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof SavingsAccount && accounts[i].getAccNo() == accNo) {
                return (SavingsAccount) accounts[i];
            }
        }
        return null;
    }

    CurrentAccount getCurrentAccount(double accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof CurrentAccount && accounts[i].getAccNo() == accNo) {
                return (CurrentAccount) accounts[i];
            }
        }
        return null;
    }

    LoanAccount getLoanAccount(double accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof LoanAccount && accounts[i].getAccNo() == accNo) {
                return (LoanAccount) accounts[i];
            }
        }
        return null;
    }

    SalaryAccount getSalaryAccount(double accNo) {
        for (int i = 0; i < accountCount; i++) {
            if (accounts[i] instanceof SalaryAccount && accounts[i].getAccNo() == accNo) {
                return (SalaryAccount) accounts[i];
            }
        }
        return null;
    }
    
    
}

