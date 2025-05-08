package Com.Service;

import Com.Dto.SignupDto;
import Com.entity.Account;
import Com.repository.SignUpCredentials;

public class SignupService implements SignUpCredentials {

	private DatabaseServiece service = DatabaseServiece.getInstance();
	
	@Override
	public boolean Signup(SignupDto s) {
		Account a1 = service.findbyUsername(s.getUsername());
		if(a1!=null && s.getUsername().equals(a1.getUsername()) ) return false;
		Account acc = new Account();
		acc.setUsername(s.getUsername());
		acc.setPassword(s.getPassword());
		acc.setPhone(s.getPhone());
		acc.setFirstname(s.getFirstname());
		acc.setLastname(s.getLastname());
		acc.setGender(s.getGender());
		acc.setEmail(s.getEmail());
		acc.setBal(s.getBal());
		acc.setDob(s.getDob());
		acc.setAccno(s.getAccno());
		return service.Save(acc);
	}
	
	private static SignupService obj;
	
	public static SignupService getInstance() {
		if(obj==null) obj = new SignupService();
		return obj;
	}
}