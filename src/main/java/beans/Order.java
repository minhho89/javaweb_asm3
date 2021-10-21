package beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;

public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;
	private String mail;
	private int status;
	private LocalDate date;
	private String discountCode;
	private String address;
	
	public Order() {
		this.id = -1;
		this.mail = "";
		this.status = -1;
		this.date = LocalDate.of(1990, Month.JANUARY, 1);
		this.discountCode = "";
		this.address = "";
	}

	public Order(int id, String mail, int status, LocalDate date, String discountCode, String address) {
		this.id = id;
		this.mail = mail;
		this.status = status;
		this.date = date;
		this.discountCode = discountCode;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getDiscountCode() {
		return discountCode;
	}

	public void setDiscountCode(String discountCode) {
		this.discountCode = discountCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", mail=" + mail + ", status=" + status + ", date=" + date + ", discountCode="
				+ discountCode + ", address=" + address + "]";
	}
	
	
}
