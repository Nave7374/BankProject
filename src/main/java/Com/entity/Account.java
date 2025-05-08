package Com.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Account {
	
	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String username;
	private String password;
	private String gender;
	private String phone;
	private String firstname;
	private String lastname;
	private String dob;
	private String email;
	private String accno;
	
	private double bal;

	@OneToMany(cascade = CascadeType.ALL,mappedBy = "accounts")
	List<Transactions> transactions;
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getGender() {
		return gender;
	}

	public String getPhone() {
		return phone;
	}

	public String getFirstname() {
		return firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public String getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public double getBal() {
		return bal;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}

	public String getAccno() {
		return accno;
	}

	public void setAccno(String accno) {
		this.accno = accno;
	}

	public List<Transactions> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transactions> transactions) {
		this.transactions = transactions;
	}

	

	@Override
	public String toString() {
		return "username=" + username + "\n"
			    + "gender=" + gender + "\n"
			    + "phone=" + phone + "\n"
			    + "firstname=" + firstname + "\n"
			    + "lastname=" + lastname + "\n"
			    + "dob=" + dob + "\n"
			    + "email=" + email + "\n"
			    + "accno=" + accno + "\n"
			    + "bal=" + bal;
	}
	
}