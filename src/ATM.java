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

import java.util.Scanner;

public class ATM {
	private Database database;
	private Scanner in;
	private BankAccount currentAccount = null;
	private BankAccount destination = null;

	public ATM(Database database) {
		this.database = database;
	}
	
	public void run() throws Exception {
		System.out.print("Welcome to APCSA Enhanced ATM, the ATM of choice for the UCVTS community.\r\n" + 
				"To begin, create a new account or access an existing one.");
		
		menu();
	}
	
	public void menu() throws Exception {
		in = new Scanner(System.in);
		System.out.print("\r\n" + 
				"   [1] Open Account\r\n" + 
				"   [2] Login\r\n" + 
				"   [3] Quit\r\n" + 
				"\r\n" + 
				"Make a selection: ");
		switch(in.next().charAt(0)) {
		case '1': 
			System.out.println("Alright, so you're opening a new account. Enter your information");
			this.createAccount();
			screen();
			break;
		case '2':
			try {
				System.out.print("Please enter your account credentials.\r\n" + 
						"\r\n" + 
						"Account # : ");
				long account = in.nextLong();
				this.currentAccount = this.database.getAccount(account);
				if(this.currentAccount == null) {
					System.out.print("Invalid Account #");
					menu();
				}
				System.out.print("    PIN # : ");
				int pin = in.nextInt();
				if(this.currentAccount.getUser().getPIN() == pin) {
					screen();
				} else {
					System.out.print("Invalid PIN");
					menu();
				}
			} catch (InvalidParameterException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Invalid");
			}
			
			break;
		case '3': 
			System.out.print("Powering Off.");
			break;
			
		default:
			System.out.println("Invalid");
			menu();
			break;
			
		}
	}
	
