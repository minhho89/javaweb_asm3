package beans;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String name;
	private String mail;
	private String password;
	private int role;
	private String address;
	private String phone;
	
	public Account() {
		this.name = "";
		this.mail = "";
		this.password = "";
		this.role = -1;
		this.address = "";
		this.phone = "";
	}

	public Account(String mail, String password) {
		this.mail = mail;
		this.password = password;
	}

	public Account(String name, String mail, String password, int role, String address, String phone) {
		this.name = name;
		this.mail = mail;
		this.password = password;
		this.role = role;
		this.address = address;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Account [name=" + name + ", mail=" + mail + ", password=" + password + ", role=" + role + ", address="
				+ address + ", phone=" + phone + "]";
	}
	
	

}
