/**
 * Just like last time, the User class is responsible for retrieving
 * (i.e., getting), and updating (i.e., setting) user information.
 * This time, though, you'll need to add the ability to update user
 * information and display that information in a formatted manner.
 * 
 * Most of the functionality for this class should have already been
 * implemented last time. You can always reference my Github repository
 * for inspiration (https://github.com/rwilson-ucvts/java-sample-atm).
 */

public class User {
	private String fname;
	private String lname;
	private int pin;
	private String dob;
	private long phone;
	private String address;
	private String city;
	private String state;
	private int postal;
	
	User (int pin, String fname, String lname, String dob, long phone, String address, String city, String state, int postal) {
		this.fname = fname;
		this.lname = lname;
		this.pin = pin;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.city = city;
		this.state = state;
		this.postal = postal;
	}
	
	public String getfname(String fname) {
		return fname;
	}
	public String getlname(String lname) {
		return lname;
	}
	public int getpin(int pin) {
		return pin;
	}
	public String getdob(String dob) {
		return dob;
	}
	public long getphone(long phone) {
		return phone;
	}
	public String getaddress(String address) {
		return address;
	}
	public String getcity(String city) {
		return city;
	}
	public String getstate(String state) {
		return state;
	}
	public int getpostal(int postal) {
		return postal;
	}
	
	public void setaddress(String address) {
		this.address = address;
	}
	public void setcity(String city) {
		this.city = city;
	}
	public void setstate(String state) {
		this.state = state;
	}
	public void setpostal(int postal) {
		this.postal = postal;
	}
}