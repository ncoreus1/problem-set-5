import java.util.Scanner;

/**
 * Just like last time, the ATM class is responsible for managing all
 * of the user interaction. This means login procedures, displaying the
 * menu, and responding to menu selections. In the enhanced version, the
 * ATM class will have the added responsibility of interfacing with the
 * Database class to write and read information to and from the database.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class ATM {
	/*public ATM(BankAccount account) {
		this.account = account;
		this.AccountHolder = AccountHolder;
	}
	
	public static void main(String[] args) {
		ATM atm = new ATM(new BankAccount(0, new AccountHolder(1256, "Nick", 5556667777L, "156 Main St., Scotch Plains, NJ 07076")));
		atm.run();
	}
	
		public void run() {
			in = new Scanner(System.in);
			boolean secure = false;
			
			while(!secure) {
				System.out.print("Account # : ");
				long accountnumber = in.nextLong();
				System.out.print("    PIN # : ");
				int PIN = in.nextInt();
				if (this.account.getaccountnumber() == accountnumber && this.account.getAccountHolder().getPIN() == PIN) {
					secure = true;
				}
				else {
					System.out.println("Invalid account number and/or PIN.");
				}
			}
			
			System.out.print("\nHi " + this.BankAccount.getAccountHolder().getName() + "! What can I help you with?");
			
			menu();
		}
		
		public void menu() {
			in = new Scanner(System.in);
			int menu = 0;
			
			System.out.print("\n\n   [1] Deposit\r\n" + 
					"   [2] Withdraw\r\n" + 
					"   [3] View Balance\r\n" + 
					"   [4] Exit\r\n" + 
					"\r\n" + 
					"Make a selection: ");
			
			while(menu == 0) {
				switch(in.nextInt()) {
				case 1:
					System.out.print("\nAlright, so we're depositing some money.\r\n" + 
							"\r\n" + 
							"Enter Amount: ");
					double deposit = in.nextDouble();
					
					if(this.BankAccount.deposit(deposit) == 0) {
						System.out.print("\nAmount must be greater than $0.00.");
					} else {
						System.out.print("\nDeposited $" + deposit + ". Updated balance is $" + this.BankAccount.getBalance() + ".");
					}
					
					menu();
				case 2:
					if (this.BankAccount.getBalance() == 0) {
						System.out.print("\nYou don't have any money to withdraw. Try depositing money first.");
					}
					else {
						System.out.print("\nAlright, so we're withdrawing some money.\r\n" + 
								"\r\n" + 
								"Enter Amount: ");
						
						double withdraw = in.nextDouble();
						
						if(this.BankAccount.withdraw(withdraw) == 2) {
							System.out.print("\nWithdrew $" + withdraw + ". Updated balance is $" + this.BankAccount.getBalance() + ".");
						} 
						else if(this.BankAccount.withdraw(withdraw) == 1) {
							System.out.print("\nAmount must be greater than $0.00.");
						} 
						else {
							System.out.print("\nInsufficient funds.");
						}
					}

					menu();
				case 3:
					System.out.print("\nCurrent balance is $" + this.BankAccount.getBalance() + ".");
					menu();
				case 4:
					System.out.print("\nGoodye!");
					break;
					run();
				default: 
					menu();
				}
			}
			in.close();
		}	
	}
	 */
}