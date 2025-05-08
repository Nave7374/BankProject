package Com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Transactions {

	@Id@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String msg;
	
	@ManyToOne
	@JoinColumn(name = "acc_id")
	private Account accounts;
	
	public Transactions() {
		// TODO Auto-generated constructor stub
	}
	
	public Transactions(String msg) {
		this.msg=msg;
	}

	public int getId() {
		return id;
	}

	public String getMsg() {
		return msg;
	}

	public Account getAcc() {
		return accounts;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void setAcc(Account accounts) {
		this.accounts = accounts;
	} 
}