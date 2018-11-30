/**
 * Just like last time, the BankAccount class is primarily responsible
 * for depositing and withdrawing money. In the enhanced version, there
 * will be the added requirement of transfering funds between accounts.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class BankAccount {
	
	private double balance;
	private long accountNum;
	private User user;
	
	BankAccount (long accountNum, double balance, User user) {
		this.balance = balance;
		this.accountNum = accountNum;
		this.user = user;
	}
	
	public double getbalance(double balance) {
		return balance;
	}
	
	public long getaccountnumber(long accountNum) {
		return accountNum;
	}
	
	public User getuser(User user) {
		return user;
	}
	
	
	public double deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			balance = balance + amount;
			
			return 1;
		}
	}
	
	public double withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			balance = balance - amount;
			
			return 2;
		}
	}
	public double transfer(BankAccount receiver, double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			this.balance -= amount;
			receiver.balance = balance + amount;
			
			return 2;
		}
	}
}