	public void screen() throws Exception {
		System.out.print("\nHi, " + this.currentAccount.getUser().getfirstName() + " " + this.currentAccount.getUser().getlastName() + "! What can I help you with?\r\n" + 
				"\r\n" + 
				"   [1] Deposit\r\n" + 
				"   [2] Withdraw\r\n" + 
				"   [3] Transfer\r\n" + 
				"   [4] View Balance\r\n" + 
				"   [5] View Personal Information\r\n" + 
				"   [6] Update Personal Information\r\n" + 
				"   [7] Close Account\r\n" + 
				"   [8] Logout\r\n" + 
				"\r\n" + 
				"Make a selection:");
		
		switch (in.next().charAt(0)) {
		case '1':
			System.out.println("How much would you like to deposit?");
			try {
				double deposit = in.nextDouble();
				
				if(this.currentAccount.deposit(deposit) == 0) {
					System.out.print("\nAmount must be greater than $0.00.");
				} else {
					System.out.print("\nDeposited $" + deposit + ". Updated balance is $" + this.currentAccount.getBalance() + ".");
				}
			} catch (InvalidParameterException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Invalid amount");
			}
			screen();
			break;
		case '2':
			System.out.println("How much would you like to withdraw?");
			try {
				if (this.currentAccount.getBalance() == 0) {
					System.out.print("\nYou don't have any money to withdraw. Try depositing money first.");
				}
				else {
					System.out.print("\nAlright, so we're withdrawing some money.\r\n" + 
							"\r\n" + 
							"Enter Amount: ");
					
					double withdraw = in.nextDouble();
					
					if(this.currentAccount.withdraw(withdraw) == 2) {
						System.out.print("\nWithdrew $" + withdraw + ". Updated balance is $" + this.currentAccount.getBalance() + ".");
					} 
					else if(this.currentAccount.withdraw(withdraw) == 1) {
						System.out.print("\nAmount must be greater than $0.00.");
					} 
					else {
						System.out.print("\nInsufficient funds.");
					}
				}
				
			} catch (InvalidParameterException e) {
				System.out.println(e.getMessage());
			} catch (InputMismatchException e) {
				System.out.println("Invalid amount");
			}
			screen();
			break;
		case '3':
			if (this.currentAccount.getBalance() == 0) {
				System.out.print("\nYou don't have any money to withdraw. Try depositing money first.");
				screen();
			} else {
				System.out.print("Enter Destination Account #: ");
				try {
					Long destinationAmt = in.nextLong();
					this.destination = this.database.getAccount(destinationAmt);
					System.out.print("Enter Amount: ");
					double amount = in.nextDouble();
					if(this.destination == null) {
						System.out.print("Account Not Found");
						screen();
					} else {
						this.currentAccount.withdraw(amount);
						this.destination.setBalance(this.destination.getBalance() + amount);
						this.database.updateAccount(this.currentAccount, this.destination);
						System.out.print("Transferred " + "$" + amount + " to " + this.destination.getAccountNumber() + ". Your updated balance is $" + String.format("%,10.2f", currentAccount.getBalance()).trim());
						
					}
				} catch (InvalidParameterException e) {
					System.out.println(e.getMessage()); 
				}
			}
			
			break;
		case '4':
			this.database.updateAccount(this.currentAccount, null);
			System.out.println("Current balance is: $" + String.format("%,10.2f", currentAccount.getBalance()).trim());
			screen();
			break;
		case '5':
			System.out.println("Account Number: " + this.currentAccount.getAccountNumber());
			System.out.println("Account Holder: " + this.currentAccount.getUser().getfirstName() + " " + this.currentAccount.getUser().getlastName());
			System.out.println("Address: " + this.currentAccount.getUser().getstreetAddress() + " " + this.currentAccount.getUser().getCity() + ", " + this.currentAccount.getUser().getState() + " " + this.currentAccount.getUser().getpostalCode());
			System.out.println("Date of Birth(YYYYMMDD): " + this.currentAccount.getUser().getDOB());
			System.out.println("Telephone: " + this.currentAccount.getUser().getPhone());
			screen();
			break;
		case '6':
			//while (!this.currentAccount.getUser().updateInfo(in)) ;
			System.out.println("Select the personal information you wish to update.\r\n" + 
					"\r\n" + 
					"   [1] PIN\r\n" + 
					"   [2] Telephone\r\n" + 
					"   [3] Address\r\n" + 
					"\r\n" + 
					"Make a selection: ");
			
			switch(in.next().charAt(0)) {
			case '1':
				System.out.print("Enter current PIN #: ");
				int test = in.nextInt();
				if(test == this.currentAccount.getUser().getPIN()) {
					System.out.print("Enter New Pin: ");
					int newpin = in.nextInt();
					if (String.valueOf(newpin).length() == 4) {
						System.out.print("Successfully changed PIN from " + this.currentAccount.getUser().getPIN() + " to " + newpin);
						this.currentAccount.getUser().setPIN(newpin);
						this.database.updateAccount(this.currentAccount, null);
					} else {
						System.out.print("PIN must be 4 digits");
						screen();
					}
				} else {
					System.out.print("Invalid PIN");
					screen();
				}
				screen();
				break;
			case '2':
				System.out.print("Enter new number: ");
				long phone = in.nextLong();
				if (String.valueOf(phone).length() == 10) {
					System.out.print("Successfully changed phone number from " + this.currentAccount.getUser().getPhone() + " to " + phone);
					this.currentAccount.getUser().setPhone(phone);
					this.database.updateAccount(this.currentAccount, null);
					screen();
				} else {
					System.out.print("Phone should be 10 digits");
					screen();
				}
				break;
			case '3':
				System.out.print("Enter new street address : ");
				in.nextLine();
				String address = in.nextLine();
				System.out.print("          Enter new city : ");
				String city = in.nextLine();
				System.out.print("       Enter new state(ex: NJ): ");
				String state = in.nextLine();
				if(String.valueOf(state).length() != 2) {
					System.out.print("2 digits please");
					screen();
				}
				System.out.print("   Enter new postal code : ");
				String postalcode = in.nextLine();

				System.out.print("Successfully changed address from " + this.currentAccount.getUser().getstreetAddress() + " " + this.currentAccount.getUser().getCity() + ", " + this.currentAccount.getUser().getState() + " " + this.currentAccount.getUser().getpostalCode() + " to " + address + " " +  city + ", " + state + " " + postalcode);
				this.currentAccount.getUser().setstreetAddress(address);
				this.currentAccount.getUser().setCity(city);
				this.currentAccount.getUser().setState(state);
				this.currentAccount.getUser().setpostalCode(postalcode);
				screen();
				break;
			default: 
				System.out.print("Invalid Option");
				screen();
			}
			break;

		case '7':
			System.out.print("Aw, we're sorry to see you go... Are you sure?\r\n" + 
					"\r\n" + 
					"Confirm (Y/N):");
			char confirm = in.next().charAt(0);
			if(confirm == 'y' || confirm == 'Y') {
				System.out.println("Closing account");
				this.currentAccount.getUser().close();
				this.database.updateAccount(this.currentAccount, null);
				menu();
			} else if (confirm == 'n' || confirm == 'N') {
				screen();
			}
			this.database.updateAccount(this.currentAccount, null);
			break;
		case '8':
			System.out.println("Logging out");
			this.database.updateAccount(this.currentAccount, null);
			this.currentAccount = null;
			menu();
			break;
		default:
			System.out.println("Invalid option");
			screen();
			break;
		}
	}
	
	private void createAccount() throws IOException {
		this.currentAccount = new BankAccount(in);
		database.updateAccount(this.currentAccount, null);
	}
}	/*public ATM(BankAccount account) {
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

