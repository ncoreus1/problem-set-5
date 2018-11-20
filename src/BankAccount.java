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
	private static long accountnumber = 100000001L;
	
	private double balance;
	private long accountNum;
	private User user;
	
	BankAccount (double balance, User user) {
		this.balance = balance;
		this.accountNum = BankAccount.accountnumber++;
		this.user = user;
	}
	
	public double getbalance(double balance) {
		return balance;
	}
	
	public int getaccountnumber(int accountnumber) {
		return accountnumber;
	}
	
	public User getaccountNum(User accountNum) {
		return accountNum;
	}
	
	public int deposit(double amount) {
		if (amount <= 0) {
			return 0;
		} else {
			balance = balance + amount;
			
			return 1;
		}
	}
	
	public int withdraw(double amount) {
		if (amount > balance) {
			return 0;
		} else if (amount <= 0) {
			return 1;
		} else {
			balance = balance - amount;
			
			return 2;
		}
	}
}