package view;

import model.*;
import controller.*;
import java.time.LocalDate;
import java.util.Scanner;


public class BankView {

    Scanner sc = new Scanner(System.in);

    public void showMenu(BankController controller) {
        int choice;
        do {
            System.out.println("\n=== Banking System Menu ===");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Calculate Interest");
            System.out.println("5. Display Accounts");
            System.out.println("6. Display Transactions");
            System.out.println("7. End of Day Report");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    createAccount(controller);
                    break;
                case 2:
                    deposit(controller);
                    break;
                case 3:
                    withdraw(controller);
                    break;
                case 4:
                    calculateInterest(controller);
                    break;
                case 5:
                    displayAccountsMenu(controller);
                    break;

                case 6:
                    controller.displayTransactions();
                    break;
                case 7:
                    controller.endOfDayReport();
                    break;
                case 0:
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);
    }

    public void displayAccountsMenu(BankController controller) {
        System.out.println("\nDisplay Accounts:");
        System.out.println("1. All Accounts");
        System.out.println("2. Savings Accounts");
        System.out.println("3. Current Accounts");
        System.out.println("4. Loan Accounts");
        System.out.println("5. Salary Accounts");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        switch (choice) {
            case 1:
                controller.displayAccounts();
                break;
            case 2:
                controller.displayAccountsByType("SAVINGS");
                break;
            case 3:
                controller.displayAccountsByType("CURRENT");
                break;
            case 4:
                controller.displayAccountsByType("LOAN");
                break;
            case 5:
                controller.displayAccountsByType("SALARY");
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void showMessage(String message) {
        System.out.println(message);
    }

    public void showError(String error) {
        System.err.println(error);
    }

    
	  public void displayAccounts(Account[] accounts, int count) {
	  System.out.println("=== Account List ===");
	  for (int i = 0; i < count; i++) {
	      Account acc = accounts[i];
	      System.out.println("AccNo: " + acc.getAccNo() +
	                         ", Name: " + acc.getName() +
	                         ", Balance:" + acc.getBalance());
	  }
	}
	
	public void displayTransactions(Transaction[] transactions, int count) {
	  System.out.println("=== Transaction History ===");
	  for (int i = 0; i < count; i++) {
	      System.out.println(transactions[i].toString());
	  }
	}
	
	public void endOfDayReport(Transaction[] transactions, int count) {
	  System.out.println("=== End of Day Report ===");
	  LocalDate today = LocalDate.now();
	
	  double totalDeposits = 0;
	  double totalWithdrawals = 0;
	
	  for (int i = 0; i < count; i++) {
	      Transaction t = transactions[i];
	      if (t.transDate.equals(today)) {
	          System.out.println(t.toString());
	
	          if (t.transType.equalsIgnoreCase("DEPOSIT")) {
	              totalDeposits += t.transAmount;
	          } else if (t.transType.equalsIgnoreCase("WITHDRAW")) {
	              totalWithdrawals += t.transAmount;
	          }
	      }
	  }
	
	  System.out.println("--- Summary ---");
	  System.out.println("Total Deposits:" + totalDeposits);
	  System.out.println("Total Withdrawals:" + totalWithdrawals);
	  System.out.println("Net Change:" + (totalDeposits - totalWithdrawals));
	}
    public  void createAccount(BankController controller) {
    	    System.out.println("\nChoose Account Type:");
    	    System.out.println("1. Savings");
    	    System.out.println("2. Current");
    	    System.out.println("3. Loan");
    	    System.out.println("4. Salary");
    	    System.out.print("Enter choice: ");
    	
        System.out.print("Enter account number: ");
        double accNo = sc.nextDouble();
        System.out.print("Enter name: ");
        String name = sc.next();
        System.out.print("Enter initial balance: ");
        double balance = sc.nextDouble();

        // For simplicity, always create SavingsAccount here
        Account acc = new SavingsAccount(accNo, name, balance, 0.04, 5, 1000);
        controller.createAccount(acc);
    }
    
    

    public void deposit(BankController controller) {
        System.out.print("Enter account number: ");
        double accNo = sc.nextDouble();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        controller.deposit(accNo, amount);
    }

    public void withdraw(BankController controller) {
        System.out.print("Enter account number: ");
        double accNo = sc.nextDouble();
        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        controller.withdraw(accNo, amount);
    }

    public void calculateInterest(BankController controller) {
        System.out.print("Enter account number: ");
        double accNo = sc.nextDouble();
        controller.calculateInterest(accNo);
    }

    // Entry point
    public static void main(String[] args) {
        BankModel model = new BankModel();
        BankView view = new BankView();
        BankController controller = new BankController(model, view);

        view.showMenu(controller);
    }
}//main ends here

