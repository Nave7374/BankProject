package Com.repository;

import Com.entity.Account;

public interface DatabaseCredentials {

	Account findbyUsername(String username);
	
	boolean Save(Account a);
	
	void Update(Account a);
}