import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Database {
	
	private String path;
	private String[] accounts;
	
	public Database(String path) throws FileNotFoundException, IOException {
		this.path = path;
		this.accounts = getAllAccounts();
	}
	
	/**
	 * Initializes the database with all accounts.
	 * 
	 * @return an array of all accounts
	 */
	
	public String[] getAllAccounts() throws FileNotFoundException, IOException {
		int count = 0;
		String[] accounts = new String[10];
		
		FileReader altered = null;
		InputStreamReader original = null;
		try {
			altered = new FileReader(System.getProperty("user.dir") + File.separator + path);			
		} catch (FileNotFoundException e) {
			original = new InputStreamReader(getClass().getResourceAsStream(path));
		}
		
		try (BufferedReader br = new BufferedReader(original != null ? original : altered)) {
			String line;
			
			while ((line = br.readLine()) != null) {
				if (count >= accounts.length) {
					accounts = Arrays.copyOf(accounts, accounts.length + 10);
				}
				accounts[count++] = line;
			}
		}
		
		return Arrays.copyOf(accounts, count);
	}
	
	/**
	 * Retrieves an account by account number.
	 * 
	 * @param accountNumber the acocunt number of the account to retrieve
	 * @return a BankAccount
	 */
	
	public BankAccount getAccount(long accountNumber) {
		for (String account : accounts) {
			if (account.startsWith(String.valueOf(accountNumber)) && account.endsWith("Y")) {
				return new BankAccount(account);
			}
		}
		
		return null;
	}
	
	/**
	 * Updates a BankAccount.
	 * 
	 * @param account the primary account being updated
	 * @param destination the secondary account being updated
	 * @throws IOException 
	 */
	
	public void updateAccount(BankAccount account, BankAccount destination) throws IOException {
		boolean newAccount = true;
		
		for (int i = 0; i < accounts.length; i++) {			
			if (accounts[i].startsWith(String.valueOf(account.getAccountNumber()))) {
				accounts[i] = account.toString();
				newAccount = false;
			}
			
			if (destination != null) {
				if (accounts[i].startsWith(String.valueOf(destination.getAccountNumber()))) {
					accounts[i] = destination.toString();
				}
			}
		}
		
		if (newAccount) {
			accounts = Arrays.copyOf(accounts, accounts.length + 1);
			accounts[accounts.length - 1] = account.toString();
		}
		
		try (BufferedWriter bw = new BufferedWriter(new FileWriter(System.getProperty("user.dir") + File.separator + path))) {
			for (String acct : accounts) {
				bw.write(acct);
				bw.newLine();
			}
		}
	}
	
	/**
	 * Retrieves the largest account number in the database.
	 * 
	 * @return the largest account number
	 */
	
	public long getMaxAccountNumber() {
		long max = -1L;
		
		for (String account : accounts) {
			long accountNumber = Long.parseLong(account.substring(0, 9));
			
			if (accountNumber > max) {
				max = accountNumber;
			}
		}
		
		return max;
	}
}
	
	/*public BankAccount getAccount(long accountNum) throws Exception {
		BankAccount account = null;
		
		try(BufferedReader br = new BufferedReader(new FileReader("accounts-db.txt"))) {
			String line;
			while ((line = br.readLine()) != null) {
				long acctNum = Long.parseLong(line.substring(0,9));
				
				if (acctNum == accountNum && line.charAt(148) == 'Y') {
					int pin = Integer.parseInt(line.substring(9, 13));
					double balance = Double.parseDouble(line.substring(13, 28));
					String lname = line.substring(28, 48).trim();
					String fname = line.substring(48, 63).trim();
					String dob = line.substring(63, 71);
					long phone = Long.parseLong(line.substring(71, 81));
					String address = line.substring(81, 111).trim();
					String city = line.substring(111, 141).trim();
					String state = line.substring(141, 143);
					int postal = Integer.parseInt(line.substring(143, 148));
					
					account = new BankAccount(acctNum, balance, new User(pin, fname, lname, dob, phone, address, city, state, postal));
					break;
				}
			}
		}
		
				
		return account;
	}
}*/
