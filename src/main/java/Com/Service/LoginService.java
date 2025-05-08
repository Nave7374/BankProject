package Com.Service;

import Com.Dto.LoginDto;
import Com.entity.Account;
import Com.repository.LoginCredentials;

public class LoginService implements LoginCredentials {

	private static LoginService obj;
	private DatabaseServiece dbservice = DatabaseServiece.getInstance();
	
	@Override
	public boolean login(LoginDto l) {
		Account a= dbservice.findbyUsername(l.getUsername());
		if(a!=null) {
			return l.getPassword().equals(a.getPassword());
		}else {
			System.out.println("Object returned is Null");
			return false;
		}
	}

	public static LoginService getInstance() {
		if(obj==null)obj=new LoginService();
		return obj;
	}
}